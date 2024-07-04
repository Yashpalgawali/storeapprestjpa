package com.example.demo.service;

import java.util.List;

import com.example.demo.models.Activities;

public interface ActivityService {

	
	public Activities saveActivity(Activities activity);
	
	public List<Activities> getAllActivities();
	
}
