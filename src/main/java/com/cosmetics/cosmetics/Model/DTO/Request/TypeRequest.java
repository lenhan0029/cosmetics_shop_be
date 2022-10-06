package com.cosmetics.cosmetics.Model.DTO.Request;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TypeRequest {

	@NotEmpty
	private String name;
	@NotEmpty
	private Integer id_category;
}
