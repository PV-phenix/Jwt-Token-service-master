package com.javatechie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javatechie.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	 public List<Product> findAll();

}
