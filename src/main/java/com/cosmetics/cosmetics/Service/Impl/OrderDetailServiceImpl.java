package com.cosmetics.cosmetics.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cosmetics.cosmetics.Model.DTO.Response.OrderDetailResponse;
import com.cosmetics.cosmetics.Model.DTO.Response.ResponseModel;
import com.cosmetics.cosmetics.Repository.OrderDetailRepository;
import com.cosmetics.cosmetics.Repository.OrderRepository;
import com.cosmetics.cosmetics.Service.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService{

	final OrderRepository orderRepository;
	final OrderDetailRepository orderDetailRepository;
	
	public OrderDetailServiceImpl(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository) {
		super();
		this.orderRepository = orderRepository;
		this.orderDetailRepository = orderDetailRepository;
	}

	@Override
	public ResponseEntity<?> getByOrder(int id_order) {
		List<OrderDetailResponse> od = orderDetailRepository.listOrderDetailBySearch(id_order);
		return ResponseEntity.ok().body( 
				new ResponseModel("Thành công",200,od));
	}
	
	
}
