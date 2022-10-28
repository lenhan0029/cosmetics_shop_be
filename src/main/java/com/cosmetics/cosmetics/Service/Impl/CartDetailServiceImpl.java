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
	public ResponseEntity<?> addToCart(int accountId, int productId, int quantity) {
		Optional<Account> acc = accountRepository.findById(accountId);
		if(acc.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseModel("Không tìm thấy tài khoản",404));
		}
		Cart entity;
		Optional<Cart> cart = cartRepository.findByAccount(acc.get());
//		CartDetail cartDetail = cartDetailRepository.findByCartAndProduct(accountId, productId).get();
		
		if(cart.isEmpty()) {
			Cart newCart = new Cart();
			newCart.setAccount(acc.get());
			entity = cartRepository.save(newCart);
		}else {
			if(cartDetailRepository.findByCartAndProduct(cart.get().getId(), productId).isPresent()) {
				CartDetail cartDetail = cartDetailRepository.findByCartAndProduct(cart.get().getId(), productId).get();
				cartDetail.setQuantity(cartDetail.getQuantity() + quantity);
				CartDetail newcd = cartDetailRepository.saveAndFlush(cartDetail);
				return ResponseEntity.ok().body(
						new ResponseModel("Thêm thành công",200,newcd.getQuantity()));
			}
			entity = cart.get();
		}
		Optional<Product> protp = productRepository.findById(productId);
		if(protp.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseModel("Sản phẩm không tồn tại",404));
		}
		int b = cartDetailRepository.addToCart(entity.getId(),productId,quantity);
		if(b != 0) {
			return ResponseEntity.ok().body(
					new ResponseModel("Thêm thành công 2",200));
		}
		return ResponseEntity.ok().body(
				new ResponseModel("Thêm thất bại",500));
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
