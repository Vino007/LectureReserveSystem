package com.vino.lecture.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vino.lecture.domain.LectureInfo;
import com.vino.lecture.domain.ReserveInfo;
@Service
public class LectureService extends BaseService{
	/**
	 * ��ѯ���еĽ�����Ϣ
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
	 * Ԥ�����������жϸ��û��Ƿ��Ѿ�ԤԼ���ˣ��ٽ���ԤԼ
	 * @param reserveInfo ԤԼ��Ϣ
	 * @return
	 */
	public String reserveLecture(ReserveInfo reserveInfo){
		if(lectureDao.checkReserveInfo(reserveInfo))		
		return lectureDao.reserveLecture(reserveInfo);
		else
			return "repeat";//�ظ�ԤԼ
		
		
	}
}
