package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.globalconfig.Global;
import com.example.demo.models.Activities;
import com.example.demo.models.StoreDetails;
import com.example.demo.repository.ActivityRepository;
import com.example.demo.repository.StoreRepository;

@Service("storeserv")
public class StoreServImpl implements StoreService {

	private final StoreRepository storerepo;
	private final ActivityRepository actrepo;
	
	public StoreServImpl(StoreRepository storerepo, ActivityRepository actrepo) {
		super();
		this.storerepo = storerepo;
		this.actrepo = actrepo;
	}

	@Override
	public StoreDetails saveStore(StoreDetails store) {
		return storerepo.save(store);
	}

	@Override
	public StoreDetails getStoreById(String id) {
		
		Long sid = Long.parseLong(id);
		Optional<StoreDetails> store = storerepo.findById(sid);
		if(!store.isEmpty()) {
			return store.get();
		}
		else {
			return null;
		}
	}

	@Override
	public int updateStore(StoreDetails store) {
		int res = storerepo.updateStoreDetails(store.getStore_name(), store.getStore_address(), store.getStore_contact(), store.getToll_free_no(), store.getStore_id());
		if(res>0) {
			Activities activity = new Activities();
			activity.setActivity("Store "+store.getStore_name() +" is updated successfully");
			activity.setActivity_date(Global.DATE_FORMATTER.format(LocalDateTime.now()));
			activity.setActivity_time(Global.TIME_FORMATTER.format(LocalDateTime.now()));
			actrepo.save(activity);
		}
		else {
			Activities activity = new Activities();
			activity.setActivity("Store "+store.getStore_name() +" is not updated");
			activity.setActivity_date(Global.DATE_FORMATTER.format(LocalDateTime.now()));
			activity.setActivity_time(Global.TIME_FORMATTER.format(LocalDateTime.now()));
			actrepo.save(activity);
		}
		return res;
	}
}
