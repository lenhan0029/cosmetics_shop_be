package com.cosmetics.cosmetics.Service.Impl;

import java.util.Collection;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cosmetics.cosmetics.Exception.ResourceAlreadyExistException;
import com.cosmetics.cosmetics.Exception.ResourceNotFoundException;
import com.cosmetics.cosmetics.Model.DTO.Request.TypeRequest;
import com.cosmetics.cosmetics.Model.DTO.Response.ResponseListModel;
import com.cosmetics.cosmetics.Model.DTO.Response.ResponseModel;
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
		Type newType = new Type();
		newType.setName(dto.getName());
		if(!categoryRepository.findById(dto.getId_category()).isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel("Danh mục không tồn tại",404,dto));
		}
		if(typeRepository.findByName(dto.getName().toLowerCase()).isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel("Loại sản phẩm không tồn tại",404,dto));
		}
		Category catetype = categoryRepository.findById(dto.getId_category()).get();
		newType.setCategory(catetype);
		return ResponseEntity.ok(new ResponseModel("Tạo thành công",200,typeRepository.save(newType)));
	}

	@Override
	public ResponseEntity<?> updateType(int id, TypeRequest dto) {
		// TODO Auto-generated method stub
		Optional<Type> oldType = typeRepository.findById(id);
		if(typeRepository.findByName(dto.getName().toLowerCase()).isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel("Loại sản phẩm không tồn tại",404,dto));
		}
		if(!categoryRepository.findById(dto.getId_category()).isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel("Danh mục không tồn tại",404,dto));
		}
		Type newType = oldType.get();
		newType.setName(dto.getName());
		newType.setCategory(categoryRepository.findById(dto.getId_category()).get());
		return ResponseEntity.ok(new ResponseModel("Thay đổi thành công",200,typeRepository.save(newType)));
	}

	@Override
	public ResponseEntity<?> deleteType(int id) {
		// TODO Auto-generated method stub
		if(!typeRepository.findById(id).isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel("Loại sản phẩm không tồn tại",404,id));
		}
		if(productRepository.findByType(typeRepository.findById(id).get()).isPresent()){
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseModel("Không thể xóa loại sản phẩm có sản phẩm",409,id));
		}
		typeRepository.deleteById(id);
		return ResponseEntity.ok(new ResponseModel("Xóa thành công",200,id));
	}

	@Override
	public ResponseEntity<?> getTypeByCategory(int id) {
		// TODO Auto-generated method stub
		if(!categoryRepository.findById(id).isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel("Danh mục không tồn tại",404,id));
		}
		Collection<Type> optional = typeRepository.findByCategory(categoryRepository.findById(id).get());
		return ResponseEntity.ok(new ResponseListModel("Tạo thành công",200,optional.toArray()));
	}

}
