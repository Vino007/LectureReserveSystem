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
	 * 查询所有讲座信息
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
	 * 查询允许预约的讲座
	 * available 非0时为可预约！
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
	 * 根据用户名查询预约过的讲座
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
		 * 批量查询！！查询结果逐个添加到lectureInfos中
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
	 * 插入新的讲座信息
	 * 
	 * @param lectureInfo
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean insertLectureInfo(LectureInfo lectureInfo) {
		try {
			lectureInfo.setAvailable(1);//新插入的讲座默认可以预约
			sessionFactory.getCurrentSession().save(lectureInfo);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 根据输入的id更新讲座信息
	 * 
	 * @param lectureInfo
	 *            从表单中获取的讲座信息
	 * @param id
	 *            要修改的讲座ID
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
		//info.setAvailable(1);//设置默认可预约
		try {
			session.update(info);
		} catch (Exception e) {
			return false;
		}
		return true;

	}

	/**
	 * 根据输入的id删除讲座信息
	 * 
	 * @param id
	 *            要删除的讲座id
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
