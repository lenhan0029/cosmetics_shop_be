package com.cosmetics.cosmetics.Service;

import org.springframework.http.ResponseEntity;

import com.cosmetics.cosmetics.Model.DTO.Request.OrderRequest;
import com.cosmetics.cosmetics.Model.DTO.Request.ShipDTO;

public interface OrderService {

	ResponseEntity<?> createOrder(OrderRequest dto);
	
	ResponseEntity<?> getAllOrder(int statusId);//admin
	
	ResponseEntity<?> getListOrderByAccount(int accountId);//user
	
	ResponseEntity<?> changeOrderStatus(int orderId, ShipDTO dto);
	
}
