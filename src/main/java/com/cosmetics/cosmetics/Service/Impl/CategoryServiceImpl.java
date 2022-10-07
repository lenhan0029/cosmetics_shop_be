package com.cosmetics.cosmetics.Service.Impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cosmetics.cosmetics.Exception.ResourceAlreadyExistException;
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
			throw new ResourceAlreadyExistException("Category đã tồn tại");
		}
		Category newCategory = new Category();
		newCategory.setName(CategoryName);
		return ResponseEntity.ok(categoryRepository.save(newCategory));
	}

	@Override
	public ResponseEntity<?> updateCategory(int id, String CategoryName) {
		// TODO Auto-generated method stub
		if(categoryRepository.findById(id).isEmpty()) {
			throw new ResourceAlreadyExistException("Category không tồn tại");
		}
		Category newCategory = categoryRepository.findById(id).get();
		newCategory.setName(CategoryName);
		return ResponseEntity.ok(categoryRepository.save(newCategory));
	}

	@Override
	public ResponseEntity<?> deleteCategory(int id) {
		// TODO Auto-generated method stub
		if(categoryRepository.findById(id).isEmpty()) {
			throw new ResourceAlreadyExistException("Category không tồn tại");
		}
		if(typeRepository.findByCategory(categoryRepository.findById(id).get()).isPresent()) {
			throw new ResourceAlreadyExistException("Không thể xóa category chứa type");
		}
		categoryRepository.deleteById(id);
		return ResponseEntity.ok("delete success");
	}

	@Override
	public ResponseEntity<?> getAll() {
		// TODO Auto-generated method stub
		return ResponseEntity.ok(categoryRepository.findAll());
	}

}
