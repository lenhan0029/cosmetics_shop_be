package com.cosmetics.cosmetics.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cosmetics.cosmetics.Model.Entity.OrderDetail;
import com.cosmetics.cosmetics.Model.Entity.OrderKey;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderKey>{

	@Modifying
	@Query(value = "INSERT INTO order_detail VALUES (:orderId, :productId, :price, :quantity)", nativeQuery = true)
	@Transactional
	int addOrderDetail(@Param("orderId") int orderId, @Param("productId") int productId,@Param("price") int price,@Param("quantity") int quantity);
}
