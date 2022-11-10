package com.cosmetics.cosmetics.Repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cosmetics.cosmetics.Model.DTO.Response.ProductResponse;
import com.cosmetics.cosmetics.Model.Entity.Brand;
import com.cosmetics.cosmetics.Model.Entity.Product;
import com.cosmetics.cosmetics.Model.Entity.Type;

public interface ProductRepository extends JpaRepository<Product, Integer>{

	Optional<Product> findByBrand(Brand brand);
	
	Optional<Product> findByType(Type type);
	
	@Query(value = "select max(price) from Product", nativeQuery = true)
	int findMaxPrice();
	
	@Query(value = "select NEW com.cosmetics.cosmetics.Model.DTO.Response.ProductResponse(" +
			"pr.id, pr.name, pr.image, pr.price, pr.star,pr.discount,pr.status) " +
			"from Product pr " +
			"where " +
			"(lower(pr.name)  like  %:name% and lower(pr.brand.name)  like %:brand% and " +
			"lower(pr.type.name)  like  %:type% and lower(pr.type.category.name)  like %:category%) " +
			"and pr.price between :from and :to " + "and pr.discount >= :discount " +
			"and pr.star >= :star ", nativeQuery = false)
	Page<ProductResponse> listProductBySearch(String name, String brand, String type, String category, Pageable page ,
			Float star, int from, int to,int discount);
}
