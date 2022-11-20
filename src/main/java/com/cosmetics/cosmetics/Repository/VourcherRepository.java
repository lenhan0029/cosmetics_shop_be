package com.cosmetics.cosmetics.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cosmetics.cosmetics.Model.Entity.Vourcher;

@Repository
public interface VourcherRepository extends JpaRepository<Vourcher, Integer>{

}
