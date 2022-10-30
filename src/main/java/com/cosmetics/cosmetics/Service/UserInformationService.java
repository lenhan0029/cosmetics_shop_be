package com.cosmetics.cosmetics.Service;

import org.springframework.http.ResponseEntity;

import com.cosmetics.cosmetics.Model.DTO.Request.UserInformationRequest;
import com.cosmetics.cosmetics.Model.DTO.Response.UserInformationResponse;


public interface UserInformationService {

	ResponseEntity<?> editUserInformation(Integer idAccount, UserInformationRequest dto); 
}
