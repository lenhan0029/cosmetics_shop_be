package com.cosmetics.cosmetics.Service;

import org.springframework.http.ResponseEntity;

import com.cosmetics.cosmetics.Model.DTO.Request.OrderRequest;

public interface OrderService {

	ResponseEntity<?> createOrder(OrderRequest dto);
	
}
