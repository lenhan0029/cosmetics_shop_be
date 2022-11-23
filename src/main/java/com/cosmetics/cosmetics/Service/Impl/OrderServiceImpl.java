package com.cosmetics.cosmetics.Service.Impl;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.cosmetics.cosmetics.Model.DTO.Request.ItemOrder;

import com.cosmetics.cosmetics.Model.DTO.Request.OrderRequest;
import com.cosmetics.cosmetics.Model.DTO.Request.ShipDTO;
import com.cosmetics.cosmetics.Model.DTO.Response.OrderResponse;
import com.cosmetics.cosmetics.Model.DTO.Response.ResponseModel;
import com.cosmetics.cosmetics.Model.Entity.Account;
import com.cosmetics.cosmetics.Model.Entity.DeliveryInformation;
import com.cosmetics.cosmetics.Model.Entity.Order;
import com.cosmetics.cosmetics.Model.Entity.Product;
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
		
		for (ItemOrder item : dto.getData()) {
			Product product = productRepository.findById(item.getProductId()).get();
			if(product.getQuantity() < item.getQuantity()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
						new ResponseModel("Không đủ số lượng mua",404));
			}
			
		}
		Order order = new Order();
		order.setAccount(acc.get());
		order.setCreatedDate(sqlDate);
		order.setTotal(dto.getTotal());
		order.setPaidStatus(dto.isPaid_status());
		order.setDeliveryInformation(delinf.get());
		if(vou.isPresent()) {
			if(vou.get().getExpiredDate().after(sqlDate)) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
						new ResponseModel("Mã giảm giá đã hết hạn",404));
			}
			order.setVourcher(vou.get());
		}
		order.setStatus(sta.get());
		Order newOrder = orderRepository.save(order);
		for (ItemOrder item : dto.getData()) {
			orderDetailRepository.addOrderDetail(newOrder.getId(), item.getProductId(), item.getPrice(), item.getQuantity());
			Product product = productRepository.findById(item.getProductId()).get();
			product.setQuantity(item.getQuantity());
			productRepository.save(product);
			
		}
		return ResponseEntity.ok().body(new ResponseModel("Thành công",200));

	}


//	@Override
//	public ResponseEntity<?> getAllOrder(int statusId, int page) {
//		Pageable pageItems = PageRequest.of(page, 12,Sort.by("createdDate"));
//		if(statusId == 0) {
//			Page<Order> orders = orderRepository.findAll(pageItems);
//			if(orders.isEmpty()) {
//				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//						new ResponseModel("Không tìm thấy đơn hàng",404));
//			}
//			return ResponseEntity.ok().body(
//					new ResponseModel("Danh sánh đơn hàng",200,orders));
//		}
//		Optional<Status> status = statusRepository.findById(statusId);
//		if(status.isEmpty()) {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//					new ResponseModel("Không tìm thấy trạng thái",404));
//		}
//		List<Order> orders = orderRepository.findByStatus(status.get(),pageItems);
//		if(orders.isEmpty()) {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//					new ResponseModel("Không tìm thấy đơn hàng",404));
//		}
//		return ResponseEntity.ok().body(
//				new ResponseModel("Danh sánh đơn hàng",200,orders));
//	}


	@Override
	public ResponseEntity<?> getListOrderByAccount(int accountId, String statusId, int page) {
		String[] temp = statusId.split("");
		for (String string : temp) {
			String[] arr = {"1","2","3","4","5","6","7"};
			if(!Arrays.asList(arr).contains(string)) {
				System.out.println(temp.toString());
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
						new ResponseModel("Trạng thái không hợp lệ",404));
			}
		}
		int[] status = Stream.of(temp).mapToInt(Integer::parseInt).toArray();
		Pageable pageItems = PageRequest.of(page, 12,Sort.by("createdDate"));
		Optional<Account> account = accountRepository.findById(accountId);
		if(accountId != 0 && account.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseModel("Không tìm thấy tài khoản",404));
		}
		if(!account.isEmpty() && account.get().getRole().getId() == 1) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseModel("Người quản lý không có đơn hàng",404));
		}
		Page<OrderResponse> orders = orderRepository.listOrderBySearch(accountId, status, pageItems);
		return ResponseEntity.ok().body(
				new ResponseModel("Danh sánh đơn hàng",200,orders));
	}

	@Override
	public ResponseEntity<?> changeOrderStatus(int orderId, ShipDTO dto) {
		java.util.Date date = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		
		Optional<Order> order = orderRepository.findById(orderId);
		if(order.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseModel("Đơn hàng không tồn tại",404));
		}
		Optional<Account> account = accountRepository.findById(dto.getId_account());
		if(account.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseModel("Tài khoản không tồn tại",404));
		}
		Optional<Status> status = statusRepository.findById(dto.getId_status());
		if(status.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseModel("Trạng thái không hợp lệ",404));
		}
		Order oldOrder = order.get();
//		if(account.get().getRole().getId() == 3) {
//			if(oldOrder.getShipper() == null) {
//				oldOrder.setShipper(account.get());
//				oldOrder.setStatus(status.get());
//				oldOrder.setUpdatedDate(sqlDate);
//			}
//			oldOrder.setStatus(status.get());
//			if(status.get().getId() == 5) {
//				oldOrder.setPaidStatus(true);
//			}
//		}
		if(account.get().getRole().getId() == 2) {
			if(oldOrder.getStatus().getId() != 1 && status.get().getId() == 7) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body(
						new ResponseModel("Không thể hủy đơn hàng đã xác nhận",409));
			}
			oldOrder.setStatus(status.get());
			oldOrder.setUpdatedDate(sqlDate);
		}
		if(account.get().getRole().getId() == 1) {
			oldOrder.setStatus(status.get());
			oldOrder.setUpdatedDate(sqlDate);
		}
		Order newOrder = orderRepository.save(oldOrder);
		return ResponseEntity.ok().body(
				new ResponseModel("Đã chuyển trạng thái",200,newOrder));
	}

}
