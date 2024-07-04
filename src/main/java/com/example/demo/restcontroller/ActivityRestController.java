package com.example.demo.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Activities;
import com.example.demo.service.ActivityService;

@RestController()
@CrossOrigin("*")
@RequestMapping("activity")
public class ActivityRestController {

	private ActivityService actserv ;
	
	@Autowired
	public ActivityRestController(ActivityService actserv) {
		this.actserv = actserv;
	}
	
	@PostMapping("/")
	public ResponseEntity<Activities> saveActivity(@RequestBody Activities activity)
	{	
		Activities act = actserv.saveActivity(activity);
		if(act!=null)
			return new ResponseEntity<Activities>(act,HttpStatus.OK);
		else
			return new ResponseEntity<Activities>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Activities>> getAllActivities()
	{	
		List<Activities> actlist = actserv.getAllActivities();
		if(actlist.size()>0) {
			return new ResponseEntity<List<Activities>>(actlist , HttpStatus.OK); 
		}
		else {
			return new ResponseEntity<List<Activities>>(actlist , HttpStatus.NO_CONTENT);
		}
	
	}
}
