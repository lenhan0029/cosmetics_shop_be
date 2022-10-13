package com.cosmetics.cosmetics.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cosmetics.cosmetics.Model.Entity.CartDetail;
import com.cosmetics.cosmetics.Model.Entity.CartKey;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, CartKey>{

	@Modifying
	@Query(value = "INSERT INTO cart_detail VALUES (:cartId, :productId, :quantity)", nativeQuery = true)
	@Transactional
	void addToCart(@Param("cartId") int cartId, @Param("productId") int productId,@Param("quantity") int quantity);
}
