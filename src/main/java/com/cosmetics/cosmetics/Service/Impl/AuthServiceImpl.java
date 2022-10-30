package com.cosmetics.cosmetics.Service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.cosmetics.cosmetics.Model.Entity.EmailDetails;
import com.cosmetics.cosmetics.Model.Entity.Role;
import com.cosmetics.cosmetics.Model.Entity.UserInformation;
import com.cosmetics.cosmetics.Repository.AccountRepository;
import com.cosmetics.cosmetics.Repository.RoleRepository;
import com.cosmetics.cosmetics.Repository.UserInformationRepository;
import com.cosmetics.cosmetics.Security.JwtUtils;
import com.cosmetics.cosmetics.Security.Service.UserDetailsImpl;
import com.cosmetics.cosmetics.Service.AuthService;
import com.cosmetics.cosmetics.Service.EmailService;

@Service
public class AuthServiceImpl implements AuthService{

	@Autowired
	private EmailService emailService;
	final AuthenticationManager authenticationManager;
	final AccountRepository accountRepository;
	final UserInformationRepository userInformationRepository;
	final PasswordEncoder passwordEncoder;
	final ModelMapper modelMapper;
	final RoleRepository roleRepository;
	final JwtUtils jwtUtils;
	private static final String alpha = "abcdefghijklmnopqrstuvwxyz"; // a-z
	private static final String alphaUpperCase = alpha.toUpperCase(); // A-Z
	private static final String digits = "0123456789"; // 0-9
	private static final String ALPHA_NUMERIC = alpha + alphaUpperCase + digits;
	private static Random generator = new Random();
	
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
		Optional<Account> optional = accountRepository.findByUserName(dto.getUserName());
		if(optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseModel(
					"Tên đăng nhập đã tồn tại",400,dto));
		}
		Account newAccount = modelMapper.map(dto, Account.class);
		newAccount.setPassword(encoder.encode(dto.getPassword()));
		if(dto.getStatus() == 1) {
			newAccount.setStatus(true);
		}else {
			newAccount.setStatus(false);
		}
		Random randomNumber = new Random();
		StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 7; i++) {
            int number = randomNumber(0, ALPHA_NUMERIC.length() - 1);
            char ch = ALPHA_NUMERIC.charAt(number);
            sb.append(ch);
        }
        EmailDetails e = new EmailDetails(dto.getEmail(),sb.toString(),"Test send mail","");
        int status = emailService.sendSimpleMail(e);
        if(status == 0) {
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel(
					"Email không tồn tại",404,dto));
        }
        newAccount.setOtp(sb.toString());
		Role accountRole = roleRepository.findById(dto.getRoleId()).get();
		newAccount.setRole(accountRole);
		Account newAcc = accountRepository.save(newAccount);
		UserInformation newUserInformation = userInformationRepository.save(modelMapper.map(dto, UserInformation.class));
		newAccount.setUserInformation(newUserInformation);
		return ResponseEntity.ok(new ResponseModel("Đăng ký thành công",200,newAcc));
	}

	private int randomNumber(int min, int max) {
		return generator.nextInt((max - min) + 1) + min;
	}

	@Override
	public ResponseEntity<?> login(LoginRequest dto) {
		// TODO Auto-generated method stub
		Optional<Account> optional = accountRepository.findByUserName(dto.getUsername());
		if(!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel(
					"Tài khoản không tồn tại",404,dto));
		}
		if(!optional.get().isStatus()== true) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseModel(
					"Tài khoản đã bị khóa",200));
		}
		if(!BCrypt.checkpw(dto.getPassword(),optional.get().getPassword())){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel(
					"Mập khẩu không đúng",404,dto));
		}
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());
		return ResponseEntity.ok(new ResponseModel("Đăng nhập thành công",200,new LoginResponse(jwt, userDetails.getId(), userDetails.getUsername(), roles.get(0), userDetails.isStatus())));
		
	}

	
}
