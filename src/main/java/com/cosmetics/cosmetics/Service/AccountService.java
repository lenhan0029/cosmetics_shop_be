package com.cosmetics.cosmetics.Service;

import org.springframework.http.ResponseEntity;

import com.cosmetics.cosmetics.Model.DTO.Request.ChangePasswordRequest;
import com.cosmetics.cosmetics.Model.DTO.Request.EditAccountRequest;

public interface AccountService {
	
	ResponseEntity<?> ChangePassword(Integer id, ChangePasswordRequest dto);
	
	ResponseEntity<?> editAccount(Integer id, EditAccountRequest dto);
	
	ResponseEntity<?> getListAccount(String searchCode, Integer roleId, boolean status, String sort);
	
	
}
