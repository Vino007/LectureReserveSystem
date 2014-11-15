package com.vino.lecture.dao;


import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

/**
 * dao的基类，注入了sessionFactory属性,注意不要采用构造器注入
 * 若用构造器注入sessionFactory，子类中将获取不到sessionFactory
 * @author Joker
 *
 */
@Repository("baseDao")
public class BaseDao {
	
	@Resource  //autowire byType, @resource by name
	protected SessionFactory sessionFactory ;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	
	
}
