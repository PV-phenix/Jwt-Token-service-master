package com.javatechie.repository;

import com.javatechie.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
    
	
	public Optional<UserInfo> findByName(String username);
	
	
	public Optional<UserInfo> findByEmail(String username);
    
    public List<UserInfo> findAll();
    
	@SuppressWarnings("unchecked")
	public UserInfo save(UserInfo userInfo);

	
	
	
}
