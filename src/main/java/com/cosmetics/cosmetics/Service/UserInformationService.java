package com.cosmetics.cosmetics.Service;

import org.springframework.http.ResponseEntity;

import com.cosmetics.cosmetics.Model.DTO.Request.UserInformationRequest;


public interface UserInformationService {

	ResponseEntity<?> editUserInformation(Integer idAccount, UserInformationRequest dto); 
	
	ResponseEntity<?> getUserInformation(int idAccount);
}
