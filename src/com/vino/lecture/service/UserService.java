package com.vino.lecture.service;


import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vino.lecture.domain.User;
@Service
public class UserService extends BaseService {

	
	public User login(User user){
		
		return userDao.checkUser(user);
	}
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void addUser(User user) throws RuntimeException{
	
		try{
		userDao.addUser(user);
		//throw new RuntimeException("test");		
		} catch (Exception e) {
			//重新抛出运行时异常以便事务管理处理,在action中处理该异常
			throw new RuntimeException();
			
		}
	}
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void deleteUser(String username) throws RuntimeException{
		try{
		userDao.deleteUserByUsername(username);
		}catch(Exception e){
			throw new RuntimeException();
		}
	}
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void updateUser(User user) throws RuntimeException{
		try{
			userDao.updateUser(user);
		}catch(Exception e){
			throw new RuntimeException();
		}
	}
}
