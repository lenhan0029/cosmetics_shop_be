package com.cosmetics.cosmetics.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cosmetics.cosmetics.Model.Entity.Account;
import com.cosmetics.cosmetics.Model.Entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer>{

	Optional<Cart> findByAccount(Account account);
}
