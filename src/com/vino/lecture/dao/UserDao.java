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
		
		query=query.setString(0, user.getUsername());
		query=query.setString(1, user.getPassword());
		@SuppressWarnings("unchecked")
		List<User> users=(List<User>) query.list();				
		if(users.size()==0){
			return null;
		}
		else
			return users.get(0);
	}
	
	public void addUser(User user){
		sessionFactory.getCurrentSession().save(user);
	}
	public void deleteUserByUsername(String username){
		Query query=sessionFactory.getCurrentSession().createQuery("delete User u where u.username=?");
		query.setString(0, username);
		query.executeUpdate();				
	}
	public void updateUser(User user){
		Query query=sessionFactory.getCurrentSession().createQuery("update User u set u.password=?,u.name=? where u.id=?");
		query.setString(0, user.getPassword());
		query.setString(1, user.getName());
		query.setLong(2, user.getId());
		query.executeUpdate();
	}
}
