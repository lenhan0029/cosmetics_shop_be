package com.cosmetics.cosmetics.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cosmetics.cosmetics.Model.Entity.Category;
import com.cosmetics.cosmetics.Model.Entity.Type;

public interface TypeRepository extends JpaRepository<Type, Integer>{

	Optional<Type> findByName(String name);
	
	Optional<Type> findByCategory(Category category);
}
