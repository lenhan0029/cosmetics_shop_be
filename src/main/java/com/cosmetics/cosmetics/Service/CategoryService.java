package com.cosmetics.cosmetics.Service;

import org.springframework.http.ResponseEntity;


public interface CategoryService {

	ResponseEntity<?> createCategory(String CategoryName);
}
