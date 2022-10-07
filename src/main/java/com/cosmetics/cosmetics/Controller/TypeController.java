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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cosmetics.cosmetics.Model.DTO.Request.TypeRequest;
import com.cosmetics.cosmetics.Service.TypeService;

@CrossOrigin(origins = "*", maxAge = 3600)
@EnableAutoConfiguration
@RestController
@RequestMapping("/type")
public class TypeController {
	
	@Autowired
	TypeService typeService;
	
	@GetMapping
	public ResponseEntity<?> getByCategory(@RequestParam("idCategory") int idCategory){
		return typeService.getTypeByCategory(idCategory);
	}
	
	@PostMapping
	public ResponseEntity<?> createType(@RequestBody TypeRequest dto){
		return typeService.createType(dto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateType(@PathVariable("id") int id, @RequestBody TypeRequest dto){
		return typeService.updateType(id, dto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteType(@PathVariable("id") int id){
		return typeService.deleteType(id);
	}

}
