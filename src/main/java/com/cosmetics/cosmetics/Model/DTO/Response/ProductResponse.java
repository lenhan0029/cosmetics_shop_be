package com.cosmetics.cosmetics.Model.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductResponse {

	private int id;
	private String name;
	private String image;
	private int price;
	private Float rate;
	private int discount;
	private int status;
	private String type;
	private String brand;
	private String category;
}
