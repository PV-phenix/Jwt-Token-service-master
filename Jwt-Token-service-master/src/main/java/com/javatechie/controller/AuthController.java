package com.javatechie.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javatechie.config.MessageResponse;
import com.javatechie.dto.AuthRequest;
import com.javatechie.entity.UserInfo;
import com.javatechie.service.JwtService;
import com.javatechie.service.UserInfoService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth/*")
public class AuthController {
	
   @Autowired
    private UserInfoService userInfoservice;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private static final Logger myLogger = LoggerFactory.getLogger(AuthController.class);

    
    @GetMapping("/")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping("/new")
    public String addNewUser(@RequestBody UserInfo userInfo) {
        return userInfoservice.addUser(userInfo);
    }
    
	    
    @GetMapping("/api/auth/me")
	public boolean getAuthMe() {
	//public boolean getAuthMe(@Validated User user,  HttpServletRequest request) {
		//myLogger.debug("Auth me return true");
		return true;
	}
     	
    @PostMapping("/api/auth/login")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) 
        {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }
    
	@PostMapping("/api/auth/register")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<?> UserCreation(@RequestBody AuthRequest authRequest) 
	{
		myLogger.info("start register method for: {}",  authRequest.getUsername());
		
		
		if(userInfoservice.findByUsername(authRequest.getUsername())!=null)
		return ResponseEntity
				.badRequest()
				.body(new MessageResponse("Error: Username is already taken"));
		
		if(userInfoservice.findByEmail(authRequest.getUsername())!=null)
		return ResponseEntity
				.badRequest()
				.body(new MessageResponse("Error: Email is already taken"));
					
		UserInfo userInfo=new UserInfo();

		 userInfo.setRoles("ROLE_USER");
//		if (strRoles==null)
//		{
//			Role userRole = roleService.findByName(ERole.ROLE_USER).orElseThrow(()->new RuntimeException("Error:Role is not found"));
//			roles.add(userRole);
//		}
//		else 
//		{	
//			strRoles.forEach
//				(role->
//					{switch(role.toString()) 
//						{
//							case "ROLE_ADMIN": 	Role adminRole = roleService.findByName(ERole.ROLE_ADMIN).orElseThrow(()->new RuntimeException("Error:Role is not found"));
//											roles.add(adminRole);
//											break;
//							case "ROLE_MOD" : 	Role modRole = roleService.findByName(ERole.ROLE_MODERATOR).orElseThrow(()->new RuntimeException("Error:Role is not found"));
//											roles.add(modRole);
//											break;
//							default		: 	Role userRole = roleService.findByName(ERole.ROLE_USER).orElseThrow(()->new RuntimeException("Error:Role is not found"));
//											roles.add(userRole);
//							break;
//						}
//			
//					}
//				);
//		}
//		
//		userInfo.setRole(roles);
		userInfoservice.saveUser(userInfo);
		
    	return ResponseEntity.ok(new MessageResponse("User registered successfully"));
	}
    

}
