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
	/**
	 * 条件更新
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
	 * 定时器使用的更新操作，不能使用spring的事务管理，spring管理的currentSession的事务！
	 * 手动管理事务
	 * getCurrentSession 创建的session会和绑定到当前线程,而openSession不会 
	 * session手动开启和关闭
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
	 * 无条件更新
	 * @param hql
	 */
	public void update(String hql){
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
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
	public List<T> queryWithCondition(String hql, List<Object> condition) {
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		if (condition != null && condition.size() > 0) {
			for (int index = 0; index < condition.size(); index++)
				query.setParameter(index, condition.get(index));
		}

		return query.list();

	}

	/**
	 * 只能返回单个结果的条件查询
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
	 * 无条件查询(不带？的hql)
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
	 * 无条件分页查询
	 * 业务逻辑：
	 * 1.查询目标表的总记录数，设置总记录数
	 * 2.按每次记录数查询数据库，设置beanList
	 * @param pageNo 当前页码
	 * @param pageRecord 每页记录数
	 * @param beanName 要查询的Bean的类名
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
		query.setFirstResult((pageNo-1)*pageRecord);//设置起始点
		query.setMaxResults(pageRecord);//设置一次返回的最大结果数
		pageBean.setBeanList(query.list()); 	
		return pageBean;		
	}
	
	
	
	@SuppressWarnings("unchecked")
	/**
	 * 条件分页查询
	 * @param pageNo 当前页码
	 * @param pageRecord 每页记录数 
	 * @param condition 查询的条件
	 * @param hql_count 求总记录数的hql
	 * @param hql_list  查询记录的hql
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
