package com.vino.lecture.dao;

import java.util.List;

public interface CommonDao<T> {
	public void add(Object o);
	public void delete(String hql,List<Object> condition);
	public void delete(Class<T> entityClass,long id);
	public void updateWithCondition(String hql,List<Object> condition);
	public List<T> queryWithCondition(String hql,List<Object> condition);
	public Object query(String hql,List<Object> condition);
	public List<T> query(String hql);
}
