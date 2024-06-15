package com.javatechie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatechie.entity.Product;
import com.javatechie.repository.ProductRepository;

import jakarta.annotation.PostConstruct;

@Service
public class ProductService {

    List<Product> productList = null;

    @Autowired
    private ProductRepository productRepository;

    
    @PostConstruct
    public void loadProductsFromDB() {
//    	productList = (List<Product>) IntStream.rangeClosed(1, 100).boxed()
//               .mapToObj( i -> Product.Builder(
//                        productId(i),
//                        name("product " + i),
//                        qty(new Random().nextInt(10)),
//                        price(new Random().nextInt(5000).build())
//                        .collect(Collectors.toList())));
    }


    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(int id) {
        return productList.stream()
                .filter(product -> product.getProductId() == id)
                .findAny()
                .orElseThrow(() -> new RuntimeException("product " + id + " not found"));
    }



}
