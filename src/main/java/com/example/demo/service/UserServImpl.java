package com.example.demo.service;

import java.time.LocalDateTime;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.globalconfig.Global;
import com.example.demo.models.Activities;
import com.example.demo.models.Users;
import com.example.demo.repository.ActivityRepository;
import com.example.demo.repository.UserRepository;

@Service("userserv")
public class UserServImpl implements UserService {

	private final UserRepository userrepo;
	private final BCryptPasswordEncoder passEncode;
	private final ActivityRepository actrepo;
	
	public UserServImpl(UserRepository userrepo, BCryptPasswordEncoder passEncode, ActivityRepository actrepo) {
		super();
		this.userrepo = userrepo;
		this.passEncode = passEncode;
		this.actrepo = actrepo;
	}


	@Override
	public int updatePassword(Users user) {
		Activities activity = new Activities();
		activity.setActivity_date(Global.DATE_FORMATTER.format(LocalDateTime.now()));
		activity.setActivity_time(Global.TIME_FORMATTER.format(LocalDateTime.now()));
		
		int res = 0;
		String encpass = passEncode.encode(user.getPassword());
		if(user.getUser_id()!=null)
		{
			res = userrepo.updatePassword(user.getUser_id(), encpass);
		}
		if(user.getUsername()!=null || !user.getUsername().equals(""))
		{
			res = userrepo.updatePasswordByUserName(user.getUsername(), encpass);
		}
		if(res > 0) {
			activity.setActivity("Password is updated Successfully");
			actrepo.save(activity);
		}else {
			activity.setActivity("Password is not updated ");
			actrepo.save(activity);
		}
		return res;
	}

}
