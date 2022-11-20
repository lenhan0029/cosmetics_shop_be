package com.cosmetics.cosmetics.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cosmetics.cosmetics.Model.DTO.Response.ResponseModel;
import com.cosmetics.cosmetics.Model.Entity.Vourcher;
import com.cosmetics.cosmetics.Repository.VourcherRepository;
import com.cosmetics.cosmetics.Service.VourcherService;

@Service
public class VoucherServiceImpl implements VourcherService{

	@Autowired
	private VourcherRepository voucherRepository;
	
	@Override
	public ResponseEntity<?> getAll() {
		List<Vourcher> vouchers = voucherRepository.findAll();
		return ResponseEntity.ok().body(
				new ResponseModel("thành công",200,vouchers));
	}

	
}
