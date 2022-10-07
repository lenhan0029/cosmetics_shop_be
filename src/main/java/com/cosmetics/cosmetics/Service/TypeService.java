package com.cosmetics.cosmetics.Service;

import org.springframework.http.ResponseEntity;

import com.cosmetics.cosmetics.Model.DTO.Request.TypeRequest;

public interface TypeService {

	ResponseEntity<?> createType(TypeRequest dto);
	
	ResponseEntity<?> updateType(int id, TypeRequest dto);
	
	ResponseEntity<?> deleteType(int id);
	
	ResponseEntity<?> getTypeByCategory(int id);
}
