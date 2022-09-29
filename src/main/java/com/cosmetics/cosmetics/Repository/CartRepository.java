package com.cosmetics.cosmetics.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cosmetics.cosmetics.Model.Entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer>{

}
