package com.cosmetics.cosmetics.Service.Impl;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cosmetics.cosmetics.Exception.ResourceNotFoundException;
import com.cosmetics.cosmetics.Model.DTO.Response.ResponseModel;
import com.cosmetics.cosmetics.Model.Entity.Account;
import com.cosmetics.cosmetics.Model.Entity.Cart;
import com.cosmetics.cosmetics.Repository.AccountRepository;
import com.cosmetics.cosmetics.Repository.CartRepository;
import com.cosmetics.cosmetics.Service.CartService;

@Service
public class CartServiceImpl implements CartService{

	final CartRepository cartRepository;
	final AccountRepository accountRepository;
	
	public CartServiceImpl(CartRepository cartRepository, AccountRepository accountRepository) {
		super();
		this.cartRepository = cartRepository;
		this.accountRepository = accountRepository;
	}

	@Override
	public ResponseEntity<?> createCart(Integer accountId) {
		// TODO Auto-generated method stub
		Optional<Account> account = accountRepository.findById(accountId);
		if(account.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseModel("Không tìm thấy tài khoản",404));
		}
		Cart cart = new Cart();
		cart.setAccount(account.get());
		Cart newCart = cartRepository.save(cart);
		return ResponseEntity.ok().body(new ResponseModel("Tạo thành công",200,newCart.getId()));
	}

	
}
