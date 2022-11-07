package com.cosmetics.cosmetics.Service;

import org.springframework.http.ResponseEntity;

import com.cosmetics.cosmetics.Model.DTO.Request.CreateProduct;

public interface ProductService {

	ResponseEntity<?> getProductBySearch(String name, String brand, String type, 
			String category, int star, int from, int to, String sortType,int page,int discount);
	
	ResponseEntity<?> getByID(int id);
	
	ResponseEntity<?> createProduct(CreateProduct dto);
	
	ResponseEntity<?> updateProduct(int id, CreateProduct dto);
	
	ResponseEntity<?> deleteProduct(int id);
}
