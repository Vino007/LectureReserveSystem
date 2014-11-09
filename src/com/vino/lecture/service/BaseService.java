package com.vino.lecture.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.vino.lecture.dao.LectureDao;
import com.vino.lecture.dao.UserDao;
@Service("baseService")
public class BaseService {
	@Resource
	protected UserDao userDao;
	@Resource
	protected LectureDao lectureDao;
	public UserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public LectureDao getLectureDao() {
		return lectureDao;
	}
	public void setLectureDao(LectureDao lectureDao) {
		this.lectureDao = lectureDao;
	}
	
}
