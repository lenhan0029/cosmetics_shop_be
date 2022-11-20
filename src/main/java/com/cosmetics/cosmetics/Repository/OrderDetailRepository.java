package com.cosmetics.cosmetics.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cosmetics.cosmetics.Model.DTO.Response.OrderDetailResponse;
import com.cosmetics.cosmetics.Model.DTO.Response.ProductTopSell;
import com.cosmetics.cosmetics.Model.Entity.OrderDetail;
import com.cosmetics.cosmetics.Model.Entity.OrderKey;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderKey>{

	@Modifying
	@Query(value = "INSERT INTO order_detail VALUES (:orderId, :productId, :price, :quantity)", nativeQuery = true)
	@Transactional
	int addOrderDetail(@Param("orderId") int orderId, @Param("productId") int productId,@Param("price") Float price,@Param("quantity") int quantity);
	
	@Query(value = "select NEW com.cosmetics.cosmetics.Model.DTO.Response.OrderDetailResponse(" +
			"od.order.id, od.product.name, od.product.image, od.product.price, od.price,od.quantity) " +
			"from OrderDetail od " +
			"where " +
			"od.order.id = :idorder ", nativeQuery = false)
	List<OrderDetailResponse> listOrderDetailBySearch(int idorder);
	
	@Query(value = "select NEW com.cosmetics.cosmetics.Model.DTO.Response.ProductTopSell(" +
			"od.product.id, od.product.name, od.product.image, od.product.price, "
			+ "od.product.star,od.product.quantity,od.product.discount, sum(od.quantity) ) " +
			"from OrderDetail od " +
			"where od.product.status = 1 " +
			"group by od.product.id, od.product.name, od.product.image, od.product.price, od.product.star,od.product.quantity,od.product.discount", nativeQuery = false)
	Page<ProductTopSell> GetTopTenBestSell(Pageable page);
}
