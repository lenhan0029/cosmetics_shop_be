package com.cosmetics.cosmetics.Service;

import org.springframework.http.ResponseEntity;


public interface CartService {

	ResponseEntity<?> createCart(Integer accountId);
	
}
