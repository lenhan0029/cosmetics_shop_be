package com.cosmetics.cosmetics.Model.DTO.Response;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseModel {

	String message;
	int status;
	Object data;
	
	public ResponseModel(String message, int status) {
		super();
		this.message = message;
		this.status = status;
	}
	
	
}
