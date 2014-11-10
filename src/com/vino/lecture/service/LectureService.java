package com.vino.lecture.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vino.lecture.domain.LectureInfo;
import com.vino.lecture.domain.ReserveInfo;
@Service
public class LectureService extends BaseService{
	/**
	 * 查询所有的讲座信息
	 * @return
	 */
	public List<LectureInfo> queryAllLecture(){
		return lectureDao.queryAllLectureInfo();
	}
	
	public boolean addLecture(LectureInfo lectureInfo){
		return lectureDao.insertLectureInfo(lectureInfo);
	}
	public boolean updateLecture(LectureInfo lectureInfo,long id){
		return lectureDao.updateLectureInfo(lectureInfo, id);
	}
	public boolean deleteLecture(long id){
		return lectureDao.deleteLectureInfoo(id);
	}
	/**
	 * 预定讲座，先判断该用户是否已经预约过了，再进行预约
	 * @param reserveInfo 预约信息
	 * @return
	 */
	public String reserveLecture(ReserveInfo reserveInfo){
		if(lectureDao.checkReserveInfo(reserveInfo))		
		return lectureDao.reserveLecture(reserveInfo);
		else
			return "repeat";//重复预约
		
		
	}
}
