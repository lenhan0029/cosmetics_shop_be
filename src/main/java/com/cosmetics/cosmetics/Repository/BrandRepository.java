package com.cosmetics.cosmetics.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cosmetics.cosmetics.Model.Entity.Brand;

public interface BrandRepository extends JpaRepository<Brand, Integer>{

	Optional<Brand> findByName(String brandName);
	
}
