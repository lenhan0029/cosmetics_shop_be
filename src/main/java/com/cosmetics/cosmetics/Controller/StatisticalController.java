package com.cosmetics.cosmetics.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cosmetics.cosmetics.Service.StatisticalService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/statistical")
public class StatisticalController {

	@Autowired
	StatisticalService statisticalService;
	
	@GetMapping("/topten")
	public ResponseEntity<?> getTopTen(){
		return statisticalService.getTopSell();
	}
}
