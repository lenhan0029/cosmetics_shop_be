package com.cosmetics.cosmetics.Model.DTO.Response;

import java.sql.Array;
import java.util.Collection;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseListModel {

	String message;
	int status;
	Object[] data;
	
}
