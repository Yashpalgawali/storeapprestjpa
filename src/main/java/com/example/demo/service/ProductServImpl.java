package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.GlobalException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.globalconfig.Global;
import com.example.demo.models.Activities;
import com.example.demo.models.Product;
import com.example.demo.repository.ActivityRepository;
import com.example.demo.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service("prodserv")
@RequiredArgsConstructor
public class ProductServImpl implements ProductService {
	
	private final ProductRepository prodrepo;
	private final ActivityRepository actrepo;

	@Override
	public void saveProduct(Product pro) {

		float cgst = pro.getGsttax() / 2;
		float igst = pro.getGsttax();
		
		pro.setCgst_per(cgst);
		pro.setSgst_per(cgst);
		pro.setIgst_per(igst);
		
		Product prod = prodrepo.save(pro);
		
		if(prod!=null) {
			Activities activity = new Activities();
			activity.setActivity("Product "+prod.getProd_name() +" is Saved successfully");
			activity.setActivity_date(Global.DATE_FORMATTER.format(LocalDateTime.now()));
			activity.setActivity_time(Global.TIME_FORMATTER.format(LocalDateTime.now()));
			actrepo.save(activity);
		}
		else {
			Activities activity = new Activities();
			activity.setActivity("Product is not Saved ");
			activity.setActivity_date(Global.DATE_FORMATTER.format(LocalDateTime.now()));
			activity.setActivity_time(Global.TIME_FORMATTER.format(LocalDateTime.now()));
			actrepo.save(activity);
			throw new GlobalException("Product "+pro.getProd_name()+" is not saved");
		}	
		 
	}

	@Override
	public List<Product> getAllProducts() {
		List<Product> prodList = prodrepo.findAll();
		if(prodList.size() > 0)
			return  prodList;
		throw new ResourceNotFoundException("Product ", "product", "product");
	}

	@Override
	public Product getProductById(Long pid) {
		return prodrepo.findById(pid).orElseThrow(() -> new ResourceNotFoundException("Product", "id", ""+pid) );
	}

	@Override
	@Transactional
	public void updateProduct(Product prod) {
		Integer result = prodrepo.updateProduct(prod.getPid(), prod.getProd_name(), prod.getProd_price(), prod.getProd_unit(),
				prod.getProd_model_no(), prod.getProd_hsn(), prod.getGsttax());
		if(result>0) {
			Activities activity = new Activities();
			activity.setActivity("Product "+prod.getProd_name() +" is updated successfully");
			activity.setActivity_date(Global.DATE_FORMATTER.format(LocalDateTime.now()));
			activity.setActivity_time(Global.TIME_FORMATTER.format(LocalDateTime.now()));
			actrepo.save(activity);
		}
		else {
			Activities activity = new Activities();
			activity.setActivity("Product "+prod.getProd_name() +" is not updated successfully");
			activity.setActivity_date(Global.DATE_FORMATTER.format(LocalDateTime.now()));
			activity.setActivity_time(Global.TIME_FORMATTER.format(LocalDateTime.now()));
			actrepo.save(activity);
		}
		throw new GlobalException("Product "+prod.getProd_name()+" is not updated");
	}

}
