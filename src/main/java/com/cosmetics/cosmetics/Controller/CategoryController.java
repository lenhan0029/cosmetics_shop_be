package com.cosmetics.cosmetics.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
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

import com.cosmetics.cosmetics.Model.DTO.Request.CategoryRequest;
import com.cosmetics.cosmetics.Service.CategoryService;

@CrossOrigin(origins = "*", maxAge = 3600)
@EnableAutoConfiguration
@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	
	@GetMapping
	public ResponseEntity<?> getListCategory(){
		return categoryService.getAll();
	}
	
	@PostMapping
	public ResponseEntity<?> createCategory(@RequestBody CategoryRequest dto){
		return categoryService.createCategory(dto.getName());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateCategory(@PathVariable("id") int id,@RequestBody CategoryRequest dto){
		return categoryService.updateCategory(id, dto.getName());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteBrand(@PathVariable("id") int id){
		return categoryService.deleteCategory(id);
	}
}
