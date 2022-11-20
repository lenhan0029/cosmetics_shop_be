package com.cosmetics.cosmetics.Service.Impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cosmetics.cosmetics.Model.DTO.Response.ProductTopSell;
import com.cosmetics.cosmetics.Model.DTO.Response.ResponseModel;
import com.cosmetics.cosmetics.Repository.OrderDetailRepository;
import com.cosmetics.cosmetics.Service.StatisticalService;

@Service
public class StatisticalServiceImpl implements StatisticalService{

	final OrderDetailRepository orderDetailRepository;
	
	public StatisticalServiceImpl(OrderDetailRepository orderDetailRepository) {
		super();
		this.orderDetailRepository = orderDetailRepository;
	}

	@Override
	public ResponseEntity<?> getTopSell() {
		Pageable pageable = PageRequest.of(0,10);
		Page<ProductTopSell> list = orderDetailRepository.GetTopTenBestSell(pageable);
		return ResponseEntity.ok().body(
				new ResponseModel("Thành công",200,list));
	}

}
