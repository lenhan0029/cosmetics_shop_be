package com.cosmetics.cosmetics.Model.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemResponse {

	private int cartId;
	private int productId;
	private String name;
	private String image;
	private int price;
	private int quantity;
	private int discount;
	private int status;
	
}
