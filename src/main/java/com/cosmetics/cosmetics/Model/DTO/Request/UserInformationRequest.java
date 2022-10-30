package com.cosmetics.cosmetics.Model.DTO.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInformationRequest {

	private String firstName;
	private String lastName;
	private String address;
	private String phoneNumber;
	private String image;
}
