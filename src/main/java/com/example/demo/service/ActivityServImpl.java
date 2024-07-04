package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Activities;
import com.example.demo.repository.ActivityRepository;

@Service("actserv")
public class ActivityServImpl implements ActivityService {

	private ActivityRepository actrepo;
	
	@Autowired
	public ActivityServImpl(ActivityRepository actrepo) {
		this.actrepo = actrepo;
	}
	
	@Override
	public Activities saveActivity(Activities activity) {
		// TODO Auto-generated method stub
		return actrepo.save(activity);
	}

	@Override
	public List<Activities> getAllActivities() {
		// TODO Auto-generated method stub
		return actrepo.findAll();
	}

}
