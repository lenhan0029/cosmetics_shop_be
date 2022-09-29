package com.cosmetics.cosmetics.Service;

import org.springframework.http.ResponseEntity;

import com.cosmetics.cosmetics.Model.Entity.Cart;

public interface CartService {

	ResponseEntity<Cart> createCart(Integer accountId);
	
}
