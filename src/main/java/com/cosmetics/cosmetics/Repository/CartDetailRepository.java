package com.cosmetics.cosmetics.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cosmetics.cosmetics.Model.Entity.CartDetail;

public interface CartDetailRepository extends JpaRepository<CartDetail, Integer>{

}
