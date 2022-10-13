package com.cosmetics.cosmetics.Model.DTO.Request;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddToCartRequest {
	
	@NotEmpty
	private int cartId;
	
	@NotEmpty
	private int productId;
	
	private int quantity;
}
