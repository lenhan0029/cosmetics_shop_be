package com.cosmetics.cosmetics.Model.DTO.Request;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForgotPassword {

	@NotEmpty
	private String email;
	
	@NotEmpty
	private String otp;
	
	@NotEmpty
	private String new_password;
}
