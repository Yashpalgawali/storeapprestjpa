package com.example.demo.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Product;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("product")
@CrossOrigin(origins = "*")
public class ProductRestController {

	private ProductService prodserv;

	@Autowired
	public ProductRestController(ProductService prodserv){
		this.prodserv = prodserv;
	}	

	@PostMapping("/")
	public ResponseEntity<Product> saveProduct(@RequestBody Product prod) {
		
		float cgst = prod.getGsttax() / 2;
		float igst = prod.getGsttax();
		
		prod.setCgst_per(cgst);
		prod.setSgst_per(cgst);
		prod.setIgst_per(igst);
		
		Product prd = prodserv.saveProduct(prod);
		if(prd!=null) {
			return new ResponseEntity<Product>(prod, HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<Product>( HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/")
	public ResponseEntity<List<Product>> getAllProducts() {
	
		List<Product> plist =  prodserv.getAllProducts();
		if( plist.size()>0)
			return  new ResponseEntity<List<Product>>(plist, HttpStatus.OK);
		else
			return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id) {
		Product prod = prodserv.getProductById(""+id);
		if(prod!=null)
			return new ResponseEntity<Product>(prod, HttpStatus.OK);
		else
			return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/")
	public ResponseEntity<Product> updateProductById(@RequestBody Product prod) {
		int res = prodserv.updateProduct(prod);
		if(res>0) {
			Product product = prodserv.getProductById(""+prod.getPid());
			return new ResponseEntity<Product>(product, HttpStatus.OK);
		}
		else
			return  new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	}
}
