package com.vino.lecture.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

	public void update(String hql, List<Object> condition) {
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		if (condition != null && condition.size() > 0) {
			for (int index = 0; index < condition.size(); index++)
				query.setParameter(index, condition.get(index));
		}
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
	public List<T> queryList(String hql, List<Object> condition) {
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		if (condition != null && condition.size() > 0) {
			for (int index = 0; index < condition.size(); index++)
				query.setParameter(index, condition.get(index));
		}

		return query.list();

	}

	/**
	 * ֻ�ܷ��ص�������Ĳ�ѯ
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
	 * ��������ѯȫ�����
	 * 
	 * @param hql
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> queryAll(String hql) {
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();

	}
}
