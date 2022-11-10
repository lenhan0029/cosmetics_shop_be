package com.cosmetics.cosmetics.Model.DTO.Response;

import java.sql.Date;

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
	private String gender;
	private Date birthday;
	private String image;
}
