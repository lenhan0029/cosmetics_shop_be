package com.cosmetics.cosmetics.Service.Impl;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cosmetics.cosmetics.Model.DTO.Request.AddToCartRequest;
import com.cosmetics.cosmetics.Model.DTO.Response.CartItemResponse;
import com.cosmetics.cosmetics.Model.DTO.Response.ResponseModel;
import com.cosmetics.cosmetics.Model.Entity.Account;
import com.cosmetics.cosmetics.Model.Entity.Cart;
import com.cosmetics.cosmetics.Model.Entity.CartDetail;
import com.cosmetics.cosmetics.Model.Entity.CartKey;
import com.cosmetics.cosmetics.Model.Entity.Product;
import com.cosmetics.cosmetics.Repository.AccountRepository;
import com.cosmetics.cosmetics.Repository.CartDetailRepository;
import com.cosmetics.cosmetics.Repository.CartRepository;
import com.cosmetics.cosmetics.Repository.ProductRepository;
import com.cosmetics.cosmetics.Service.CartDetailService;

@Service
public class CartDetailServiceImpl implements CartDetailService{

	final CartDetailRepository cartDetailRepository;
	final CartRepository cartRepository;
	final AccountRepository accountRepository;
	final ProductRepository productRepository;

	public CartDetailServiceImpl(CartDetailRepository cartDetailRepository, CartRepository cartRepository,
			AccountRepository accountRepository, ProductRepository productRepository) {
		super();
		this.cartDetailRepository = cartDetailRepository;
		this.cartRepository = cartRepository;
		this.accountRepository = accountRepository;
		this.productRepository = productRepository;
	}

	@Override
	public ResponseEntity<?> addToCart(int accountId, AddToCartRequest dto) {
		Optional<Account> acc = accountRepository.findById(accountId);
		if(acc.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseModel("Không tìm thấy tài khoản",404));
		}
		Cart entity;
		Optional<Cart> cart = cartRepository.findByAccount(acc.get());
		CartDetail cartDetail = cartDetailRepository.findByCartAndProduct(accountId, dto.getProductId()).get();
		int productId = dto.getProductId();
		if(!cartDetailRepository.findByCartAndProduct(accountId, productId).isEmpty()) {
//			CartDetail cartDetail = cartDetailRepository.findByCartAndProduct(accountId, dto.getProductId()).get();
//			cartDetail.setQuantity(dto.getQuantity());
//			CartDetail newcd = cartDetailRepository.save(cartDetail);
			return ResponseEntity.ok().body(
					new ResponseModel("Thêm thành công",200));
		}
//		if(cart.isEmpty()) {
//			entity = new Cart();
//			entity.setAccount(acc.get());
//			Cart newCart = cartRepository.save(entity);
//		}else {
//			entity = cart.get();
//		}
//		Optional<Product> protp = productRepository.findById(dto.getProductId());
//		if(protp.isEmpty()) {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//					new ResponseModel("Sản phẩm không tồn tại",404));
//		}
//		CartDetail cartDetail = new CartDetail();
//		cartDetail.setCart(entity);
//		cartDetail.setProduct(protp.get());
//		cartDetail.setQuantity(dto.getQuantity());
//		cartDetailRepository.addToCart(dto.getCartId(),dto.getProductId(),dto.getQuantity());
//		CartDetail newItem = cartDetailRepository.saveAndFlush(cartDetail);
//		CartItemResponse res = new CartItemResponse(entity.getId(),protp.get().getId(),protp.get().getName(),
//				protp.get().getImage(),protp.get().getPrice(),newItem.getQuantity());
		return ResponseEntity.ok().body(
				new ResponseModel("Thêm thành công 2",200,cartDetail.getQuantity()));
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

	@Override
	public ResponseEntity<?> test(int cartid, int productid) {
		// TODO Auto-generated method stub
//		Cart cart = cartRepository.findById(cartid).get();
//		Product product = productRepository.findById(productid).get();
		Optional<CartDetail> cd = cartDetailRepository.findByCartAndProduct(cartid, productid);
//		Optional<CartDetail> cd = cartDetailRepository.findByCartAndProduct();
		return ResponseEntity.ok().body(
				new ResponseModel("thành công",200,cd.get().getQuantity()));
	}

}
