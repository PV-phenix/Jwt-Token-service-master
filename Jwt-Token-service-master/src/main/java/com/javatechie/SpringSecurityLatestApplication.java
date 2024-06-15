package com.javatechie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.javatechie.entity.Product;
import com.javatechie.entity.UserInfo;
import com.javatechie.service.ProductService;
import com.javatechie.service.UserInfoService;

import jakarta.transaction.Transactional;

@SpringBootApplication
public class SpringSecurityLatestApplication implements CommandLineRunner{

	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private ProductService productService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityLatestApplication.class, args);
	}
	@Override
	@Transactional
	public void run(String... args) throws Exception {
		Iterable<UserInfo> users = userInfoService.getUsersInfos();
		users.forEach(user -> System.out.println(user.getName()));
		
		Iterable<Product> products = productService.getProducts();
		products.forEach(product -> System.out.println(product.getName() +" "+ product.getPrice()));
		
	}

}
