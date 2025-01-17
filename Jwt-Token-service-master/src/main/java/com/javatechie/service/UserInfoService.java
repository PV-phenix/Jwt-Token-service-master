package com.javatechie.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.javatechie.entity.UserInfo;
import com.javatechie.repository.UserInfoRepository;



@Service
public class UserInfoService {
	
	@Autowired
    private UserInfoRepository userInfoRepository;
	   
	private final PasswordEncoder passwordEncoder;
	
    UserInfoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
	
	public List<UserInfo> getUsersInfos() {
		return userInfoRepository.findAll();
	}
	
    public String addUser(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfoRepository.save(userInfo);
        return "user added to system ";
    }

	public Optional<UserInfo> findByUsername(String username) {
		// TODO Auto-generated method stub
		return userInfoRepository.findByName(username);
	}

	public Optional<UserInfo> findByEmail(String username) {
		// TODO Auto-generated method stub
		
		return userInfoRepository.findByEmail(username);
	}
	
    public String saveUser(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfoRepository.save(userInfo);
        return "user saved to system ";
    }



}
