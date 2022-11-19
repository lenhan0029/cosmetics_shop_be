package com.cosmetics.cosmetics.Service;

import org.springframework.http.ResponseEntity;

public interface BrandService {

	ResponseEntity<?> createBrand(String brandName);  
	
	ResponseEntity<?> updateBrand(int id,String brandName); 
	
	ResponseEntity<?> deleteBrand(Integer id); 
	
	ResponseEntity<?> getAll(); 
}
