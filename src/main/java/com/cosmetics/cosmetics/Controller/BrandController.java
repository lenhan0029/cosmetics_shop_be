package com.cosmetics.cosmetics.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cosmetics.cosmetics.Model.DTO.Request.BrandRequest;
import com.cosmetics.cosmetics.Service.BrandService;

@CrossOrigin(origins = "*", maxAge = 3600)
@EnableAutoConfiguration
@RestController
@RequestMapping("/brand")
public class BrandController {

	@Autowired
	BrandService brandService;
	@GetMapping
	public ResponseEntity<?> getAlLBrand(){
		return brandService.getAll();
	}
	@PostMapping
	public ResponseEntity<?> createBrand(@RequestBody BrandRequest dto){
		return brandService.createBrand(dto.getName());
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> updateBrand(@PathVariable("id") int id,@RequestBody BrandRequest dto){
		return brandService.updateBrand(id, dto.getName());
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteBrand(@PathVariable("id") int id){
		return brandService.deleteBrand(id);
	}
}
