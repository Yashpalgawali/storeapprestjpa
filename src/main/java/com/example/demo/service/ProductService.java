package com.example.demo.service;

import java.util.List;

import com.example.demo.models.Product;

public interface ProductService {
	
	public void saveProduct(Product pro);
	
	public List<Product> getAllProducts();
	
	public Product getProductById(Long pid);
	
	public void updateProduct(Product prod);
	
}