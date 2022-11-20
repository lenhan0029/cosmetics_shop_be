package com.cosmetics.cosmetics.Model.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderDetailResponse {

	private int id_order;
	private String product_name;
	private String product_image;
	private int cost;
	private Float price;
	private int quantity;
}
