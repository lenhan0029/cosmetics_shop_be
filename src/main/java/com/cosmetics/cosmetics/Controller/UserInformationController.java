package com.cosmetics.cosmetics.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cosmetics.cosmetics.Model.DTO.Request.UserInformationRequest;
import com.cosmetics.cosmetics.Service.UserInformationService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/userInformation")
public class UserInformationController {

	@Autowired
	UserInformationService userInformationService;
	
	@GetMapping
	public ResponseEntity<?> getUserInformation(@RequestParam(name = "accountId",defaultValue = "", required = true) int accountId){
		return userInformationService.getUserInformation(accountId);
	}
	@PutMapping
	public ResponseEntity<?> editUserInformation(@RequestParam(name = "accountId",defaultValue = "", required = true) int accountId,@RequestBody UserInformationRequest dto){
		return userInformationService.editUserInformation(accountId, dto);
	}
}
