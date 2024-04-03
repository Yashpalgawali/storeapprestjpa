package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Product;
import com.example.demo.repository.ProductRepository;

@Service("prodserv")
public class ProductServImpl implements ProductService {

	@Autowired
	ProductRepository prodrepo;
	
	@Override
	public Product saveProduct(Product pro) {
		return prodrepo.save(pro);
	}

	@Override
	public List<Product> getAllProducts() {
		return  prodrepo.findAll();
	}

	@Override
	public Product getProductById(String pid) {
		Long prid = Long.parseLong(pid);
		try {
			return prodrepo.findById(prid).get();
		}
		catch(Exception e) {
			return null;
		}
	}

	@Override
	public int updateProduct(Product prod) {
		return prodrepo.updateProduct(prod.getPid(), prod.getProd_name(), prod.getProd_price(), prod.getProd_unit(),
				prod.getProd_model_no(), prod.getProd_hsn(), prod.getGsttax());
	}

}
