package com.cosmetics.cosmetics.Model.DTO.Response;

import java.sql.Timestamp;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AccountResponse {

	private int id;
	private String username;
	private boolean status;
	private int role;
	private Date created_date;
}
