package com.cosmetics.cosmetics.Repository;

import java.util.Optional;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cosmetics.cosmetics.Model.Entity.Cart;
import com.cosmetics.cosmetics.Model.Entity.CartDetail;
import com.cosmetics.cosmetics.Model.Entity.CartKey;
import com.cosmetics.cosmetics.Model.Entity.Product;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, CartKey>{
	
	@Query(value = "SELECT * FROM cart_detail cd WHERE cd.id_cart = :cart AND cd.id_product = :product",nativeQuery = true)
	Optional<CartDetail> findByCartAndProduct(@Param("cart") int cart, @Param("product") int product);
	
	@Query(value = "SELECT * FROM cart_detail cd where cd.id_cart = :cart",nativeQuery = true)
	List<CartDetail> findByCart(@Param("cart") int cart);

	@Modifying
	@Query(value = "INSERT INTO cart_detail VALUES (:cartId, :productId, :quantity)", nativeQuery = true)
	@Transactional
	int addToCart(@Param("cartId") int cartId, @Param("productId") int productId,@Param("quantity") int quantity);
	
	@Modifying
	@Query(value = "UPDATE cart_detail SET quantity = :quantity WHERE id_cart = :cartId AND id_product = :productId", nativeQuery = true)
	@Transactional
	int editCartItem(@Param("cartId") int cartId, @Param("productId") int productId,@Param("quantity") int quantity);
	
	@Modifying
	@Query(value = "DELETE FROM cart_detail WHERE id_cart = :cartId AND id_product = :productId", nativeQuery = true)
	@Transactional
	int deleteCartItem(@Param("cartId") int cartId, @Param("productId") int productId);
	
	@Modifying
	@Query(value = "DELETE FROM cart_detail WHERE id_cart = :cartId", nativeQuery = true)
	@Transactional
	int deleteAllCartItem(@Param("cartId") int cartId);
}
