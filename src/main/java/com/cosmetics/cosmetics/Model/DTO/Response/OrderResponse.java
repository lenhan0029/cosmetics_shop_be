package com.cosmetics.cosmetics.Model.DTO.Response;

import java.util.Date;

import com.cosmetics.cosmetics.Model.Entity.DeliveryInformation;
import com.cosmetics.cosmetics.Model.Entity.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderResponse {

	private int id;
	private Date created_date;
	private Date updated_date;
	private boolean paid_status;
	private Float total;
	private String status;
	private String deliveryInformation;
}
