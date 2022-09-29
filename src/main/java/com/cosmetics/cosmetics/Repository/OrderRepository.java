package com.cosmetics.cosmetics.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cosmetics.cosmetics.Model.Entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

}
