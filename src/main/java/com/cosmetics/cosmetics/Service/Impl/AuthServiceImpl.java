package com.cosmetics.cosmetics.Service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cosmetics.cosmetics.Exception.ResourceNotFoundException;
import com.cosmetics.cosmetics.Model.DTO.Request.LoginRequest;
import com.cosmetics.cosmetics.Model.DTO.Request.SignupRequest;
import com.cosmetics.cosmetics.Model.DTO.Response.LoginResponse;
import com.cosmetics.cosmetics.Model.DTO.Response.ResponseModel;
import com.cosmetics.cosmetics.Model.Entity.Account;
import com.cosmetics.cosmetics.Model.Entity.Role;
import com.cosmetics.cosmetics.Model.Entity.UserInformation;
import com.cosmetics.cosmetics.Repository.AccountRepository;
import com.cosmetics.cosmetics.Repository.RoleRepository;
import com.cosmetics.cosmetics.Repository.UserInformationRepository;
import com.cosmetics.cosmetics.Security.JwtUtils;
import com.cosmetics.cosmetics.Security.Service.UserDetailsImpl;
import com.cosmetics.cosmetics.Service.AuthService;

@Service
public class AuthServiceImpl implements AuthService{

	final AuthenticationManager authenticationManager;
	final AccountRepository accountRepository;
	final UserInformationRepository userInformationRepository;
	final PasswordEncoder passwordEncoder;
	final ModelMapper modelMapper;
	final RoleRepository roleRepository;
	final JwtUtils jwtUtils;
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	public AuthServiceImpl(AuthenticationManager authenticationManager, AccountRepository accountRepository,
			UserInformationRepository userInformationRepository, PasswordEncoder passwordEncoder,
			ModelMapper modelMapper, RoleRepository roleRepository, JwtUtils jwtUtils) {
		super();
		this.authenticationManager = authenticationManager;
		this.accountRepository = accountRepository;
		this.userInformationRepository = userInformationRepository;
		this.passwordEncoder = passwordEncoder;
		this.modelMapper = modelMapper;
		this.roleRepository = roleRepository;
		this.jwtUtils = jwtUtils;
	}

	@Override
	public ResponseEntity<?> signup(SignupRequest dto) {
		// TODO Auto-generated method stub
		Account newAccount = modelMapper.map(dto, Account.class);
		newAccount.setPassword(encoder.encode(dto.getPassword()));
		if(dto.getStatus() == 1) {
			newAccount.setStatus(true);
		}else {
			newAccount.setStatus(false);
		}
		Role accountRole = roleRepository.findById(dto.getRoleId()).get();
		newAccount.setRole(accountRole);
		accountRepository.save(newAccount);
		UserInformation newUserInformation = modelMapper.map(dto, UserInformation.class);
		newUserInformation.setAccount(newAccount);
		return ResponseEntity.ok(new ResponseModel("Signup successfull",200));
	}

	@Override
	public ResponseEntity<?> login(LoginRequest dto) {
		// TODO Auto-generated method stub
		Optional<Account> optional = accountRepository.findByUserName(dto.getUsername());
		if(optional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel(
					"Account does not exist",404,dto));
		}
		if(!optional.get().isStatus()== true) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseModel(
					"Account is disable can not Login",200));
		}
		if(!BCrypt.checkpw(dto.getPassword(),optional.get().getPassword())){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel(
					"Password is incorrect",404,dto));
		}
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());
		return ResponseEntity.ok(new ResponseModel("login success",200,new LoginResponse(jwt, userDetails.getId(), userDetails.getUsername(), roles.get(0), userDetails.isStatus())));
		
	}

	
}
