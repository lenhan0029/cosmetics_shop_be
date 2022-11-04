package com.cosmetics.cosmetics.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cosmetics.cosmetics.Model.Entity.Account;
import com.cosmetics.cosmetics.Model.Entity.CartDetail;
import com.cosmetics.cosmetics.Model.Entity.UserInformation;

public interface AccountRepository extends JpaRepository<Account, Integer>{

	Optional<Account> findByUserName(String username);
	
//	@Query(value = "SELECT * FROM account a WHERE a.id_information = :id_inform",nativeQuery = true)
	Optional<Account> findByUserInformation(UserInformation userInformation);
//	@Query(value = "SELECT * FROM c cd WHERE cd.id_cart = :cart AND cd.id_product = :product",nativeQuery = true)
//	Optional<CartDetail> findByCartAndProduct(@Param("cart") int cart, @Param("product") int product);
}
