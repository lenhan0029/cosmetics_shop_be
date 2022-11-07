package com.cosmetics.cosmetics.Model.DTO.Request;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {

	
	@NotEmpty
	private int accountId;
	
	@NotEmpty
	private List<ItemOrder> data;
	
	@NotEmpty
	private int id_delivery;
	
	private int id_voucher;
	
	@NotEmpty
	private int id_status;
	
	private boolean paid_status;
	
}
