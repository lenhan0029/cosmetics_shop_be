package com.cosmetics.cosmetics.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cosmetics.cosmetics.Model.DTO.Request.CreateProduct;
import com.cosmetics.cosmetics.Service.ProductService;

@CrossOrigin(origins = "*", maxAge = 3600)
@EnableAutoConfiguration
@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;
	
	@GetMapping
	public ResponseEntity<?> listProductBySearch(
			@RequestParam(name = "name",defaultValue = "%", required = false) String name,
			@RequestParam(name = "brand",defaultValue = "%", required = false) String brand,
			@RequestParam(name = "type",defaultValue = "%", required = false) String type,
			@RequestParam(name = "category",defaultValue = "%", required = false) String category,
            @RequestParam(name = "star", defaultValue = "0", required = false) String star,
            @RequestParam(name = "priceFrom", defaultValue = "0", required = false) String from,
            @RequestParam(name = "priceTo", defaultValue = "0", required = false) String to,
            @RequestParam(name = "page", defaultValue = "0", required = false) String page,
            @RequestParam(name = "sortType", defaultValue = "ASC", required = false) String sortType
			){
		int starConverted = Integer.parseInt(star);
		int priceFrom = Integer.parseInt(from);
		int priceTo = Integer.parseInt(to);
		int pageConverted = Integer.parseInt(page);
		return productService.getProductBySearch(name, brand, type, category, starConverted, priceFrom, priceTo, sortType, pageConverted);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getByID(@PathVariable("id") int id){
		return productService.getByID(id);
	}
	
	@PostMapping
	public ResponseEntity<?> createProduct(@RequestBody CreateProduct product){
		return productService.createProduct(product);
	}
}
