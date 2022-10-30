package com.cosmetics.cosmetics.Service;

import org.springframework.http.ResponseEntity;

import com.cosmetics.cosmetics.Model.DTO.Request.AddToCartRequest;

public interface CartDetailService {

	ResponseEntity<?> addToCart(int accountId,int productId, int quantity);
	
	ResponseEntity<?> editCartItem(int accountId, AddToCartRequest dto);
	
	ResponseEntity<?> getListCartItems(int accountId);
	
	ResponseEntity<?> deleteCartItem(Integer accountId, Integer productId);
	
	ResponseEntity<?> deleteAllCartItems(int accountId);
	
	ResponseEntity<?> test(int cartid, int productid);
}
