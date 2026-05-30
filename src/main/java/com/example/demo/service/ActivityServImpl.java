package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.models.Activities;
import com.example.demo.repository.ActivityRepository;

@Service("actserv")
public class ActivityServImpl implements ActivityService {

	private final ActivityRepository actrepo;
	
	public ActivityServImpl(ActivityRepository actrepo) {
		this.actrepo = actrepo;
	}
	
	@Override
	public Activities saveActivity(Activities activity) {

		return actrepo.save(activity);
	}

	@Override
	public List<Activities> getAllActivities() {

		return actrepo.findAll();
	}

}
