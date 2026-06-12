package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.models.Activities;
import com.example.demo.repository.ActivityRepository;

import lombok.RequiredArgsConstructor;

@Service("actserv")
@RequiredArgsConstructor
public class ActivityServImpl implements ActivityService {

	private final ActivityRepository actrepo;

	@Override
	public Activities saveActivity(Activities activity) {

		return actrepo.save(activity);
	}

	@Override
	public List<Activities> getAllActivities() {

		return actrepo.findAll();
	}

}
