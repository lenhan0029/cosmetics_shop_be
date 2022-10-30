package com.cosmetics.cosmetics.Model.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInformationResponse {

	private String firstName;
	private String lastName;
	private String email;
	private String address;
	private String phoneNumber;
	private String image;
}
