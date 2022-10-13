package com.cosmetics.cosmetics.Service.Impl;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cosmetics.cosmetics.Exception.ResourceAlreadyExistException;
import com.cosmetics.cosmetics.Exception.ResourceNotFoundException;
import com.cosmetics.cosmetics.Model.DTO.Request.TypeRequest;
import com.cosmetics.cosmetics.Model.Entity.Category;
import com.cosmetics.cosmetics.Model.Entity.Type;
import com.cosmetics.cosmetics.Repository.CategoryRepository;
import com.cosmetics.cosmetics.Repository.ProductRepository;
import com.cosmetics.cosmetics.Repository.TypeRepository;
import com.cosmetics.cosmetics.Service.TypeService;

@Service
public class TypeServiceImpl implements TypeService{

	final TypeRepository typeRepository;
	final CategoryRepository categoryRepository;
	final ProductRepository productRepository;

	public TypeServiceImpl(TypeRepository typeRepository, CategoryRepository categoryRepository,
			ProductRepository productRepository) {
		super();
		this.typeRepository = typeRepository;
		this.categoryRepository = categoryRepository;
		this.productRepository = productRepository;
	}

	@Override
	public ResponseEntity<?> createType(TypeRequest dto) {
		// TODO Auto-generated method stub
		if(typeRepository.findByName(dto.getName().toLowerCase()).isPresent()) {
			throw new ResourceAlreadyExistException("Type đã tồn tại");
		}
		Type newType = new Type();
		newType.setName(dto.getName());
		if(categoryRepository.findById(dto.getId_category()).isEmpty()) {
			throw new ResourceNotFoundException("Category không tồn tại");
		}
		Category catetype = categoryRepository.findById(dto.getId_category()).get();
		newType.setCategory(catetype);
		return ResponseEntity.ok(typeRepository.save(newType));
	}

	@Override
	public ResponseEntity<?> updateType(int id, TypeRequest dto) {
		// TODO Auto-generated method stub
		Optional<Type> oldType = typeRepository.findById(id);
		if(oldType.isEmpty()) {
			throw new ResourceNotFoundException("Type không tồn tại");
		}
		if(typeRepository.findByName(dto.getName().toLowerCase()).isPresent()) {
			throw new ResourceAlreadyExistException("Type đã tồn tại");
		}
		if(categoryRepository.findById(dto.getId_category()).isEmpty()) {
			throw new ResourceNotFoundException("Category không tồn tại");
		}
		Type newType = oldType.get();
		newType.setName(dto.getName());
		newType.setCategory(categoryRepository.findById(dto.getId_category()).get());
		return ResponseEntity.ok(typeRepository.save(newType));
	}

	@Override
	public ResponseEntity<?> deleteType(int id) {
		// TODO Auto-generated method stub
		if(typeRepository.findById(id).isEmpty()) {
			throw new ResourceNotFoundException("Type không tồn tại");
		}
		if(productRepository.findByType(typeRepository.findById(id).get()).isPresent()){
			throw new ResourceAlreadyExistException("Không thể xóa type chứa product");
		}
		typeRepository.deleteById(id);
		return ResponseEntity.ok("delete success");
	}

	@Override
	public ResponseEntity<?> getTypeByCategory(int id) {
		// TODO Auto-generated method stub
		if(categoryRepository.findById(id).isEmpty()) {
			throw new ResourceNotFoundException("Category không tồn tại");
		}
		return ResponseEntity.ok(typeRepository.findByCategory(categoryRepository.findById(id).get()));
	}

}
