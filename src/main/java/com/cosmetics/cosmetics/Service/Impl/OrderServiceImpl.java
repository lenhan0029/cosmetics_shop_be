package com.cosmetics.cosmetics.Service.Impl;

import java.sql.Date;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.cosmetics.cosmetics.Model.DTO.Request.ItemOrder;

import com.cosmetics.cosmetics.Model.DTO.Request.OrderRequest;
import com.cosmetics.cosmetics.Model.DTO.Response.ResponseModel;
import com.cosmetics.cosmetics.Model.Entity.Account;
import com.cosmetics.cosmetics.Model.Entity.DeliveryInformation;
import com.cosmetics.cosmetics.Model.Entity.Order;
import com.cosmetics.cosmetics.Model.Entity.OrderDetail;
import com.cosmetics.cosmetics.Model.Entity.OrderKey;

import com.cosmetics.cosmetics.Model.Entity.Status;
import com.cosmetics.cosmetics.Model.Entity.Vourcher;
import com.cosmetics.cosmetics.Repository.AccountRepository;
import com.cosmetics.cosmetics.Repository.DeliveryInformationRepository;
import com.cosmetics.cosmetics.Repository.OrderDetailRepository;
import com.cosmetics.cosmetics.Repository.OrderRepository;
import com.cosmetics.cosmetics.Repository.ProductRepository;

import com.cosmetics.cosmetics.Repository.StatusRepository;
import com.cosmetics.cosmetics.Repository.VourcherRepository;
import com.cosmetics.cosmetics.Service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{

	final OrderRepository orderRepository;
	final OrderDetailRepository orderDetailRepository;
	final AccountRepository accountRepository;
	final StatusRepository statusRepository;
	final VourcherRepository vourcherRepository;
	final DeliveryInformationRepository deliveryInformationRepository;
	final ProductRepository productRepository;

	public OrderServiceImpl(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository,
			AccountRepository accountRepository, StatusRepository statusRepository,
			VourcherRepository vourcherRepository, DeliveryInformationRepository deliveryInformationRepository,
			ProductRepository productRepository) {
		super();
		this.orderRepository = orderRepository;
		this.orderDetailRepository = orderDetailRepository;
		this.accountRepository = accountRepository;
		this.statusRepository = statusRepository;
		this.vourcherRepository = vourcherRepository;
		this.deliveryInformationRepository = deliveryInformationRepository;
		this.productRepository = productRepository;

	}


	@Override
	public ResponseEntity<?> createOrder(OrderRequest dto) {
		Optional<Account> acc = accountRepository.findById(dto.getAccountId());
		Optional<Vourcher> vou = vourcherRepository.findById(dto.getId_voucher());
		Optional<Status> sta = statusRepository.findById(dto.getId_status());
		Optional<DeliveryInformation> delinf = deliveryInformationRepository.findById(dto.getId_delivery());
		
		if(acc.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseModel("Tài khoản không tồn tại",404));
		}
		if(dto.getId_voucher() != 0 && vou.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseModel("Mã khuyến mãi không tồn tại",404));
		}
		if(sta.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseModel("Trạng thái không hợp lệ",404));
		}
		if(delinf.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseModel("thông tin giao hàng không tồn tại",404));
		}
		java.util.Date date = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		
		Order order = new Order();
		order.setAccount(acc.get());
		order.setCreatedDate(sqlDate);
		order.setPaidStatus(dto.isPaid_status());
		order.setDeliveryInformation(delinf.get());
		if(vou.isPresent()) {
			if(vou.get().getExpiredDate().after(sqlDate)) {
				return ResponseEntity.status(HttpStatus.ACCEPTED).body(
						new ResponseModel("Mã giảm giá đã hết hạn",202));
			}
			order.setVourcher(vou.get());
		}
		order.setStatus(sta.get());
		Order newOrder = orderRepository.save(order);
		for (ItemOrder item : dto.getData()) {
			orderDetailRepository.addOrderDetail(newOrder.getId(), item.getProductId(), item.getPrice(), item.getQuantity());
			
		}
		return ResponseEntity.ok().body(new ResponseModel("Thành công",200));

	}

}
