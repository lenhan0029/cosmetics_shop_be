package com.cosmetics.cosmetics.Repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cosmetics.cosmetics.Model.DTO.Response.AccountResponse;
import com.cosmetics.cosmetics.Model.DTO.Response.ProductResponse;
import com.cosmetics.cosmetics.Model.Entity.Account;
import com.cosmetics.cosmetics.Model.Entity.UserInformation;

public interface AccountRepository extends JpaRepository<Account, Integer>{

	Optional<Account> findByUsername(String username);
	
//	@Query(value = "SELECT * FROM account a WHERE a.id_information = :id_inform",nativeQuery = true)
	Optional<Account> findByUserInformation(UserInformation userInformation);
	
	@Query(value = "select NEW com.cosmetics.cosmetics.Model.DTO.Response.AccountResponse(" +
			"pr.id, pr.username, pr.status, pr.role.id, pr.createTime) " +
			"from Account pr " +
			"where " +
			"lower(pr.username)  like  %:name% " +
			"and pr.role.id in :idrole " +
			"and pr.status in :status ", nativeQuery = false)
	Page<AccountResponse> listAccountBySearch(String name, int[] idrole, boolean[] status, Pageable page);
}
