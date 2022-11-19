package com.cosmetics.cosmetics.Service.Impl;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Calendar;
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

import com.cosmetics.cosmetics.Model.DTO.Request.ForgotPassword;
import com.cosmetics.cosmetics.Model.DTO.Request.LoginRequest;
import com.cosmetics.cosmetics.Model.DTO.Request.SignupRequest;
import com.cosmetics.cosmetics.Model.DTO.Response.LoginResponse;
import com.cosmetics.cosmetics.Model.DTO.Response.ResponseModel;
import com.cosmetics.cosmetics.Model.Entity.Account;
import com.cosmetics.cosmetics.Model.Entity.DeliveryInformation;
import com.cosmetics.cosmetics.Model.Entity.EmailDetails;
import com.cosmetics.cosmetics.Model.Entity.Role;
import com.cosmetics.cosmetics.Model.Entity.UserInformation;
import com.cosmetics.cosmetics.Repository.AccountRepository;
import com.cosmetics.cosmetics.Repository.DeliveryInformationRepository;
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
	final DeliveryInformationRepository deliveryInformationRepository;
	private static final String alpha = "abcdefghijklmnopqrstuvwxyz"; // a-z
	private static final String alphaUpperCase = alpha.toUpperCase(); // A-Z
	private static final String digits = "0123456789"; // 0-9
	private static final String ALPHA_NUMERIC = alpha + alphaUpperCase + digits;
	private static Random generator = new Random();
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	public AuthServiceImpl(AuthenticationManager authenticationManager, AccountRepository accountRepository,
			UserInformationRepository userInformationRepository, PasswordEncoder passwordEncoder,
			ModelMapper modelMapper, RoleRepository roleRepository, JwtUtils jwtUtils, DeliveryInformationRepository deliveryInformationRepository) {
		super();
		this.authenticationManager = authenticationManager;
		this.accountRepository = accountRepository;
		this.userInformationRepository = userInformationRepository;
		this.passwordEncoder = passwordEncoder;
		this.modelMapper = modelMapper;
		this.roleRepository = roleRepository;
		this.jwtUtils = jwtUtils;
		this.deliveryInformationRepository = deliveryInformationRepository;
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
		Random randomNumber = new Random();
		StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 7; i++) {
            int number = randomNumber(0, ALPHA_NUMERIC.length() - 1);
            char ch = ALPHA_NUMERIC.charAt(number);
            sb.append(ch);
        }
		EmailDetails e = new EmailDetails(dto.getEmail(),sb.toString(),"Test send mail","");
		if(dto.getStatus() == 1) {
			newAccount.setStatus(true);
		}else {
			newAccount.setStatus(false);
			int status = emailService.sendSimpleMail(e);
	        if(status == 0) {
	        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel(
						"Email không tồn tại",404,dto));
	        }
	        Timestamp instant= Timestamp.from(Instant.now());
	        newAccount.setCreateTime(instant);
		}
        
        newAccount.setOtp(sb.toString());
		Role accountRole = roleRepository.findById(dto.getRoleId()).get();
		newAccount.setRole(accountRole);
		UserInformation newUserInformation = userInformationRepository.save(modelMapper.map(dto, UserInformation.class));
		newAccount.setUserInformation(newUserInformation);
		Timestamp instant= Timestamp.from(Instant.now());
		newAccount.setCreateTime(instant);
		Account newAcc = accountRepository.save(newAccount);
		DeliveryInformation deliveryInformation = new DeliveryInformation();
		deliveryInformation.setAccount(newAcc);
		deliveryInformation.setAddress(dto.getAddress());
		deliveryInformation.setDefault(true);
		deliveryInformation.setName(dto.getFirstName() + " " + dto.getLastName());
		deliveryInformation.setPhoneNumber(dto.getPhoneNumber());
		deliveryInformationRepository.save(deliveryInformation);
		return ResponseEntity.ok(new ResponseModel("Đăng ký thành công",200,newAcc));
	}

	private int randomNumber(int min, int max) {
		return generator.nextInt((max - min) + 1) + min;
	}

	@Override
	public ResponseEntity<?> login(LoginRequest dto) {
		// TODO Auto-generated method stub
		Optional<Account> optional = accountRepository.findByUserName(dto.getUsername());
		if(optional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel(
					"Tài khoản không tồn tại",404,dto));
		}
		if(!optional.get().isStatus()) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseModel(
					"Tài khoản đã bị khóa",202));
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

	@Override
	public ResponseEntity<?> activeAccount(String email,String code) {
		Optional<UserInformation> userInfor = userInformationRepository.findByEmail(email);
		if(userInfor.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel(
					"Tài khoản không tồn tại 1",404));
		}
		Optional<Account> acc = accountRepository.findByUserInformation(userInfor.get());
		if(acc.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel(
					"Tài khoản không tồn tại 2",404));
		}
		Account newAccount = acc.get();
		if(!newAccount.getOtp().equals(code)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel(
					"OTP không đúng",404));
		}
		Timestamp instant= Timestamp.from(Instant.now());
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(instant.getTime());
		cal.add(Calendar.SECOND, -120);
		instant = new Timestamp(cal.getTime().getTime());
		if(instant.after(newAccount.getCreateTime())) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel(
					"OTP đã hết hạn",404));
		}
		newAccount.setOtp("");
		newAccount.setCreateTime(null);
		newAccount.setStatus(true);
		accountRepository.save(newAccount);
		return ResponseEntity.ok(new ResponseModel("Tài khoản đã được kích hoạt",200));
	}

	@Override
	public ResponseEntity<?> sendOTP(String email) {
		Optional<UserInformation> userInfor = userInformationRepository.findByEmail(email);
		if(userInfor.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel(
					"Tài khoản không tồn tại",404));
		}
		Optional<Account> acc = accountRepository.findByUserInformation(userInfor.get());
		if(acc.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel(
					"Tài khoản không tồn tại",404));
		}
		Random randomNumber = new Random();
		StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 7; i++) {
            int number = randomNumber(0, ALPHA_NUMERIC.length() - 1);
            char ch = ALPHA_NUMERIC.charAt(number);
            sb.append(ch);
        }
        EmailDetails e = new EmailDetails(email,sb.toString(),"Mã xác thực OTP","");
        int status = emailService.sendSimpleMail(e);
        if(status == 0) {
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel(
					"Email không tồn tại",404));
        }
        
        Timestamp instant= Timestamp.from(Instant.now());
        Account newAccount = acc.get();
        newAccount.setCreateTime(instant);
        newAccount.setOtp(sb.toString());
        accountRepository.save(newAccount);
		return ResponseEntity.ok(new ResponseModel("Gửi OTP thành công",200));
	}

	@Override
	public ResponseEntity<?> forgotPassword(ForgotPassword dto) {
		Optional<UserInformation> userInfor = userInformationRepository.findByEmail(dto.getEmail());
		if(userInfor.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel(
					"Tài khoản không tồn tại",404));
		}
		Optional<Account> acc = accountRepository.findByUserInformation(userInfor.get());
		if(acc.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel(
					"Tài khoản không tồn tại",404));
		}
		Account newAccount = acc.get();
		if(!newAccount.getOtp().equals(dto.getOtp())) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel(
					"OTP không đúng",404));
		}
		Timestamp instant= Timestamp.from(Instant.now());
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(instant.getTime());
		cal.add(Calendar.SECOND, -120);
		instant = new Timestamp(cal.getTime().getTime());
		if(instant.after(newAccount.getCreateTime())) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel(
					"OTP đã hết hạn",404));
		}
		newAccount.setOtp("");
		newAccount.setCreateTime(null);
		newAccount.setStatus(true);
		newAccount.setPassword(encoder.encode(dto.getNew_password()));
		accountRepository.save(newAccount);
		return ResponseEntity.ok(new ResponseModel("Mật khẩu mới đã được cập nhật",200));
	}

	
}
