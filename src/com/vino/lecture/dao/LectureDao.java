package com.vino.lecture.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vino.lecture.domain.LectureInfo;

import com.vino.lecture.domain.User;

@Repository("lectureDao")
public class LectureDao extends BaseDao {
	/**
	 * ��ѯ���н�����Ϣ
	 * 
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<LectureInfo> queryAllLectureInfo() {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from LectureInfo");
		@SuppressWarnings("unchecked")
		List<LectureInfo> lectureInfos = (List<LectureInfo>) query.list();
		return lectureInfos;
	}

	/**
	 * ��ѯ����ԤԼ�Ľ���
	 * available ��0ʱΪ��ԤԼ��
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<LectureInfo> queryAvailableLectureInfo() {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from LectureInfo l where l.available>=?");
		query.setInteger(0, 1);
		@SuppressWarnings("unchecked")
		List<LectureInfo> lectureInfos = (List<LectureInfo>) query.list();
		return lectureInfos;

		
	}
	/**
	 * �����û�����ѯԤԼ���Ľ���
	 * 
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<LectureInfo> queryReservedLectureInfo(User user) {
		List<LectureInfo> lectureInfos=new ArrayList<LectureInfo>();
		
		Query query = sessionFactory.getCurrentSession().createQuery(
				"select lectureId from ReserveInfo r where r.username=?");
		
		query.setString(0, user.getUsername());
		@SuppressWarnings("rawtypes")
		List list=query.list();
		ArrayList<Long> lectureIds=new ArrayList<Long>();		
		for(Object obj:list){
			 lectureIds.add((long) obj);			
		}
		/*
		 * ������ѯ������ѯ��������ӵ�lectureInfos��
		 */
	
		for(int i=0;i<lectureIds.size();i++){
		query = sessionFactory.getCurrentSession().createQuery(
				"from LectureInfo l where l.id=?");
		query.setLong(0, lectureIds.get(i));
		LectureInfo lectureInfo=(LectureInfo) query.list().get(0);
		lectureInfos.add(lectureInfo);
		}
		
		return lectureInfos;

	}
	
	/**
	 * �����µĽ�����Ϣ
	 * 
	 * @param lectureInfo
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean insertLectureInfo(LectureInfo lectureInfo) {
		try {
			lectureInfo.setAvailable(1);//�²���Ľ���Ĭ�Ͽ���ԤԼ
			sessionFactory.getCurrentSession().save(lectureInfo);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * ���������id���½�����Ϣ
	 * 
	 * @param lectureInfo
	 *            �ӱ��л�ȡ�Ľ�����Ϣ
	 * @param id
	 *            Ҫ�޸ĵĽ���ID
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean updateLectureInfo(LectureInfo lectureInfo, long id) {
		Session session = sessionFactory.getCurrentSession();
		LectureInfo info = (LectureInfo) session.get(LectureInfo.class,
				new Long(id));
		info.setTitle(lectureInfo.getTitle());
		info.setTime(lectureInfo.getTime());
		info.setAddress(lectureInfo.getAddress());
		info.setLecturer(lectureInfo.getLecturer());
		info.setMaxPeople(lectureInfo.getMaxPeople());
		info.setContent(lectureInfo.getContent());
		//info.setAvailable(1);//����Ĭ�Ͽ�ԤԼ
		try {
			session.update(info);
		} catch (Exception e) {
			return false;
		}
		return true;

	}

	/**
	 * ���������idɾ��������Ϣ
	 * 
	 * @param id
	 *            Ҫɾ���Ľ���id
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean deleteLectureInfoo(long id) {
		Session session = sessionFactory.getCurrentSession();
		LectureInfo info = (LectureInfo) session.get(LectureInfo.class,
				new Long(id));
		try {
			session.delete(info);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	
}
