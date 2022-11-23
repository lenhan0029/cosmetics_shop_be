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
			"pr.id, pr.name, pr.image, pr.price, pr.star,pr.discount,pr.status, pr.type.name, pr.brand.name, pr.type.category.name) " +
			"from Product pr " +
			"where " +
			"lower(pr.name)  like  %:name% and pr.brand.id in :brand and " +
			"pr.type.id = :type " +
			"and pr.price between :from and :to " + "and pr.discount >= :discount " +
			"and pr.star >= :star ", nativeQuery = false)
	Page<ProductResponse> listProductBySearch(String name, int[] brand, int type, Pageable page ,
			Float star, int from, int to,int discount);
	
	@Query(value = "select NEW com.cosmetics.cosmetics.Model.DTO.Response.ProductResponse(" +
			"pr.id, pr.name, pr.image, pr.price, pr.star,pr.discount,pr.status, pr.type.name, pr.brand.name, pr.type.category.name) " +
			"from Product pr " +
			"where " +
			"lower(pr.name)  like  %:name% and pr.brand.id in :brand " +
			"and pr.price between :from and :to " + "and pr.discount >= :discount " +
			"and pr.star >= :star ", nativeQuery = false)
	Page<ProductResponse> listProductByBrand(String name, int[] brand, Pageable page ,
			Float star, int from, int to,int discount);
	
	@Query(value = "select NEW com.cosmetics.cosmetics.Model.DTO.Response.ProductResponse(" +
			"pr.id, pr.name, pr.image, pr.price, pr.star,pr.discount,pr.status, pr.type.name, pr.brand.name, pr.type.category.name) " +
			"from Product pr " +
			"where " +
			"lower(pr.name)  like  %:name% and " +
			"pr.type.id = :type " +
			"and pr.price between :from and :to " + "and pr.discount >= :discount " +
			"and pr.star >= :star ", nativeQuery = false)
	Page<ProductResponse> listProductByType(String name, int type, Pageable page ,Float star, int from, int to,int discount);
	
	@Query(value = "select NEW com.cosmetics.cosmetics.Model.DTO.Response.ProductResponse(" +
			"pr.id, pr.name, pr.image, pr.price, pr.star,pr.discount,pr.status, pr.type.name, pr.brand.name, pr.type.category.name) " +
			"from Product pr " +
			"where " +
			"lower(pr.name)  like  %:name% "+
			"and pr.price between :from and :to " + "and pr.discount >= :discount " +
			"and pr.star >= :star ", nativeQuery = false)
	Page<ProductResponse> listProductByName(String name, Pageable page ,Float star, int from, int to,int discount);

}
