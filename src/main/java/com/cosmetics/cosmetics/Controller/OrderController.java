package com.cosmetics.cosmetics.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cosmetics.cosmetics.Model.DTO.Request.OrderRequest;
import com.cosmetics.cosmetics.Model.DTO.Request.ShipDTO;
import com.cosmetics.cosmetics.Service.OrderDetailService;
import com.cosmetics.cosmetics.Service.OrderService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderDetailService orderDetailService;
	
	@PostMapping
	private ResponseEntity<?> order(@RequestBody OrderRequest dto){
		return orderService.createOrder(dto);
	}
	
	@GetMapping
	private ResponseEntity<?> getAllOrder(
			@RequestParam(name = "accountId",defaultValue = "0", required = false) int id_account,
			@RequestParam(name = "status",defaultValue = "1234567", required = false) String id_status,
			@RequestParam(name = "page",defaultValue = "0", required = false) int page
			){
		return orderService.getListOrderByAccount(id_account,id_status, page);
	}
	
	@GetMapping("/{id}/detail")
	private ResponseEntity<?> getItems(@PathVariable("id") int id_order){
		return orderDetailService.getByOrder(id_order);
	}
	
	@PutMapping("/{id}")
	private ResponseEntity<?> updateOrderStatus(@PathVariable("id") int id_order,@RequestBody ShipDTO dto){
		return orderService.changeOrderStatus(id_order, dto);
	}
}
