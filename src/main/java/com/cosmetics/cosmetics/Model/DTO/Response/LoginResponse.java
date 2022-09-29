package com.cosmetics.cosmetics.Model.DTO.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {

	private String token;
	private String type = "Bearer";
	private int id;
	private String username;
	private String roles;

	private boolean status;

	public LoginResponse(String token, int id, String username, String roles, boolean status) {
		this.token = token;
		this.type = "Bearer";
		this.id = id;
		this.username = username;
		this.roles = roles;
		this.status = status;
	}
}
