package com.cosmetics.cosmetics.Service.Impl;

import org.springframework.stereotype.Service;

import com.cosmetics.cosmetics.Repository.UserInformationRepository;

@Service
public class userInformationServiceImpl {

	final UserInformationRepository userInformationRepository;

	public userInformationServiceImpl(UserInformationRepository userInformationRepository) {
		super();
		this.userInformationRepository = userInformationRepository;
	}
	
	
}
