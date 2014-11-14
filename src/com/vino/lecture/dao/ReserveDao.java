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
	 * Ԥ���������߼��ǣ��Ȳ�ѯ�����еĽ����б�Ȼ���ٵ��Ԥ����ť
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
	 * ͨ���û����ͽ���ID��ȡ��ԤԼ
	 * 
	 * @param reserveInfo
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public String cancelReserveLecture(ReserveInfo reserveInfo) {
		/*
		 * ͨ��HQL�����ɾ������
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
	 * �ж��û��Ƿ��Ѿ�Ԥ�����ý��������reserveLectureʹ��
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
	 * ͨ��count��������reserveinfo���е�ǰԤԼ����������lectureinfo���е�currentpeople�ֶ�
	 * @param reserveInfo
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean updateCurrentPeople(ReserveInfo reserveInfo){
		/*
		 * ����ԤԼ����
		 */
		Query query=sessionFactory.getCurrentSession()
				.createQuery("select count(*)  from ReserveInfo r where r.lectureId=?");
		query.setLong(0, reserveInfo.getLectureId());	
		long currentPeople=  (long) query.list().get(0);	
		/*
		 * ����ԤԼ����
		 */
		query=sessionFactory.getCurrentSession()
				.createQuery("update LectureInfo l set l.currentPeople=? where l.id=?");
		query.setInteger(0, (int) currentPeople);
		query.setLong(1, reserveInfo.getLectureId());
		query.executeUpdate();
		return true;		
	}
	
	/**
	 * ��鵱ǰ�����Ƿ�С�������������
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
		//����ѯ���ֶ������������������ϵ�ʱ��list���װ����object[]����
		Object[] obj= (Object[]) list.get(0);		
		int currentPeople=(int) obj[0] ;
		int maxPeople=(int) obj[1];	
		if(currentPeople<maxPeople)
			return true;
		else 
			return false;
		
	}
}
