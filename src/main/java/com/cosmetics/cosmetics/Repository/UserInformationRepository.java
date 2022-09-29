package com.cosmetics.cosmetics.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cosmetics.cosmetics.Model.Entity.UserInformation;

public interface UserInformationRepository extends JpaRepository<UserInformation, Integer>{

}
