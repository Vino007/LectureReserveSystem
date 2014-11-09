package com.vino.lecture.service;


import org.springframework.stereotype.Service;
import com.vino.lecture.domain.User;
@Service
public class UserService extends BaseService {

	
	public User login(User user){
		
		return userDao.checkUser(user);
	}
}
