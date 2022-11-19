package com.cosmetics.cosmetics.Model.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetail {

	private int id;
	private String name;
	private String image;
	private int price;
	private Float rate;
	private int quantity;
	private String description;
	private int Status;
	private String brand;
	private String type;
	private String category;
	private int discount;
}
