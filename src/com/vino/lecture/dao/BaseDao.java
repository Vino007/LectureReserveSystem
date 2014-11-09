package com.vino.lecture.dao;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;

import org.springframework.stereotype.Repository;
@Repository("baseDao")
public class BaseDao {
	@Resource  //autowire byType, @resource by name
	protected SessionFactory sessionFactory ;
	public BaseDao(){
		
	}
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}
