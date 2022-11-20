package com.cosmetics.cosmetics.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cosmetics.cosmetics.Model.DTO.Request.VoucherRequest;
import com.cosmetics.cosmetics.Service.VourcherService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/vouchers")
public class VourcherController {
    @Autowired
    VourcherService vourcherService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody VoucherRequest dto) {
        return vourcherService.create(dto);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody VoucherRequest dto) {
        return vourcherService.update(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        return vourcherService.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getByID(@PathVariable("id") int id) {
        return vourcherService.getByID(id);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return vourcherService.getAll();
    }
}
