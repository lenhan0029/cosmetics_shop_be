package com.cosmetics.cosmetics.Service;

import org.springframework.http.ResponseEntity;

import com.cosmetics.cosmetics.Model.DTO.Request.VoucherRequest;

public interface VourcherService {
	ResponseEntity<?> create(VoucherRequest voucherDTO);
    ResponseEntity<?> update(VoucherRequest voucherDTO);
    ResponseEntity<?> delete(int id);
    ResponseEntity<?> getByID(int id);
    ResponseEntity<?> getAll();
}
