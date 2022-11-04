package com.cosmetics.cosmetics.Service.Impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cosmetics.cosmetics.Exception.ResourceAlreadyExistException;
import com.cosmetics.cosmetics.Model.DTO.Response.ResponseModel;
import com.cosmetics.cosmetics.Model.Entity.Category;
import com.cosmetics.cosmetics.Repository.CategoryRepository;
import com.cosmetics.cosmetics.Repository.TypeRepository;
import com.cosmetics.cosmetics.Service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	final CategoryRepository categoryRepository;
	final TypeRepository typeRepository;
	
	public CategoryServiceImpl(CategoryRepository categoryRepository, TypeRepository typeRepository) {
		super();
		this.categoryRepository = categoryRepository;
		this.typeRepository = typeRepository;
	}

	@Override
	public ResponseEntity<?> createCategory(String CategoryName) {
		// TODO Auto-generated method stub
		if(categoryRepository.findByName(CategoryName.toLowerCase()).isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel("danh mục không tồn tại",404,CategoryName));
		}
		Category newCategory = new Category();
		newCategory.setName(CategoryName);
		return ResponseEntity.ok(new ResponseModel("tạo thành công",200,categoryRepository.save(newCategory)));
	}

	@Override
	public ResponseEntity<?> updateCategory(int id, String CategoryName) {
		// TODO Auto-generated method stub
		if(!categoryRepository.findById(id).isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel("danh mục không tồn tại",404,CategoryName));
		}
		Category newCategory = categoryRepository.findById(id).get();
		newCategory.setName(CategoryName);
		return ResponseEntity.ok(new ResponseModel("tạo thành công",200,categoryRepository.save(newCategory)));
	}

	@Override
	public ResponseEntity<?> deleteCategory(int id) {
		// TODO Auto-generated method stub
		if(!categoryRepository.findById(id).isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel("danh mục không tồn tại",404,id));
		}
		if(typeRepository.findByCategory(categoryRepository.findById(id).get()).size() != 0) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseModel("Không thể xóa danh mục chứa loại sản phẩm",409,id));
		}
		categoryRepository.deleteById(id);
		return ResponseEntity.ok(new ResponseModel("xóa thành công",200,id));
	}

	@Override
	public ResponseEntity<?> getAll() {
		// TODO Auto-generated method stub
		return ResponseEntity.ok(new ResponseModel("thành công",200,categoryRepository.findAll()));
	}

}
