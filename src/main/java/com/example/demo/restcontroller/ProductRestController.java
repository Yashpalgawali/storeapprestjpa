package com.example.demo.restcontroller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ResponseDto;
import com.example.demo.models.Product;
import com.example.demo.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductRestController {

	private final ProductService prodserv;

	@PostMapping("/")
	public ResponseEntity<ResponseDto> saveProduct(@RequestBody Product prod) {

		return ResponseEntity.status(HttpStatus.CREATED).body(
				new ResponseDto("Product " + prod.getProd_name() + " is created successfully", HttpStatus.CREATED));

	}

	@GetMapping("/")
	public ResponseEntity<List<Product>> getAllProducts() {

		List<Product> plist = prodserv.getAllProducts();

		return new ResponseEntity<List<Product>>(plist, HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id) {
		Product prod = prodserv.getProductById(id);

		return new ResponseEntity<Product>(prod, HttpStatus.OK);

	}

	@PutMapping("/")
	public ResponseEntity<ResponseDto> updateProductById(@RequestBody Product prod) {
		prodserv.updateProduct(prod);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseDto("Product " + prod.getProd_name() + " is updated successfully", HttpStatus.OK));

	}
}
