package com.cosmetics.cosmetics.Model.DTO.Request;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {

	@NotEmpty
	private String userName;
	@NotEmpty
	private String password;
	@NotEmpty
	private Integer status;
	@NotEmpty
	private Integer roleId;
	@NotEmpty
	private String firstName;
	@NotEmpty
	private String LastName;
	@NotEmpty
	private String address;
	@NotEmpty
	private String phoneNumber;
	@NotEmpty
	private String email;
}
