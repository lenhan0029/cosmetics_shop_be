package com.cosmetics.cosmetics.Service;

import org.springframework.http.ResponseEntity;

import com.cosmetics.cosmetics.Model.DTO.Request.LoginRequest;
import com.cosmetics.cosmetics.Model.DTO.Request.SignupRequest;
import com.cosmetics.cosmetics.Model.DTO.Response.LoginResponse;

public interface AuthService {

	ResponseEntity<?> signup(SignupRequest dto);
	
	ResponseEntity<LoginResponse> login(LoginRequest dto);
}
