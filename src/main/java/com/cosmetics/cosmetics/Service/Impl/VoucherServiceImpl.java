package com.cosmetics.cosmetics.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cosmetics.cosmetics.Model.DTO.Request.VoucherRequest;
import com.cosmetics.cosmetics.Model.DTO.Response.ResponseModel;
import com.cosmetics.cosmetics.Model.Entity.Vourcher;
import com.cosmetics.cosmetics.Repository.VourcherRepository;
import com.cosmetics.cosmetics.Service.VourcherService;

@Service
public class VoucherServiceImpl implements VourcherService{

    @Autowired
    VourcherRepository vourcherRepository;

    @Override
    public ResponseEntity<?> create(VoucherRequest voucherDTO) {

        Vourcher voucher = voucherDTO.toVoucher();
        vourcherRepository.save(voucher);
        return ResponseEntity.ok().body(new ResponseModel("Thành công",200));
    }

    @Override
    public ResponseEntity<?> update(VoucherRequest voucherDTO) {
        Vourcher voucher = voucherDTO.toVoucher();
        Vourcher voucherEnity = vourcherRepository.save(voucher);
        return ResponseEntity.ok().body(new ResponseModel("Thành công",200));
    }

    @Override
    public ResponseEntity<?> delete(int id) {
        Vourcher voucher = vourcherRepository.findById(id).get();
        if (voucher.getId() == null) {
            ResponseEntity.badRequest().body(new ResponseModel("Xóa mã giảm giá thât bại",400));
        }

        voucher.setStatus(false);
        vourcherRepository.save(voucher);
        return ResponseEntity.ok().body(new ResponseModel("Thành công",200));
    }

    @Override
    public ResponseEntity<?> getByID(int id) {
        Vourcher voucher = vourcherRepository.findById(id).get();
        if (voucher.getId() == null) {
            ResponseEntity.badRequest().body(new ResponseModel("Xóa mã giảm giá thât bại",400));
        }

        return ResponseEntity.ok().body(new ResponseModel("Thành công",200, voucher));
    }

    @Override
    public ResponseEntity<?> getAll() {
         List<Vourcher> vouchers = vourcherRepository.findAll();
        System.out.println("voucher: "+ vouchers);
        return ResponseEntity.ok().body(new ResponseModel("Thành công",200, vouchers));
    }
    
}
