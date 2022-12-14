package com.cosmetics.cosmetics.Model.DTO.Request;


import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordRequest {
	
	@NotEmpty
	private String oldPassword;
	
	@NotEmpty
	private String newPassword;
}
