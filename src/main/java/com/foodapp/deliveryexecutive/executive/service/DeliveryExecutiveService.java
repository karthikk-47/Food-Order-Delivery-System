package com.foodapp.deliveryexecutive.executive.service;

import com.foodapp.deliveryexecutive.common.entity.Actor;
import com.foodapp.deliveryexecutive.common.exception.ResourceNotFoundException;
import com.foodapp.deliveryexecutive.executive.dto.DeliveryLoginRequest;
import com.foodapp.deliveryexecutive.executive.dto.DeliveryLoginResponse;
import com.foodapp.deliveryexecutive.executive.dto.DeliveryRegisterRequest;
import com.foodapp.deliveryexecutive.executive.dto.DeliveryRegisterResponse;
import com.foodapp.deliveryexecutive.executive.dto.DeliveryStatusUpdateRequest;
import com.foodapp.deliveryexecutive.executive.entity.DeliveryExecutive;
import com.foodapp.deliveryexecutive.executive.repository.DeliveryExecutiveRepository;
import com.foodapp.deliveryexecutive.map.service.MapService;
import com.foodapp.deliveryexecutive.order.dto.OrderSummaryDTO;
import com.foodapp.deliveryexecutive.order.entity.Order;
import com.foodapp.deliveryexecutive.order.service.OrderService;
import com.foodapp.deliveryexecutive.order.service.OrderSortingService;
import com.foodapp.deliveryexecutive.rating.dto.RatingDTO;
import com.foodapp.deliveryexecutive.rating.service.RatingService;
import com.foodapp.deliveryexecutive.security.JwtTokenProvider;
import com.foodapp.deliveryexecutive.wallet.dto.WalletDTO;
import com.foodapp.deliveryexecutive.wallet.service.WalletService;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeliveryExecutiveService {
    private static final Logger logger = LoggerFactory.getLogger(DeliveryExecutiveService.class);
    @Autowired
    private DeliveryExecutiveRepository deliveryExecutiveRepository;
    @Autowired
    private WalletService walletService;
    @Autowired
    private RatingService ratingService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private MapService mapService;
    @Autowired
    private OrderSortingService orderSortingService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public DeliveryRegisterResponse register(DeliveryRegisterRequest request) {
        DeliveryExecutive executive = new DeliveryExecutive();
        executive.setName(request.getName());
        executive.setAadharNo(request.getAadharNumber());
        executive.setMobile(request.getMobile());
        executive.setPassword(this.passwordEncoder.encode((CharSequence)request.getPassword()));
        executive.setLicenseNo(request.getLicenseNumber());
        executive.setRole(Actor.Role.DELIVERYEXECUTIVE);
        executive.setOnline(false);
        this.deliveryExecutiveRepository.save(executive);
        logger.info("Delivery executive registered with ID: {}", executive.getId());
        DeliveryRegisterResponse response = new DeliveryRegisterResponse();
        response.setDeliveryExecutiveId(executive.getId());
        response.setMessage("Successfully Registered");
        return response;
    }

    public DeliveryLoginResponse login(DeliveryLoginRequest request) {
        DeliveryExecutive executive = this.deliveryExecutiveRepository.findByMobile(request.getMobile()).orElseThrow(() -> new ResourceNotFoundException("Invalid mobile number"));
        if (executive.getApprovalStatus() == DeliveryExecutive.ApprovalStatus.PENDING) {
            throw new RuntimeException("Your profile is pending approval. Please wait for admin verification.");
        }
        if (executive.getApprovalStatus() == DeliveryExecutive.ApprovalStatus.REJECTED) {
            String reason = executive.getRejectionReason() != null ? executive.getRejectionReason() : "No reason provided";
            throw new RuntimeException("Your profile has been rejected. Reason: " + reason);
        }
        Authentication authentication = this.authenticationManager.authenticate((Authentication)new UsernamePasswordAuthenticationToken(request.getMobile(), request.getPassword()));
        String jwt = this.jwtTokenProvider.createToken(authentication);
        logger.info("Delivery executive logged in: {}", executive.getId());
        DeliveryLoginResponse response = new DeliveryLoginResponse();
        response.setDeliveryExecutiveId(executive.getId());
        response.setName(executive.getName());
        response.setToken(jwt);
        return response;
    }

    @Transactional
    public void updateStatus(Long executiveId, DeliveryStatusUpdateRequest request) {
        DeliveryExecutive executive = (DeliveryExecutive)this.deliveryExecutiveRepository.findById(executiveId).orElseThrow(() -> new ResourceNotFoundException("Executive not found"));
        executive.setOnline(request.isOnline());
        this.deliveryExecutiveRepository.save(executive);
        logger.info("Executive {} status updated to: {}", executiveId, (Object)(request.isOnline() ? "online" : "offline"));
    }

    public List<OrderSummaryDTO> getNearbyOrders(Long executiveId, Point location) {
        return this.getNearbyOrders(executiveId, location, "optimal");
    }

    public List<OrderSummaryDTO> getNearbyOrders(Long executiveId, Point location, String sortBy) {
        this.deliveryExecutiveRepository.findById(executiveId).orElseThrow(() -> new ResourceNotFoundException("Executive not found"));
        List<Order> availableOrders = this.orderService.getAvailableOrders();
        if (availableOrders.isEmpty()) {
            logger.info("No available orders found");
            return List.of();
        }
        try {
            List<OrderSummaryDTO> sortedOrders;
            Map<Long, Integer> nearbyOrdersMap = this.mapService.calculateDistancesToOrders(location, availableOrders);
            List<Order> nearbyOrders = availableOrders.stream().filter(o -> nearbyOrdersMap.containsKey(o.getId())).collect(Collectors.toList());
            switch (sortBy.toLowerCase()) {
                case "commission": {
                    sortedOrders = this.orderSortingService.sortByCommission(nearbyOrders, nearbyOrdersMap);
                    logger.info("Sorted {} orders by commission for executive: {}", sortedOrders.size(), executiveId);
                    break;
                }
                case "distance": {
                    sortedOrders = this.orderSortingService.sortByDistance(nearbyOrders, nearbyOrdersMap);
                    logger.info("Sorted {} orders by distance for executive: {}", sortedOrders.size(), executiveId);
                    break;
                }
                case "priority": {
                    sortedOrders = this.orderSortingService.sortByPriority(nearbyOrders, nearbyOrdersMap);
                    logger.info("Sorted {} orders by priority for executive: {}", sortedOrders.size(), executiveId);
                    break;
                }
                default: {
                    sortedOrders = this.orderSortingService.sortOrdersByOptimalParameters(nearbyOrders, nearbyOrdersMap);
                    logger.info("Sorted {} orders by optimal score for executive: {}", sortedOrders.size(), executiveId);
                }
            }
            return sortedOrders;
        }
        catch (Exception e) {
            logger.error("Failed to get nearby orders for executive: {}", executiveId, e);
            return List.of();
        }
    }

    @Transactional
    public void updateLiveLocation(Long executiveId, Point location) {
        DeliveryExecutive executive = (DeliveryExecutive)this.deliveryExecutiveRepository.findById(executiveId).orElseThrow(() -> new ResourceNotFoundException("Executive not found"));
        executive.addLocation(LocalTime.now(), location);
        this.deliveryExecutiveRepository.save(executive);
        logger.debug("Updated location for executive: {}", executiveId);
    }

    @Transactional
    public void processDeliveryPayment(Long executiveId, Long orderId) {
        Order order = this.orderService.getOrderById(orderId);
        if (order.getExecutive().getId() != executiveId) {
            throw new RuntimeException("Order not assigned to this executive");
        }
        this.walletService.processDeliveryPayment(executiveId, order);
        logger.info("Processed delivery payment for executive: {}", executiveId);
    }

    public WalletDTO getWallet(Long executiveId) {
        return this.walletService.getWalletDTO(executiveId, Actor.Role.DELIVERYEXECUTIVE);
    }

    public List<RatingDTO> getRatings(Long executiveId) {
        return this.ratingService.getRatingsByIdAndRole(executiveId, Actor.Role.DELIVERYEXECUTIVE);
    }

    public Double getAverageRating(Long executiveId) {
        return this.ratingService.getAverageRating(executiveId, Actor.Role.DELIVERYEXECUTIVE);
    }

    public DeliveryExecutive getExecutiveById(Long executiveId) {
        return (DeliveryExecutive)this.deliveryExecutiveRepository.findById(executiveId).orElseThrow(() -> new ResourceNotFoundException("Executive not found with ID: " + executiveId));
    }

    public Map<String, Object> getProfileById(Long executiveId) {
        DeliveryExecutive executive = this.getExecutiveById(executiveId);
        HashMap<String, Object> profile = new HashMap<String, Object>();
        profile.put("id", executive.getId());
        profile.put("name", executive.getName());
        profile.put("mobile", executive.getMobile());
        profile.put("aadharNo", executive.getAadharNo());
        profile.put("licenseNo", executive.getLicenseNo());
        profile.put("approvalStatus", executive.getApprovalStatus());
        profile.put("online", executive.isOnline());
        profile.put("totalDeliveries", this.orderService.getDeliveredOrderCount(executiveId));
        profile.put("totalEarnings", this.orderService.getTotalEarnings(executiveId));
        profile.put("averageRating", this.getAverageRating(executiveId));
        return profile;
    }

    @Transactional
    public void updateProfile(Long executiveId, Map<String, Object> updates) {
        DeliveryExecutive executive = this.getExecutiveById(executiveId);
        if (updates.containsKey("name")) {
            executive.setName((String)updates.get("name"));
        }
        if (updates.containsKey("vehicleType")) {
            // empty if block
        }
        if (updates.containsKey("vehicleNumber")) {
            // empty if block
        }
        this.deliveryExecutiveRepository.save(executive);
        logger.info("Updated profile for executive: {}", executiveId);
    }
}
