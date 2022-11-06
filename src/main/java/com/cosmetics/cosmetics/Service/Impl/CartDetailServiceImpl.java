package com.cosmetics.cosmetics.Service.Impl;

import java.util.ArrayList;
import java.util.List;
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
						new ResponseModel("Thêm thành công",200));
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
					new ResponseModel("Thêm thành công",200));
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
				new ResponseModel("Thêm thất bại",500));
	}

	@Override
	public ResponseEntity<?> editCartItem(int accountId, AddToCartRequest dto) {
		Optional<Account> accOptional = accountRepository.findById(accountId);
		if(accOptional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseModel("Không tìm thấy tài khoản",404));
		}
		Optional<Cart> cart = cartRepository.findByAccount(accOptional.get());
		if(cart.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseModel("Không tìm thấy giỏ hàng",404));
		}
		Optional<Product> protp = productRepository.findById(dto.getProductId());
		if(protp.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseModel("Sản phẩm không tồn tại",404));
		}
		if(!cartDetailRepository.findByCartAndProduct(cart.get().getId(), dto.getProductId()).isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseModel("Sản phẩm không tồn tại trong giỏ hàng",404));
		}
		int b = cartDetailRepository.editCartItem(cart.get().getId(),dto.getProductId(),dto.getQuantity());
		if(b != 0) {
			return ResponseEntity.ok().body(
					new ResponseModel("Cập nhật thành công",200));
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
				new ResponseModel("Cập nhật thất bại",500));
	}

	@Override
	public ResponseEntity<?> getListCartItems(int accountId) {
		Optional<Account> accOptional = accountRepository.findById(accountId);
		if(accOptional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseModel("Không tìm thấy tài khoản",404));
		}
		Optional<Cart> cart = cartRepository.findByAccount(accOptional.get());
		if(cart.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseModel("Không tìm thấy giỏ hàng",404));
		}
		List<CartDetail> list = cartDetailRepository.findByCart(cart.get().getId());
		List<CartItemResponse> reslist = new ArrayList<>();
		for(CartDetail item : list) {
			CartItemResponse cartItem = new CartItemResponse();
			cartItem.setCartId(item.getCart().getId());
			cartItem.setProductId(item.getProduct().getId());
			cartItem.setName(item.getProduct().getName());
			cartItem.setImage(item.getProduct().getImage());
			cartItem.setPrice(item.getProduct().getPrice());
			cartItem.setQuantity(item.getQuantity());
			cartItem.setStatus(item.getProduct().getStatus());
			cartItem.setDiscount(item.getProduct().getDiscount());
			reslist.add(cartItem);
		}
		return ResponseEntity.ok().body(new ResponseModel("Thành công",200,reslist));
	}

	@Override
	public ResponseEntity<?> deleteCartItem(Integer accountId, Integer productId) {
		Optional<Account> accOptional = accountRepository.findById(accountId);
		if(accOptional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseModel("Không tìm thấy tài khoản",404));
		}
		Optional<Cart> cart = cartRepository.findByAccount(accOptional.get());
		if(cart.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseModel("Không tìm thấy giỏ hàng",404));
		}
		if(!cartDetailRepository.findByCartAndProduct(cart.get().getId(), productId).isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseModel("Sản phẩm không tồn tại trong giỏ hàng",404));
		}
		int rs = cartDetailRepository.deleteCartItem(accountId, productId);
		if(rs != 0) {
			return ResponseEntity.ok().body(
					new ResponseModel("Xóa thành công",200));
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
				new ResponseModel("Xóa thất bại",500));
	}

	@Override
	public ResponseEntity<?> deleteAllCartItems(int accountId) {
		Optional<Account> accOptional = accountRepository.findById(accountId);
		if(accOptional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseModel("Không tìm thấy tài khoản",404));
		}
		Optional<Cart> cart = cartRepository.findByAccount(accOptional.get());
		if(cart.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseModel("Không tìm thấy giỏ hàng",404));
		}
		int rs = cartDetailRepository.deleteAllCartItem(accountId);
		if(rs != 0) {
			return ResponseEntity.ok().body(
					new ResponseModel("Xóa thành công",200));
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
				new ResponseModel("Xóa thất bại",500));
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
