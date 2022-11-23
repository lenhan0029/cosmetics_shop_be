package com.cosmetics.cosmetics.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cosmetics.cosmetics.Model.DTO.Request.ChangePasswordRequest;
import com.cosmetics.cosmetics.Model.DTO.Request.EditAccountRequest;
import com.cosmetics.cosmetics.Service.AccountService;

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
	
	@GetMapping
	public ResponseEntity<?> getAllAccount(
			@RequestParam(name = "name",defaultValue = "%", required = false) String name,
			@RequestParam(name = "role",defaultValue = "123", required = false) String role,
			@RequestParam(name = "status",defaultValue = "10", required = false) String status,
			@RequestParam(name = "page",defaultValue = "0", required = false) int page,
            @RequestParam(name = "sort", defaultValue = "ASC", required = false) String sort
			){
		return accountService.getListAccount(name, role, status, sort, page);
	}
	
	@PutMapping("/admin/{id}")
	public ResponseEntity<?> editAccount(@PathVariable("id") int id,@RequestBody EditAccountRequest dto){
		return accountService.editAccount(id, dto);
	}
}
