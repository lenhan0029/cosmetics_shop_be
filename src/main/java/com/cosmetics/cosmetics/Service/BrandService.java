package com.cosmetics.cosmetics.Service;

import org.springframework.http.ResponseEntity;

import com.cosmetics.cosmetics.Model.Entity.Brand;

public interface BrandService {

	ResponseEntity<?> createBrand(String brandName);  
	
	ResponseEntity<?> updateBrand(int id,String brandName); 
	
	ResponseEntity<?> deleteBrand(Integer id); 
	
	ResponseEntity<?> getAll(); 
}
