package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.globalconfig.Global;
import com.example.demo.models.Activities;
import com.example.demo.models.Product;
import com.example.demo.repository.ActivityRepository;
import com.example.demo.repository.ProductRepository;

@Service("prodserv")
public class ProductServImpl implements ProductService {
	
	private final ProductRepository prodrepo;
	private final ActivityRepository actrepo;

	public ProductServImpl(ProductRepository prodrepo, ActivityRepository actrepo) {
		super();
		this.prodrepo = prodrepo;
		this.actrepo = actrepo;
	}

	@Override
	public Product saveProduct(Product pro) {
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
		}	
		return prod;
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
		return result;
	}

}
