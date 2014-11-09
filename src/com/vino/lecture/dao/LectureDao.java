package com.vino.lecture.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vino.lecture.domain.LectureInfo;

@Repository("lectureDao")
public class LectureDao extends BaseDao{
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public List<LectureInfo> queryAllLectureInfo(){
		Query query=sessionFactory.getCurrentSession().createQuery("from LectureInfo");
		List<LectureInfo> lectureInfos=(List<LectureInfo>)query.list();
		return lectureInfos;
		
	}
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public boolean insertLectureInfo(LectureInfo lectureInfo){
		try{
		sessionFactory.getCurrentSession().save(lectureInfo);
		}
		catch(Exception e){
			return false;
		}
		return true;
	}
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public boolean updateLectureInfo(LectureInfo lectureInfo,long id){
		Session session=sessionFactory.getCurrentSession();
		LectureInfo info=(LectureInfo) session.get(LectureInfo.class, new Long(id));
		info.setTitle(lectureInfo.getTitle());
		info.setTime(lectureInfo.getTime());
		info.setAddress(lectureInfo.getAddress());
		info.setLecturer(lectureInfo.getLecturer());
		info.setMaxPeople(lectureInfo.getMaxPeople());
		info.setContent(lectureInfo.getContent());
		try{
		session.update(info);
		}catch(Exception e){
			return false;
		}
		return true;
		
	}
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public boolean deleteLectureInfoo(long id){
		Session session=sessionFactory.getCurrentSession();
		LectureInfo info=(LectureInfo) session.get(LectureInfo.class, new Long(id));
		try{
			session.delete(info);
			}catch(Exception e){
				return false;
			}
			return true;
	}
	
	
}
