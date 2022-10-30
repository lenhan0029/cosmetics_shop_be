package com.cosmetics.cosmetics.Model.DTO.Request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateProduct {

	private String name;
	private String image;
	private int price;
	private int rate;
	private int quantity;
	private String description;
	private int id_brand;
	private int id_type;
	private int id_promotion;
	private int status;
}
