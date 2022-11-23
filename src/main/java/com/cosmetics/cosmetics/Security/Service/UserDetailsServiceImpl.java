package com.cosmetics.cosmetics.Security.Service;

import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cosmetics.cosmetics.Model.Entity.Account;
import com.cosmetics.cosmetics.Repository.AccountRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final AccountRepository accountRepository;

	public UserDetailsServiceImpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Account acc = accountRepository.findByUsername(userName).orElseThrow(
				() -> new UsernameNotFoundException("User Not Found with -> username: " + userName));
		return UserDetailsImpl.build(acc);
	}

}
