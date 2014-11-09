package com.vino.lecture.dao;



import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vino.lecture.domain.User;
@Repository
public class UserDao extends BaseDao {	
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public User checkUser(User user){
		
		Query query=sessionFactory.getCurrentSession().createQuery("from User u where u.username=? and u.password=?");
		//System.out.println(user.getUsername()+user.getPassword());
		query=query.setString(0, user.getUsername());
		query=query.setString(1, user.getPassword());
		List<User> users=(List<User>) query.list();				
		if(users.size()==0){
			return null;
		}
		else
			return users.get(0);
	}
	
}
