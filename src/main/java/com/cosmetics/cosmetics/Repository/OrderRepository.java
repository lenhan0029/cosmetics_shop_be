package com.cosmetics.cosmetics.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cosmetics.cosmetics.Model.DTO.Response.OrderResponse;
import com.cosmetics.cosmetics.Model.Entity.Account;
import com.cosmetics.cosmetics.Model.Entity.Order;
import com.cosmetics.cosmetics.Model.Entity.Status;

public interface OrderRepository extends JpaRepository<Order, Integer>{

	List<Order> findByAccount(Account account);
	
//	List<Order> findByShipper(Account shipper);
	
	List<Order> findByStatus(Status status, Pageable page);
	
	@Query(value = "select NEW com.cosmetics.cosmetics.Model.DTO.Response.OrderResponse(" +
			"o.id, o.createdDate, o.updatedDate, o.paidStatus, o.total,o.status.name,o.deliveryInformation.address) " +
			"from Order o " +
			"where " +
			"(o.account.id = :idaccount or o.shipper.id = :idaccount) and " +
			"o.status.id in :idstatus ", nativeQuery = false)
	Page<OrderResponse> listOrderBySearch(int idaccount, int[] idstatus, Pageable page );
}