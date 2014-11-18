package com.vino.lecture.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vino.lecture.domain.PageBean;

public class CommonDaoImpl<T> extends BaseDao implements CommonDao<T> {
	public CommonDaoImpl() {
		/*
		 * �����˳��࣬Ҳ�����������͵��Ǹ���Ĺ��췽���С�����һ���������ڼ̳о��з��͵ĳ���ʱ
		 * �����Զ����ó���Ĺ��췽�����ڴ˳���Ĺ��췽���У����õ�getClass���ص��������Class���� ʹ��(Class<T>)��
		 * getActualTypeArguments()���ص�Ԫ����casting�����ɵõ���ν��T.class��
		 * 
		 * �����ת�ʹ���cannot be cast to java.lang.reflect.ParameterizedType
		 */
		/*
		 * //getGenericSuperclass ��ȡ���͸��� Type
		 * genType=getClass().getGenericSuperclass();
		 * System.out.println(genType); //��ȡ������Ĳ������� Type[]
		 * params=((ParameterizedType)genType).getActualTypeArguments();
		 * //���T.class //System.out.println(params); entityClass=(Class<T>)
		 * params[0];
		 */
	}

	public void add(Object o) {

		sessionFactory.getCurrentSession().save(o);
	}

	public void delete(String hql, List<Object> condition) {
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		if (condition != null && condition.size() > 0) {
			for (int index = 0; index < condition.size(); index++)
				query.setParameter(index, condition.get(index));
		}

		query.executeUpdate();
	}

	public void delete(Class<T> entityClass, long id) {
		@SuppressWarnings("unchecked")
		T bean = (T) sessionFactory.getCurrentSession().get(entityClass,
				new Long(id));
		sessionFactory.getCurrentSession().delete(bean);
	}
	/**
	 * ��������
	 */
	public void updateWithCondition(String hql, List<Object> condition) {
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		if (condition != null && condition.size() > 0) {
			for (int index = 0; index < condition.size(); index++)
				query.setParameter(index, condition.get(index));
		}
		query.executeUpdate();

	}
	/**
	 * ��ʱ��ʹ�õĸ��²���������ʹ��spring���������spring�����currentSession������
	 * �ֶ���������
	 * getCurrentSession ������session��Ͱ󶨵���ǰ�߳�,��openSession���� 
	 * session�ֶ������͹ر�
	 * @param hql
	 * @param condition
	 */
	public void updateTimer(String hql, List<Object> condition) {
		Session session=sessionFactory.openSession();
		Query query = session.createQuery(hql);
		if (condition != null && condition.size() > 0) {
			for (int index = 0; index < condition.size(); index++)
				query.setParameter(index, condition.get(index));
		}
		session.beginTransaction();
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();

	}
	/**
	 * ����������
	 * @param hql
	 */
	public void update(String hql){
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		query.executeUpdate();

	}

	/**
	 * ������ѯ
	 * 
	 * @param hql
	 *            hqlִ�е����
	 * @param condition
	 *            ����:hql�У���ֵ�� int
	 *            ,string�Ȳ�Ҫhibernate���Զ�ʶ�����ͣ���date��Ҫ��Hibernate.DATE��ָ������
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<T> queryWithCondition(String hql, List<Object> condition) {
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		if (condition != null && condition.size() > 0) {
			for (int index = 0; index < condition.size(); index++)
				query.setParameter(index, condition.get(index));
		}

		return query.list();

	}

	/**
	 * ֻ�ܷ��ص��������������ѯ
	 * 
	 * @param hql
	 * @param condition
	 * @return null or object
	 */

	public Object query(String hql, List<Object> condition) {
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		if (condition != null && condition.size() > 0) {
			for (int index = 0; index < condition.size(); index++)
				query.setParameter(index, condition.get(index));
		}

		return query.uniqueResult();

	}

	/**
	 * ��������ѯ(��������hql)
	 * 
	 * @param hql 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> query(String hql) {
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();

	}
	@SuppressWarnings("unchecked")
	/**
	 * ��������ҳ��ѯ
	 * ҵ���߼���
	 * 1.��ѯĿ�����ܼ�¼���������ܼ�¼��
	 * 2.��ÿ�μ�¼����ѯ���ݿ⣬����beanList
	 * @param pageNo ��ǰҳ��
	 * @param pageRecord ÿҳ��¼��
	 * @param beanName Ҫ��ѯ��Bean������
	 * @return pageBean
	 */
	public PageBean<T> queryPage(int pageNo,int pageRecord,String beanName){
		PageBean<T> pageBean=new PageBean<T>();
		pageBean.setPageNo(pageNo);
		pageBean.setPageRecord(pageRecord);
		String hql="select count(*) from "+ beanName;	
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		long totalRecord=(long) query.uniqueResult();
		pageBean.setTotalRecord((int) totalRecord);
		hql="from "+beanName;
		query=sessionFactory.getCurrentSession().createQuery(hql);
		query.setFirstResult((pageNo-1)*pageRecord);//������ʼ��
		query.setMaxResults(pageRecord);//����һ�η��ص��������
		pageBean.setBeanList(query.list()); 	
		return pageBean;		
	}
	
	
	
	@SuppressWarnings("unchecked")
	/**
	 * ������ҳ��ѯ
	 * @param pageNo ��ǰҳ��
	 * @param pageRecord ÿҳ��¼�� 
	 * @param condition ��ѯ������
	 * @param hql_count ���ܼ�¼����hql
	 * @param hql_list  ��ѯ��¼��hql
	 * @return
	 */
	public PageBean<T> queryPageWithCondition(int pageNo,int pageRecord,
			List<Object> condition,String hql_count,String hql_list){
		PageBean<T> pageBean=new PageBean<T>();
		pageBean.setPageNo(pageNo);
		pageBean.setPageRecord(pageRecord);
		
		Query query=sessionFactory.getCurrentSession().createQuery(hql_count);
		if (condition != null && condition.size() > 0) {
			for (int index = 0; index < condition.size(); index++)
				query.setParameter(index, condition.get(index));
		}

		long totalRecord=(long) query.uniqueResult();
		pageBean.setTotalRecord((int) totalRecord);	
		query=sessionFactory.getCurrentSession().createQuery(hql_list);	
		if (condition != null && condition.size() > 0) {
			for (int index = 0; index < condition.size(); index++)
				query.setParameter(index, condition.get(index));
		}
		query.setFirstResult((pageNo-1)*pageRecord);
		query.setMaxResults(pageRecord);
		pageBean.setBeanList(query.list()); 	
		return pageBean;
		
		
	}
}
