package com.vino.lecture.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vino.lecture.domain.LectureInfo;

import com.vino.lecture.domain.User;
@Service
public class LectureService extends BaseService{
	/**
	 * 查询所有的讲座信息
	 * @return
	 */
	public List<LectureInfo> queryAllLecture(){
		return lectureDao.queryAllLectureInfo();
	}
	public List<LectureInfo> queryAvailableLecture(){
		return lectureDao.queryAvailableLectureInfo();
	}
	public List<LectureInfo> queryReservedLecture(User user){
		return lectureDao.queryReservedLectureInfo(user);
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
