package com.cosmetics.cosmetics.Model.DTO.Request;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditAccountRequest {

	@NotEmpty
	private int roleId;
	
	@NotEmpty
	private int status;
}
