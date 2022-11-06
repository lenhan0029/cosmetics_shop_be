package com.cosmetics.cosmetics.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cosmetics.cosmetics.Model.DTO.Request.ChangePasswordRequest;
import com.cosmetics.cosmetics.Model.DTO.Request.OrderRequest;
import com.cosmetics.cosmetics.Service.AccountService;
import com.cosmetics.cosmetics.Service.OrderService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	AccountService accountService;
	
	@PutMapping("/{id}")
	public ResponseEntity<?> changePassword(@PathVariable("id") int id,@RequestBody ChangePasswordRequest dto){
		return accountService.ChangePassword(id, dto);
	}
}
