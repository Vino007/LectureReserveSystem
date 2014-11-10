package com.vino.lecture.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vino.lecture.domain.LectureInfo;
import com.vino.lecture.domain.ReserveInfo;

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
	 * 
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<LectureInfo> queryAvailableLectureInfo() {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from LectureInfo l where l.available=?");
		query.setInteger(0, 1);
		@SuppressWarnings("unchecked")
		List<LectureInfo> lectureInfos = (List<LectureInfo>) query.list();
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
	public boolean cancelReserveLecture(ReserveInfo reserveInfo) {
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
			return false;
		}
		return true;
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
}
