package com.foodapp.deliveryexecutive.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.foodapp.deliveryexecutive.dto.DeliveryLoginRequest;
import com.foodapp.deliveryexecutive.dto.DeliveryLoginResponse;
import com.foodapp.deliveryexecutive.dto.DeliveryProofDTO;
import com.foodapp.deliveryexecutive.dto.DeliveryRegisterRequest;
import com.foodapp.deliveryexecutive.dto.DeliveryRegisterResponse;
import com.foodapp.deliveryexecutive.dto.DeliveryStatusUpdateRequest;
import com.foodapp.deliveryexecutive.dto.LocationUpdateDTO;
import com.foodapp.deliveryexecutive.dto.OrderDetailsDTO;
import com.foodapp.deliveryexecutive.dto.OrderSummaryDTO;
import com.foodapp.deliveryexecutive.dto.RatingDTO;
import com.foodapp.deliveryexecutive.dto.WalletDTO;
import com.foodapp.deliveryexecutive.entity.DeliveryExecutive;
import com.foodapp.deliveryexecutive.entity.Order;
import com.foodapp.deliveryexecutive.entity.Order.OrderStatus;
import com.foodapp.deliveryexecutive.entity.Wallet;
import com.foodapp.deliveryexecutive.entity.Wallet.CustomerRole;
import com.foodapp.deliveryexecutive.repository.DeliveryExecutiveRepository;
import com.foodapp.deliveryexecutive.repository.OrderRepository;
import com.foodapp.deliveryexecutive.repository.RatingRepository;
import com.foodapp.deliveryexecutive.repository.WalletRepository;

import lombok.AllArgsConstructor;

@Service
public class DeliveryExecutiveService {

	private final DeliveryExecutiveRepository delRepo;
	private final OrderRepository orderRepo;
	private final WalletRepository walletRepo;
	private final  RatingRepository ratingRepo;
	
	public DeliveryExecutiveService(DeliveryExecutiveRepository repo, OrderRepository orderRepo, WalletRepository walletRepo, RatingRepository ratingRepo) {
		this.delRepo=repo;
		this.orderRepo=orderRepo;
		this.walletRepo=walletRepo;
		this.ratingRepo=ratingRepo;
	}
	
	public DeliveryRegisterResponse register(DeliveryRegisterRequest request) {

		DeliveryExecutive ex=new DeliveryExecutive();
		ex.setName(request.getName());
		ex.setAadharNo(request.getAadhar());
		ex.setMobile(request.getMobile());
		ex.setPassword(request.getPassword());
		ex.setLicenseNo(request.getLicense());
		ex.setOnline(false);
		delRepo.save(ex);

		DeliveryRegisterResponse res=new DeliveryRegisterResponse();
		res.setExecutiveId(ex.getId());
		res.setMessage("Successfully Registered");
		
		return res;
	}
	public DeliveryLoginResponse login(DeliveryLoginRequest req) {
		
		//Oauth2 login
		DeliveryExecutive ex=delRepo.findByMobile(req.getMobile())
				.orElseThrow(()-> new RuntimeException("Invalid Mobile No"));
		
		DeliveryLoginResponse res=new DeliveryLoginResponse();
		res.setExecutiveId(ex.getId());
		res.setName(ex.getName());
		res.setToken("JWT Token");
		return res;
	}
	
	public void updateStatus(Long id, DeliveryStatusUpdateRequest request) {
        DeliveryExecutive ex = delRepo.findById(id).orElseThrow();
        ex.setOnline(request.isOnline());
        delRepo.save(ex);
    }
	
	public List<OrderSummaryDTO> getNearbyOrders(Long id) {
		//Location services based Implementation for nearby orders
		//further implementation needed
        return orderRepo.findByExecutiveId(id)
                .stream()
                .map(o -> {
                    OrderSummaryDTO dto = new OrderSummaryDTO();
                    dto.setOrderId(o.getId());
                    dto.setAmount(o.getAmount());
                    dto.setOrderStatus(o.getOrderStatus());
                    return dto;
                })
                .toList();
    }
	
	public OrderDetailsDTO getOrderDetails(Long id) {
        Order o = orderRepo.findById(id).orElseThrow();
        OrderDetailsDTO dto = new OrderDetailsDTO();

        dto.setOrderId(o.getId());
        dto.setUserId(o.getUserId());
        dto.setHomeMakerId(o.getHomeMakerId());
        dto.setPickupAddress(o.getPickupAddress());
        dto.setDeliveryAddress(o.getDeliveryAddress());
        dto.setAmount(o.getAmount());

        return dto;
    }
	public void updateLiveLocation(Long id, LocationUpdateDTO dto) {
        DeliveryExecutive ex = delRepo.findById(id).orElseThrow();
        
        ex.setLat(dto.getLat());
        ex.setLng(dto.getLng());
        delRepo.save(ex);
    }
	
	public void acceptOrder(Long exId, Long orderId) {
        Order o = orderRepo.findById(orderId).orElseThrow();
        o.setOrderStatus(OrderStatus.ACCEPTED);
        orderRepo.save(o);
    }
	
	public void confirmPickup(Long exId, Long orderId) {
        Order o = orderRepo.findById(orderId).orElseThrow();
        o.setOrderStatus(OrderStatus.OUTFORDELIVERY);
        orderRepo.save(o);
    }
	
	public void confirmDelivery(long exId, long orderId, DeliveryProofDTO proof) {
		Order o=orderRepo.findById(orderId).orElseThrow();
		if(!o.getCustomerOtp().equals(proof.getCustomerOtp())) {
			throw new RuntimeException("Invalid OTP");
		}
		o.setOrderStatus(OrderStatus.DELIVERED);
		orderRepo.save(o);
		 Wallet w = walletRepo.findByIdAndCustomerRole(exId,CustomerRole.DELIVERYEXECUTIVE)
	                .orElse(new Wallet());
	        w.setId(exId);
	        w.setCustomerRole(CustomerRole.DELIVERYEXECUTIVE);
	        w.setBalance(w.getBalance() + (o.getAmount() * 0.8)); // payment vary related to distance calculate based on location services
	        														//further implementation needed
	        walletRepo.save(w);
	}
	public List<OrderDetailsDTO> getArchives(long id){
		
		 return orderRepo.findByExecutiveIdAndOrderStatus(id, OrderStatus.DELIVERED).stream().map(o->{
			 OrderDetailsDTO oDet=new OrderDetailsDTO();
			 oDet.setOrderId(o.getId());
			 oDet.setUserId(o.getUserId());
			 oDet.setHomeMakerId(o.getHomeMakerId());
			 oDet.setPickupAddress(o.getPickupAddress());
			 oDet.setExecutiveId(id);
			 oDet.setDeliveryAddress(o.getDeliveryAddress());
			 oDet.setOrderStatus(o.getOrderStatus());
			 return oDet;
		 }).toList();
	}
	
	 public WalletDTO getWallet(Long id) {
	        Wallet w = walletRepo.findByIdAndCustomerRole(id,CustomerRole.DELIVERYEXECUTIVE)
	                .orElse(new Wallet());
	        WalletDTO wdto = new WalletDTO();
	        wdto.setBalance(w.getBalance());
	        return wdto;
	    }

	    
	    public List<RatingDTO> getRatings(Long id) {
	        return ratingRepo.findByIdAndCustomerRole(id, CustomerRole.DELIVERYEXECUTIVE)
	                .stream()
	                .map(r -> {
	                    RatingDTO dto = new RatingDTO();
	                    dto.setStars(r.getStars());
	                    dto.setComment(r.getComment());
	                    return dto;
	                })
	                .toList();
	    }
	
}
