/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Service
 *  org.springframework.transaction.annotation.Transactional
 */
package com.foodapp.deliveryexecutive.order.service;

import com.foodapp.deliveryexecutive.common.exception.ResourceNotFoundException;
import com.foodapp.deliveryexecutive.executive.entity.DeliveryExecutive;
import com.foodapp.deliveryexecutive.executive.repository.DeliveryExecutiveRepository;
import com.foodapp.deliveryexecutive.notification.service.OrderNotificationService;
import com.foodapp.deliveryexecutive.order.dto.OrderDetailsDTO;
import com.foodapp.deliveryexecutive.order.dto.OrderSummaryDTO;
import com.foodapp.deliveryexecutive.order.entity.Order;
import com.foodapp.deliveryexecutive.order.repository.OrderRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private DeliveryExecutiveRepository deliveryExecutiveRepository;
    @Autowired(required = false)
    private OrderNotificationService orderNotificationService;

    public Order getOrderById(Long orderId) {
        return (Order)this.orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + orderId));
    }

    public OrderDetailsDTO getOrderDetails(Long orderId) {
        Order order = this.getOrderById(orderId);
        return this.mapToOrderDetailsDTO(order);
    }

    public List<OrderDetailsDTO> getOrdersByExecutiveId(Long executiveId) {
        return this.orderRepository.findByExecutiveIdOrderByIdDesc(executiveId).stream().map(this::mapToOrderDetailsDTO).collect(Collectors.toList());
    }

    public List<OrderDetailsDTO> getOrdersByUserId(Long userId) {
        return this.orderRepository.findByUserId(userId).stream().map(this::mapToOrderDetailsDTO).collect(Collectors.toList());
    }

    public List<OrderDetailsDTO> getDeliveredOrders(Long executiveId) {
        return this.orderRepository.findByExecutiveIdAndOrderStatus(executiveId, Order.OrderStatus.DELIVERED).stream().map(this::mapToOrderDetailsDTO).collect(Collectors.toList());
    }

    public List<OrderDetailsDTO> getActiveOrders(Long executiveId) {
        return this.orderRepository.findActiveOrdersByExecutiveId(executiveId).stream().map(this::mapToOrderDetailsDTO).collect(Collectors.toList());
    }

    public List<Order> getAvailableOrders() {
        return this.orderRepository.findAvailableOrders();
    }

    public List<OrderSummaryDTO> getAvailableOrdersSummary() {
        return this.getAvailableOrders().stream().map(this::mapToOrderSummaryDTO).collect(Collectors.toList());
    }

    @Transactional
    public Order acceptOrder(Long executiveId, Long orderId) {
        Order order = this.getOrderById(orderId);
        if (order.getExecutive() != null) {
            throw new RuntimeException("Order already accepted by another executive");
        }
        if (order.getOrderStatus() != Order.OrderStatus.PREPARED && order.getOrderStatus() != Order.OrderStatus.PREPARING) {
            throw new RuntimeException("Order is not available for acceptance");
        }
        DeliveryExecutive executive = (DeliveryExecutive)this.deliveryExecutiveRepository.findById(executiveId).orElseThrow(() -> new ResourceNotFoundException("Executive not found"));
        order.setExecutive(executive);
        order.setOrderStatus(Order.OrderStatus.ACCEPTED);
        this.orderRepository.save(order);
        logger.info("Order {} accepted by executive {}", orderId, executiveId);
        
        // Send notifications
        if (orderNotificationService != null) {
            orderNotificationService.notifyExecutiveOrderAssigned(order);
        }
        return order;
    }

    @Transactional
    public Order confirmPickup(Long executiveId, Long orderId) {
        Order order = this.getOrderById(orderId);
        if (order.getExecutive().getId() != executiveId) {
            throw new RuntimeException("Order not assigned to this executive");
        }
        if (order.getOrderStatus() != Order.OrderStatus.ACCEPTED) {
            throw new RuntimeException("Order must be in ACCEPTED status");
        }
        order.setOrderStatus(Order.OrderStatus.OUTFORDELIVERY);
        this.orderRepository.save(order);
        logger.info("Order {} picked up by executive {}", orderId, executiveId);
        
        // Send notifications
        if (orderNotificationService != null) {
            orderNotificationService.notifyUserOrderPickedUp(order);
            orderNotificationService.notifyUserOrderOutForDelivery(order);
        }
        return order;
    }

    @Transactional
    public Order confirmDelivery(Long executiveId, Long orderId, String customerOtp) {
        Order order = this.getOrderById(orderId);
        if (order.getExecutive().getId() != executiveId) {
            throw new RuntimeException("Order not assigned to this executive");
        }
        if (order.getOrderStatus() != Order.OrderStatus.OUTFORDELIVERY) {
            throw new RuntimeException("Order must be out for delivery");
        }
        if (!order.getCustomerOtp().equals(customerOtp)) {
            throw new RuntimeException("Invalid OTP");
        }
        order.setOrderStatus(Order.OrderStatus.DELIVERED);
        this.orderRepository.save(order);
        logger.info("Order {} delivered by executive {}", orderId, executiveId);
        
        // Send notifications
        if (orderNotificationService != null) {
            orderNotificationService.notifyUserOrderDelivered(order);
        }
        return order;
    }

    @Transactional
    public Order updateOrderStatus(Long orderId, Order.OrderStatus status) {
        Order order = this.getOrderById(orderId);
        Order.OrderStatus previousStatus = order.getOrderStatus();
        order.setOrderStatus(status);
        this.orderRepository.save(order);
        logger.info("Order {} status updated to {}", orderId, status);
        
        // Send notifications based on status change
        if (orderNotificationService != null) {
            sendStatusChangeNotifications(order, previousStatus, status);
        }
        return order;
    }
    
    private void sendStatusChangeNotifications(Order order, Order.OrderStatus previousStatus, Order.OrderStatus newStatus) {
        switch (newStatus) {
            case PREPARING:
                orderNotificationService.notifyUserOrderConfirmed(order);
                orderNotificationService.notifyUserOrderPreparing(order);
                break;
            case PREPARED:
                orderNotificationService.notifyUserOrderReady(order);
                if (order.getExecutive() != null) {
                    orderNotificationService.notifyExecutiveOrderReady(order);
                }
                // Notify nearby executives about new order ready for pickup
                if (order.getPickupLocation() != null) {
                    orderNotificationService.notifyNearbyExecutivesNewOrder(
                        order, 
                        order.getPickupLocation().getX(), 
                        order.getPickupLocation().getY(), 
                        5.0 // 5km radius
                    );
                }
                break;
            case CANCELLED:
                orderNotificationService.notifyUserOrderCancelled(order, null);
                orderNotificationService.notifyHomemakerOrderCancelled(order, null);
                break;
            default:
                break;
        }
    }

    public Long getDeliveredOrderCount(Long executiveId) {
        return this.orderRepository.countByExecutiveIdAndStatus(executiveId, Order.OrderStatus.DELIVERED);
    }

    public Double getTotalEarnings(Long executiveId) {
        Double total = this.orderRepository.getTotalEarningsByExecutiveId(executiveId);
        return total != null ? total : 0.0;
    }

    public List<OrderDetailsDTO> getOrdersByStatus(Order.OrderStatus status) {
        return this.orderRepository.findByOrderStatus(status).stream().map(this::mapToOrderDetailsDTO).collect(Collectors.toList());
    }

    private OrderDetailsDTO mapToOrderDetailsDTO(Order order) {
        OrderDetailsDTO dto = new OrderDetailsDTO();
        dto.setOrderId(order.getId());
        dto.setUserId(order.getUser() != null ? order.getUser().getId() : null);
        dto.setHomeMakerId(order.getHomeMaker() != null ? order.getHomeMaker().getId() : null);
        dto.setHomemakerName(order.getHomeMaker() != null ? order.getHomeMaker().getName() : null);
        dto.setExecutiveId(order.getExecutive() != null ? order.getExecutive().getId() : null);
        dto.setPickupLocation(order.getPickupLocation());
        dto.setDeliveryLocation(order.getDeliveryLocation());
        dto.setAmount(order.getAmount());
        dto.setDeliveryFee(order.getDeliveryFee());
        dto.setPayment(order.getPayment());
        dto.setPaymentMethod(order.getPaymentMethod());
        dto.setOrderStatus(order.getOrderStatus());
        dto.setDistance(order.getDistance());
        dto.setOrderDate(order.getCreatedAt() != null ? order.getCreatedAt().toString() : null);
        return dto;
    }

    private OrderSummaryDTO mapToOrderSummaryDTO(Order order) {
        OrderSummaryDTO dto = new OrderSummaryDTO();
        dto.setOrderId(order.getId());
        dto.setAmount(order.getAmount());
        dto.setOrderStatus(order.getOrderStatus());
        dto.setPickupLocation(order.getPickupLocation());
        dto.setDeliveryLocation(order.getDeliveryLocation());
        dto.setPayment(order.getPayment());
        dto.setDistance((int)order.getDistance());
        return dto;
    }
}
