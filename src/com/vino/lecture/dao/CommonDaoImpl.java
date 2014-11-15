package com.vino.lecture.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class CommonDaoImpl<T> extends BaseDao implements CommonDao<T> {
	public CommonDaoImpl() {
		/*
		 * 放在了超类，也就是声明泛型的那个类的构造方法中。这样一来，子类在继承具有泛型的超类时
		 * ，会自动调用超类的构造方法。在此超类的构造方法中，调用的getClass返回的是子类的Class类型 使用(Class<T>)对
		 * getActualTypeArguments()返回的元素做casting，即可得到所谓的T.class。
		 * 
		 * 会出现转型错误cannot be cast to java.lang.reflect.ParameterizedType
		 */
		/*
		 * //getGenericSuperclass 获取泛型父类 Type
		 * genType=getClass().getGenericSuperclass();
		 * System.out.println(genType); //获取泛型类的参数类型 Type[]
		 * params=((ParameterizedType)genType).getActualTypeArguments();
		 * //获得T.class //System.out.println(params); entityClass=(Class<T>)
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
	 * 条件查询
	 * 
	 * @param hql
	 *            hql执行的语句
	 * @param condition
	 *            条件:hql中？的值。 int
	 *            ,string等不要hibernate能自动识别类型，如date就要用Hibernate.DATE来指定类型
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
	 * 只能返回单个结果的查询
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
	 * 无条件查询全部结果
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
