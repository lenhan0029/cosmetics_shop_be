package com.cosmetics.cosmetics.Service;

import org.springframework.http.ResponseEntity;

import com.cosmetics.cosmetics.Model.DTO.Request.AddToCartRequest;

public interface CartDetailService {

	ResponseEntity<?> addToCart(int accountId,int productId, int quantity);
	
	ResponseEntity<?> editCartItem(AddToCartRequest dto);
	
	ResponseEntity<?> getListCartItems(Integer cartId);
	
	ResponseEntity<?> deleteCartItem(Integer cartId, Integer productId);
	
	ResponseEntity<?> deleteAllCartItems(Integer cartId);
	
	ResponseEntity<?> test(int cartid, int productid);
}
