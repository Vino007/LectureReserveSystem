package com.vino.lecture.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vino.lecture.domain.LectureInfo;
@Service
public class LectureService extends BaseService{
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
}
