package com.cosmetics.cosmetics.Service.Impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cosmetics.cosmetics.Model.DTO.Request.UserInformationRequest;
import com.cosmetics.cosmetics.Model.DTO.Response.ResponseModel;
import com.cosmetics.cosmetics.Model.DTO.Response.UserInformationResponse;
import com.cosmetics.cosmetics.Model.Entity.Account;
import com.cosmetics.cosmetics.Model.Entity.UserInformation;
import com.cosmetics.cosmetics.Repository.AccountRepository;
import com.cosmetics.cosmetics.Repository.UserInformationRepository;
import com.cosmetics.cosmetics.Service.UserInformationService;

@Service
public class userInformationServiceImpl implements UserInformationService{

	final UserInformationRepository userInformationRepository;
	final AccountRepository accountRepository;
	final ModelMapper modelMapper;

	public userInformationServiceImpl(UserInformationRepository userInformationRepository,
			AccountRepository accountRepository, ModelMapper modelMapper) {
		super();
		this.userInformationRepository = userInformationRepository;
		this.accountRepository = accountRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public ResponseEntity<?> editUserInformation(Integer idAccount, UserInformationRequest dto) {
		Optional<Account> acc = accountRepository.findById(idAccount);
		if(acc.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel(
					"Tài khoản không tồn tại",404));
		}
		Optional<UserInformation> optional = userInformationRepository.findById(acc.get().getId());
		if(optional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel(
					"Thông tin tài khoản không tồn tại",404));
		}
		UserInformation userInfo = optional.get();
		userInfo.setFirstName(dto.getFirstName());
		userInfo.setLastName(dto.getLastName());
		userInfo.setAddress(dto.getAddress());
		userInfo.setPhoneNumber(dto.getPhoneNumber());
		userInfo.setImage(dto.getImage());
		userInfo.setBirthday(dto.getBirthday());
		userInfo.setGender(dto.getGender());
		UserInformation newUserInfo = userInformationRepository.save(userInfo);
		UserInformationResponse userRes = modelMapper.map(newUserInfo, UserInformationResponse.class);
		return ResponseEntity.ok().body(
				new ResponseModel("Thay đổi thành công",200,userRes));
	}

	@Override
	public ResponseEntity<?> getUserInformation(int idAccount) {
		Optional<Account> acc = accountRepository.findById(idAccount);
		if(acc.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel(
					"Tài khoản không tồn tại",404));
		}
		Optional<UserInformation> optional = userInformationRepository.findById(acc.get().getId());
		if(optional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel(
					"Thông tin tài khoản không tồn tại",404));
		}
		UserInformationResponse userRes = modelMapper.map(optional.get(), UserInformationResponse.class);
		return ResponseEntity.ok().body(
				new ResponseModel("Lấy thông tin thành công",200,userRes));
	}
	
	
}
