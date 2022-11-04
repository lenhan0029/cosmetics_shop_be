package com.cosmetics.cosmetics.Service.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cosmetics.cosmetics.Exception.ResourceAlreadyExistException;
import com.cosmetics.cosmetics.Exception.ResourceNotFoundException;
import com.cosmetics.cosmetics.Model.DTO.Response.ResponseModel;
import com.cosmetics.cosmetics.Model.Entity.Brand;
import com.cosmetics.cosmetics.Repository.BrandRepository;
import com.cosmetics.cosmetics.Repository.ProductRepository;
import com.cosmetics.cosmetics.Service.BrandService;

@Service
public class BrandServiceImpl implements BrandService{
	
	final BrandRepository brandRepository;
	final ProductRepository productRepository;
	final ModelMapper modelMapper;
	

	public BrandServiceImpl(BrandRepository brandRepository, ProductRepository productRepository,
			ModelMapper modelMapper) {
		super();
		this.brandRepository = brandRepository;
		this.productRepository = productRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public ResponseEntity<?> createBrand(String brandName) {
		if(brandRepository.findByName(brandName.toLowerCase()).isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseModel("thương hiệu đã tồn tại",409,brandName));
		}
		Brand newBrand = new Brand();
		newBrand.setName(brandName);
		return ResponseEntity.ok(new ResponseModel("Thêm thành công",200,brandRepository.save(newBrand)));
	}

	@Override
	public ResponseEntity<?> deleteBrand(Integer id) {
		if(!brandRepository.findById(id).isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel("thương hiệu không tồn tại",404,id));
		}
		if(productRepository.findByBrand(brandRepository.findById(id).get()).isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseModel("Không thể xóa thương hiệu có sản phẩm",409,id));
		}
		brandRepository.deleteById(id);
		return ResponseEntity.ok(new ResponseModel("xóa thành công",200));
	}

	@Override
	public ResponseEntity<?> updateBrand(int id, String brandName) {
		if(!brandRepository.findById(id).isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel("thương hiệu không tồn tại",404,id));
		}
		Brand newBrand = brandRepository.findById(id).get();
		newBrand.setName(brandName);
		return ResponseEntity.ok(new ResponseModel("thay đổi thành công",200,brandRepository.save(newBrand)));
	}

	@Override
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(new ResponseModel("thành công",200,brandRepository.findAll()));
	}


	
}
