package com.cosmetics.cosmetics.Service.Impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cosmetics.cosmetics.Model.DTO.Request.AddToCartRequest;
import com.cosmetics.cosmetics.Model.Entity.Cart;
import com.cosmetics.cosmetics.Model.Entity.CartDetail;
import com.cosmetics.cosmetics.Model.Entity.CartKey;
import com.cosmetics.cosmetics.Model.Entity.Product;
import com.cosmetics.cosmetics.Repository.CartDetailRepository;
import com.cosmetics.cosmetics.Repository.CartRepository;
import com.cosmetics.cosmetics.Repository.ProductRepository;
import com.cosmetics.cosmetics.Service.CartDetailService;

@Service
public class CartDetailServiceImpl implements CartDetailService{

	final CartDetailRepository cartDetailRepository;
	final CartRepository cartRepository;
	final ProductRepository productRepository;
	
	
	public CartDetailServiceImpl(CartDetailRepository cartDetailRepository, CartRepository cartRepository,
			ProductRepository productRepository) {
		super();
		this.cartDetailRepository = cartDetailRepository;
		this.cartRepository = cartRepository;
		this.productRepository = productRepository;
	}

	@Override
	public ResponseEntity<?> addToCart(AddToCartRequest dto) {
		// TODO Auto-generated method stub
		CartDetail cartDetail = new CartDetail();
//		CartKey cartKey = new CartKey(dto.getCartId(),dto.getProductId());
//		Cart cart = cartRepository.findById(dto.getCartId()).get();
//		Product product = productRepository.findById(dto.getProductId()).get();
//		cartDetail.setId(cartKey);
//		cartDetail.setCart(cart);
//		cartDetail.setProduct(product);
//		cartDetail.setQuantity(dto.getQuantity());
		cartDetailRepository.addToCart(dto.getCartId(),dto.getProductId(),dto.getQuantity());
		return ResponseEntity.ok().body("Thêm thành công");
	}

	@Override
	public ResponseEntity<?> editCartItem(AddToCartRequest dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> getListCartItems(Integer cartId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> deleteCartItem(Integer cartId, Integer productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> deleteAllCartItems(Integer cartId) {
		// TODO Auto-generated method stub
		return null;
	}

}
