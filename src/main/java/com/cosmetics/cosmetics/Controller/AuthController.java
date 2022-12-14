package com.cosmetics.cosmetics.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cosmetics.cosmetics.Model.DTO.Request.ForgotPassword;
import com.cosmetics.cosmetics.Model.DTO.Request.LoginRequest;
import com.cosmetics.cosmetics.Model.DTO.Request.SendOTP;
import com.cosmetics.cosmetics.Model.DTO.Request.SignupRequest;
import com.cosmetics.cosmetics.Model.DTO.Request.VerifyRequest;
import com.cosmetics.cosmetics.Service.AuthService;

@CrossOrigin(origins = "*", maxAge = 3600)
@EnableAutoConfiguration
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	AuthService authService;
	
	@PostMapping("/login")
	public ResponseEntity<?> Login(@Valid @RequestBody LoginRequest loginRequest) {
		return authService.login(loginRequest);
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest){
		return authService.signup(signupRequest);
	}
	
	@PutMapping("/verify")
	public ResponseEntity<?> verify(@RequestBody VerifyRequest verifyRequest){
		return authService.activeAccount(verifyRequest.getEmail(), verifyRequest.getOtp());
	}
	
	@PutMapping("/forgotpassword")
	public ResponseEntity<?> forgotPassword(@RequestBody ForgotPassword forgotPassword){
		return authService.forgotPassword(forgotPassword);
	}
	
	@PutMapping("/sendotp")
	public ResponseEntity<?> sendOtp(@RequestBody SendOTP otp){
		return authService.sendOTP(otp.getEmail());
	}
}
