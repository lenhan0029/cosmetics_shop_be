package com.cosmetics.cosmetics.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cosmetics.cosmetics.Model.Entity.Account;
import com.cosmetics.cosmetics.Model.Entity.Order;
import com.cosmetics.cosmetics.Model.Entity.Status;

public interface OrderRepository extends JpaRepository<Order, Integer>{

	List<Order> findByAccount(Account account);
	
	List<Order> findByShipper(Account shipper);
	
	List<Order> findByStatus(Status status);
}
