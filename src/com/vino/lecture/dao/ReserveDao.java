package com.vino.lecture.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vino.lecture.domain.ReserveInfo;

@Repository("reserveDao")
public class ReserveDao extends BaseDao {
	/**
	 * 预定讲座，逻辑是：先查询出所有的讲座列表，然后再点击预定按钮
	 * 
	 * @param reserveInfo
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public String reserveLecture(ReserveInfo reserveInfo) {
		try {
			sessionFactory.getCurrentSession().save(reserveInfo);
		} catch (Exception e) {
			return "fail";
		}
		return "success";
	}

	/**
	 * 通过用户名和讲座ID来取消预约
	 * 
	 * @param reserveInfo
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public String cancelReserveLecture(ReserveInfo reserveInfo) {
		/*
		 * 通过HQL语句来删除对象
		 */
		Query query = sessionFactory.getCurrentSession().createQuery(
				"delete ReserveInfo r where r.username=? and r.lectureId=?");
		query.setString(0, reserveInfo.getUsername());
		query.setLong(1, reserveInfo.getLectureId());
		try {
			query.executeUpdate();
		} catch (Exception e) {
			return "fail";
		}
		return "success";
	}

	/**
	 * 判断用户是否已经预定过该讲座，配合reserveLecture使用
	 * 
	 * @param reserveInfo
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public boolean checkReserveInfo(ReserveInfo reserveInfo) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from ReserveInfo r where r.username=? and r.lectureId=?");
		query.setString(0, reserveInfo.getUsername());
		query.setLong(1, reserveInfo.getLectureId());
		@SuppressWarnings("unchecked")
		List<ReserveInfo> reserveInfos = query.list();
		if (reserveInfos.size() == 0)
			return true;
		else
			return false;
	}
	/**
	 * 通过count（）计算reserveinfo表中当前预约人数并更新lectureinfo表中的currentpeople字段
	 * @param reserveInfo
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean updateCurrentPeople(ReserveInfo reserveInfo){
		/*
		 * 计算预约人数
		 */
		Query query=sessionFactory.getCurrentSession()
				.createQuery("select count(*)  from ReserveInfo r where r.lectureId=?");
		query.setLong(0, reserveInfo.getLectureId());	
		long currentPeople=  (long) query.list().get(0);	
		/*
		 * 更新预约人数
		 */
		query=sessionFactory.getCurrentSession()
				.createQuery("update LectureInfo l set l.currentPeople=? where l.id=?");
		query.setInteger(0, (int) currentPeople);
		query.setLong(1, reserveInfo.getLectureId());
		query.executeUpdate();
		return true;		
	}
	
	/**
	 * 检查当前人数是否小于最大允许人数
	 * @param reserveInfo
	 * @return true currentPeople<maxpeople
	 * @return false currentPeople>maxpeople
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public boolean checkCurrentPeople(ReserveInfo reserveInfo){
		Query query=sessionFactory.getCurrentSession()
				.createQuery("select  currentPeople,maxPeople  from LectureInfo l where l.id=?");
		query.setLong(0, reserveInfo.getLectureId());	
		@SuppressWarnings("rawtypes")
		List list=query.list();
		//当查询的字段有两个或者两个以上的时候，list里封装的是object[]对象
		Object[] obj= (Object[]) list.get(0);		
		int currentPeople=(int) obj[0] ;
		int maxPeople=(int) obj[1];	
		if(currentPeople<maxPeople)
			return true;
		else 
			return false;
		
	}
}
