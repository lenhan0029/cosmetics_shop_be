package com.cosmetics.cosmetics.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cosmetics.cosmetics.Model.DTO.Request.AddToCartRequest;
import com.cosmetics.cosmetics.Service.CartDetailService;

@CrossOrigin(origins = "*", maxAge = 3600)
@EnableAutoConfiguration
@RestController
@RequestMapping("/cartdetail")
public class CartDetailController {

	@Autowired
	private CartDetailService cartDetailService;
	
	@PostMapping
	private ResponseEntity<?> addToCart(@RequestParam(name = "account",defaultValue = "0", required = true) String account
			,@RequestBody AddToCartRequest dto){
		int accountConverted = Integer.parseInt(account);
		return cartDetailService.addToCart(accountConverted,dto.getProductId(),dto.getQuantity());
	}
	@GetMapping
	private ResponseEntity<?> test(
			@RequestParam(name = "cart",defaultValue = "0", required = false) String cart,
			@RequestParam(name = "product",defaultValue = "0", required = false) String product){
		int cartConverted = Integer.parseInt(cart);
		int productConverted = Integer.parseInt(product);
		return cartDetailService.test(cartConverted, productConverted);
	}
}
