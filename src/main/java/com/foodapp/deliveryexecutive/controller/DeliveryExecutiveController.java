package com.foodapp.deliveryexecutive.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

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
import com.foodapp.deliveryexecutive.entity.Order.OrderStatus;
import com.foodapp.deliveryexecutive.service.DeliveryExecutiveService;

@RestController
@RequestMapping("/deliveryExecutive")
public class DeliveryExecutiveController {
	
	private final DeliveryExecutiveService deliveryService;
	
	DeliveryExecutiveController(DeliveryExecutiveService deliveryService){
		this.deliveryService = deliveryService;
		
	}
	
	 @PostMapping("/register")
	    public ResponseEntity<DeliveryRegisterResponse> register(@RequestBody DeliveryRegisterRequest request) {
	        return ResponseEntity.ok(deliveryService.register(request));
	    }
	
	@PostMapping("/login")
	public ResponseEntity<DeliveryLoginResponse> login(@RequestBody DeliveryLoginRequest request)
	{
		return ResponseEntity.ok(deliveryService.login(request));
		
	}
	
	@PostMapping("/{id}/status")
	public ResponseEntity<Void> updateStatus(@PathVariable Long id, @RequestBody DeliveryStatusUpdateRequest request){
		deliveryService.updateStatus(id, request);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/{id}/location")
	public ResponseEntity<Void> updateLocation(@PathVariable Long id, @RequestBody LocationUpdateDTO location){
		deliveryService.updateLiveLocation(id,location);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{id}/dashboard")
	public ResponseEntity<List<OrderSummaryDTO>> getNearbyOrders(@PathVariable Long id){
		
		return ResponseEntity.ok(deliveryService.getNearbyOrders(id));
		
	}
	
	@GetMapping("/order/{orderId}")
	public ResponseEntity<OrderDetailsDTO> getOrderDetails(@PathVariable Long orderId){
		return ResponseEntity.ok(deliveryService.getOrderDetails(orderId));
		
	}

	@PostMapping("/order/{orderId}/accept")
	public ResponseEntity<Void> acceptOrder(@PathVariable Long orderId, @PathVariable Long deliveryExecutiveId){
		deliveryService.acceptOrder(orderId, deliveryExecutiveId);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/order/{oredrId}/pickup")
	public ResponseEntity<Void> pickup(@PathVariable Long orderId, @RequestParam Long executiveId){
		
		deliveryService.confirmPickup(orderId,executiveId);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/order/{orderId}/delivered")
	public ResponseEntity<Void> delivered(@PathVariable Long orderId, @RequestParam Long executiveId, @RequestBody DeliveryProofDTO deliveryproof){
		deliveryService.confirmDelivery(orderId, executiveId, deliveryproof);
		return ResponseEntity.ok().build();	
		}
	
//	@PostMapping("/{id}/archive")
//	public ResponseEntity<List<OrderDetailsDTO>> archives(@PathVariable long id){
//		deliveryService.getArchives(id);
//		return ResponseEntity.ok().build();
//	}
//	
	@GetMapping("/wallet")
	public ResponseEntity<WalletDTO> wallet(@PathVariable Long id){
		return ResponseEntity.ok(deliveryService.getWallet(id));
	}
	
	@GetMapping("/rating")
	public ResponseEntity<List<RatingDTO>> ratings(@PathVariable Long id){
		
		return ResponseEntity.ok(deliveryService.getRatings(id));
	}
	
	
}
