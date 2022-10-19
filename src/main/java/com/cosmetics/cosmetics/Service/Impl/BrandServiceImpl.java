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
		// TODO Auto-generated method stub
		if(brandRepository.findByName(brandName.toLowerCase()).isPresent()) {
//			throw new ResourceAlreadyExistException("Brand đã tồn tại");
			return ResponseEntity.badRequest().body("brand đã tồn tại");
		}
		Brand newBrand = new Brand();
		newBrand.setName(brandName);
		return ResponseEntity.ok(new ResponseModel("Thêm thành công",200,brandRepository.save(newBrand)));
	}

	@Override
	public ResponseEntity<?> deleteBrand(Integer id) {
		if(brandRepository.findById(id).isEmpty()) {
			throw new ResourceNotFoundException("Brand không tồn tại");
		}
		if(productRepository.findByBrand(brandRepository.findById(id).get()).isPresent()) {
			throw new ResourceAlreadyExistException("Không thể xóa brand có sản phẩm");
		}
		brandRepository.deleteById(id);
		return ResponseEntity.ok("delete success");
	}

	@Override
	public ResponseEntity<?> updateBrand(int id, String brandName) {
		if(brandRepository.findById(id).isEmpty()) {
			throw new ResourceNotFoundException("Brand không tồn tại");
		}
		Brand newBrand = brandRepository.findById(id).get();
		newBrand.setName(brandName);
		return ResponseEntity.ok(brandRepository.save(newBrand));
	}

	@Override
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(new ResponseModel("thành công",200,brandRepository.findAll()));
	}


	
}
