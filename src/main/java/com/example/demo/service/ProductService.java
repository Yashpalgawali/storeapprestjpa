package com.example.demo.service;

import java.util.List;

import com.example.demo.models.Product;

public interface ProductService {
	
	public Product saveProduct(Product pro);
	
	public List<Product> getAllProducts();
	
	public Product getProductById(String pid);
	
	public int updateProduct(Product prod);
	
}