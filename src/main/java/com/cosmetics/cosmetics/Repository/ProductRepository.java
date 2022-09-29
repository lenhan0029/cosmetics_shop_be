package com.cosmetics.cosmetics.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cosmetics.cosmetics.Model.Entity.Brand;
import com.cosmetics.cosmetics.Model.Entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

	Optional<Product> findByBrand(Brand brand);
}
