package com.cosmetics.cosmetics.Model.DTO.Request;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemOrder {

	@NotEmpty
	private int productId;
	
	@NotEmpty
	private int quantity;
	
	@NotEmpty
	private Float price;
}
