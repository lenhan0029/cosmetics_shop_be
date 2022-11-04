package com.cosmetics.cosmetics.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cosmetics.cosmetics.Model.Entity.Account;
import com.cosmetics.cosmetics.Model.Entity.CartDetail;
import com.cosmetics.cosmetics.Model.Entity.UserInformation;

public interface UserInformationRepository extends JpaRepository<UserInformation, Integer>{

	Optional<UserInformation> findByEmail(String email);
	
	@Query(value = "select * from user_information u where u.id_account = :idaccount", nativeQuery = true)
	Optional<UserInformation> findByAccount(int idaccount);
	
//	@Query(value = "SELECT * FROM user_information u WHERE u.email = :email",nativeQuery = true)
//	Optional<UserInformation> findByEmail(@Param("email") String email);
}
