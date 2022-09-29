package com.cosmetics.cosmetics.Service;

import org.springframework.http.ResponseEntity;

import com.cosmetics.cosmetics.Model.Entity.Brand;

public interface BrandService {

	ResponseEntity<Brand> createBrand(String brandName);  
	
	ResponseEntity<?> deleteBrand(Integer id); 
}
