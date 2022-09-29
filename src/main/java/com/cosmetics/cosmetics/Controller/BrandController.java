package com.cosmetics.cosmetics.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cosmetics.cosmetics.Service.BrandService;

@CrossOrigin(origins = "*", maxAge = 3600)
@EnableAutoConfiguration
@RestController
@RequestMapping("/brand")
public class BrandController {

	@Autowired
	BrandService brandService;
	
	@PostMapping
	public ResponseEntity<?> createBrand(@RequestBody String brandName){
		return brandService.createBrand(brandName);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteBrand(@PathVariable("id") int id){
		return brandService.deleteBrand(id);
	}
}
