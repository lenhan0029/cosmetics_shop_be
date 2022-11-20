package com.cosmetics.cosmetics.Service;

import org.springframework.http.ResponseEntity;

import com.cosmetics.cosmetics.Model.DTO.Request.OrderRequest;
import com.cosmetics.cosmetics.Model.DTO.Request.ShipDTO;

public interface OrderService {

	ResponseEntity<?> createOrder(OrderRequest dto);
	
	ResponseEntity<?> getListOrderByAccount(int accountId, String statusId, int page);//user
	
	ResponseEntity<?> changeOrderStatus(int orderId, ShipDTO dto);
	
}
