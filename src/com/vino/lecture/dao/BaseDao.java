package com.vino.lecture.dao;


import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

/**
 * dao�Ļ��࣬ע����sessionFactory����,ע�ⲻҪ���ù�����ע��
 * ���ù�����ע��sessionFactory�������н���ȡ����sessionFactory
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
