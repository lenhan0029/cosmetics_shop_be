package com.cosmetics.cosmetics.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cosmetics.cosmetics.Model.Entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>{

	Optional<Account> findByUserName(String username);
}
