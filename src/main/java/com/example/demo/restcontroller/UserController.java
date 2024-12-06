package com.example.demo.restcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Users;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {

	private final UserService userserv;

	public UserController(UserService userserv) {
		super();
		this.userserv = userserv;
	}
	
	@PutMapping("/")
	public ResponseEntity<String> updatePassword(@RequestBody Users user )
	{
		int result = userserv.updatePassword(user);
		if(result>0)
		{
			System.out.println("UPDATED success");
			return new ResponseEntity<String>("Password is updated successfully", HttpStatus.OK);
		}
		else {
			System.err.println("NOT UPDATED ");
			return new ResponseEntity<String>("Password is not updated ", HttpStatus.NOT_MODIFIED);
		}
	}
}
