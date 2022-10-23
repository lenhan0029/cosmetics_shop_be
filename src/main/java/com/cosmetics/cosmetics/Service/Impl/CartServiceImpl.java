package com.cosmetics.cosmetics.Service.Impl;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cosmetics.cosmetics.Exception.ResourceNotFoundException;
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
		if(!account.isPresent()) {
			throw new ResourceNotFoundException("Account không tồn tại");
		}
		Cart cart = new Cart();
		cart.setAccount(account.get());
		return ResponseEntity.ok(cartRepository.save(cart));
	}

	
}
