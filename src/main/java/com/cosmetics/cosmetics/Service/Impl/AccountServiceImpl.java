package com.cosmetics.cosmetics.Service.Impl;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cosmetics.cosmetics.Exception.ResourceNotFoundException;
import com.cosmetics.cosmetics.Model.DTO.Request.ChangePasswordRequest;
import com.cosmetics.cosmetics.Model.DTO.Request.EditAccountRequest;
import com.cosmetics.cosmetics.Model.DTO.Response.AccountResponse;
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
	public ResponseEntity<?> getListAccount(String searchCode, String roleId, String status, String sort, int page) {
		// TODO Auto-generated method stub
		String[] arr_roles = roleId.split("");
		for (String string : arr_roles) {
			String[] arr = {"1","2","3"};
			if(!Arrays.asList(arr).contains(string)) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
						new ResponseModel("Quyền không tồn tại",404));
			}
		}
		String[] arr_status = status.split("");
		for (String string : arr_status) {
			String[] arr = {"0","1"};
			if(!Arrays.asList(arr).contains(string)) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
						new ResponseModel("Trạng thái không tồn tại",404));
			}
		}
		int[] role = Stream.of(arr_roles).mapToInt(Integer::parseInt).toArray();
		boolean[] arrstatus = convert(arr_status);
		Pageable newPage = createPage(page,sort);
		Page<AccountResponse> pageAccount = accountRepository.listAccountBySearch(searchCode, role, arrstatus, newPage);
		if (pageAccount.hasContent())
		{
			return ResponseEntity.ok(new ResponseModel("thành công",200,pageAccount));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel("không tồn tại tài khoản",404));
	}

	public Pageable createPage(int page, String sortType){

		String sType = sortType.toUpperCase();
		Sort sort;

		switch (sType){
			case "ASC":
				sort = Sort.by(Sort.Direction.ASC, "username");
				break;
			case "DESC":
				sort = Sort.by(Sort.Direction.DESC, "username");
				break;
			default:
				throw new ResourceNotFoundException("NO MODE SORT FOUND !");
		}

		return PageRequest.of(page,12,sort);

	}
	public boolean[] convert(String[] bool) {
		boolean[] boolArray = new boolean[bool.length];
		for (int i = 0; i < boolArray.length; i++) {
			if(bool[i].equals("0")) {
				boolArray[i]=false;
			}else {
				boolArray[i]=true;
			}
		}
		return boolArray;
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
