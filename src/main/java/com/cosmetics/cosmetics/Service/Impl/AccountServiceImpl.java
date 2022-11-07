package com.cosmetics.cosmetics.Service.Impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cosmetics.cosmetics.Exception.ResourceNotFoundException;
import com.cosmetics.cosmetics.Model.DTO.Request.ChangePasswordRequest;
import com.cosmetics.cosmetics.Model.DTO.Request.EditAccountRequest;
import com.cosmetics.cosmetics.Model.DTO.Response.ResponseModel;
import com.cosmetics.cosmetics.Model.Entity.Account;
import com.cosmetics.cosmetics.Model.Entity.Role;
import com.cosmetics.cosmetics.Repository.AccountRepository;
import com.cosmetics.cosmetics.Repository.RoleRepository;
import com.cosmetics.cosmetics.Service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{

	final AccountRepository accountRepository;
	final RoleRepository roleRepository;
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	public AccountServiceImpl(AccountRepository accountRepository, RoleRepository roleRepository) {
		super();
		this.accountRepository = accountRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	public ResponseEntity<?> ChangePassword(Integer id, ChangePasswordRequest dto) {
		// TODO Auto-generated method stub
		Optional<Account> account = accountRepository.findById(id);
		if(!account.isPresent()) {
			throw new ResourceNotFoundException("Account không tồn tại");
		}
		String encryptPassword = encoder.encode(dto.getNewPassword());
		Account newAccount = account.get();
		if(!BCrypt.checkpw(dto.getOldPassword(),newAccount.getPassword())){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel(
					"Mập khẩu không đúng",404,dto));
		}
		newAccount.setPassword(encryptPassword);
		accountRepository.save(newAccount);
		return ResponseEntity.ok().body(new ResponseModel("Đổi mật khẩu thành công",200));
	}

	@Override
	public ResponseEntity<?> getListAccount(String searchCode, Integer roleId, boolean status, String sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> editAccount(Integer id, EditAccountRequest dto) {
		// TODO Auto-generated method stub
		Optional<Account> account = accountRepository.findById(id);
		if(!account.isPresent()) {
			throw new ResourceNotFoundException("Account không tồn tại");
		}
		Account newAccount = account.get();
		Optional<Role> role = roleRepository.findById(dto.getRoleId());
		if(!role.isPresent()) {
			throw new ResourceNotFoundException("role không tồn tại");
		}
		newAccount.setRole(role.get());
		if(dto.getStatus() == 1) {
			newAccount.setStatus(true);
		}else {
			newAccount.setStatus(false);
		}
		return ResponseEntity.ok(accountRepository.save(newAccount));
	}

	

}
