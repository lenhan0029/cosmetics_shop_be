package com.cosmetics.cosmetics.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cosmetics.cosmetics.Model.Entity.OrderDetail;
import com.cosmetics.cosmetics.Model.Entity.OrderKey;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderKey>{

}
