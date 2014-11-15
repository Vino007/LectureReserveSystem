package com.vino.lecture.service;


import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vino.lecture.domain.User;
@Service
public class UserService extends BaseService {
private List<Object> condition=new ArrayList<Object>();
@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public User login(User user){
		String hql="from User u where u.username=? and u.password=?";
		condition.clear();
		condition.add(user.getUsername());
		condition.add(user.getPassword());
		user=(User) userDao.query(hql, condition);
		condition.clear();
		return user;	
		}
	
	

	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void addUser(User user) throws RuntimeException{
	
		try{
		userDao.add(user);
		//throw new RuntimeException("test");		
		} catch (Exception e) {
			//重新抛出运行时异常以便事务管理处理,在action中处理该异常
			throw new RuntimeException();
			
		}
	}
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void deleteUser(long id) throws RuntimeException{
		try{
		userDao.delete(User.class,id);
		}catch(Exception e){
			throw new RuntimeException();
		}
	}
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void updateUser(User user) throws RuntimeException{
		String hql="update User u set u.password=?,u.name=? where u.id=?";
		condition.clear();
		condition.add(user.getPassword());
		condition.add(user.getName());
		condition.add(user.getId());
		try{
			userDao.update(hql, condition);
			condition.clear();
		}catch(Exception e){
			throw new RuntimeException();
		}
	}
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public List<User> queryAllUser() {
		String hql="from User";	
		return userDao.queryAll(hql);	
	}
}
