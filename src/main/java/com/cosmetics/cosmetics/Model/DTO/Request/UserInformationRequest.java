package com.cosmetics.cosmetics.Model.DTO.Request;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInformationRequest {

	private String firstName;
	private String lastName;
	private String address;
	private String phoneNumber;
	private String gender;
	private String image;
	private Date birthday;
}
