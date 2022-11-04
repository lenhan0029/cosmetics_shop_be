package com.cosmetics.cosmetics.Service;

import org.springframework.http.ResponseEntity;

import com.cosmetics.cosmetics.Model.DTO.Request.LoginRequest;
import com.cosmetics.cosmetics.Model.DTO.Request.SignupRequest;

public interface AuthService {

	ResponseEntity<?> signup(SignupRequest dto);
	
	ResponseEntity<?> login(LoginRequest dto);
	
	ResponseEntity<?> activeAccount(String email,String code);
	
	ResponseEntity<?> sendOTP(String email);
}
