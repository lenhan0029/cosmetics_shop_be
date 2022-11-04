package com.cosmetics.cosmetics.Model.DTO.Request;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerifyRequest {

	@NotEmpty
	private String email;
	
	@NotEmpty
	private String otp;
}
