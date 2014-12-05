package com.vino.lecture.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vino.lecture.domain.PageBean;
import com.vino.lecture.domain.User;
@Service
public class UserService extends BaseService {
private List<Object> condition;
@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public User login(User user){
		String hql="from User u where u.username=? and u.password=?";
		condition=new ArrayList<Object>();
		condition.add(user.getUsername());
		condition.add(user.getPassword());
		user=(User) userDao.query(hql, condition);	
		return user;	
		}
	
	

	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public String addUser(User user) throws RuntimeException{
		user.setPassword("123456");//user默认密码
		String hql="from User u where u.username=?";
		condition=new ArrayList<Object>();
		condition.add(user.getUsername());
		try{
		if(userDao.query(hql, condition)==null){
		userDao.add(user);
		return "success";
		}
		else
			return "repeat";
			
		} catch (Exception e) {
			//重新抛出运行时异常以便事务管理处理,在action中处理该异常
			e.printStackTrace();
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
	public void deleteUsers(List<Long> ids) throws RuntimeException{
		try{
		for(Long id :ids)
			if(id!=null)
				userDao.delete(User.class,id);
		}catch(Exception e){
			throw new RuntimeException();
		}
	}
	
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public void updateUser(User user) throws RuntimeException{
		String hql="update User u set u.password=?,u.name=? where u.id=?";
		condition=new ArrayList<Object>();
		condition.add(user.getPassword());
		condition.add(user.getName());
		condition.add(user.getId());
		try{
			userDao.updateWithCondition(hql, condition);
			
		}catch(Exception e){
			throw new RuntimeException();
		}
	}
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public List<User> queryAllUser() {
		String hql="from User";	
		return userDao.query(hql);	
	}
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public PageBean<User> pageQueryUser(int pageNo,int pageRecord) {		
		return userDao.queryPage(pageNo, pageRecord,"User");		
	}
	
	/**
	 * 条件查询用户！！
	 * @param pageNo
	 * @param pageRecord
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public PageBean<User> pageQueryUserWithCondition(int pageNo,int pageRecord) {
		
		String hql_count="select count(*) from User u where u.id>?";
		String hql_list="from User u where u.id>?";
		condition=new ArrayList<Object>();
		condition.add((long)3);
		return userDao.queryPageWithCondition(pageNo, pageRecord, condition, hql_count, hql_list);
		
		
	}
	
	
}
