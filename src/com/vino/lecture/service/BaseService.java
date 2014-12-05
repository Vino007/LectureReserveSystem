package com.vino.lecture.service;

import javax.annotation.Resource;




import org.springframework.stereotype.Service;

import com.vino.lecture.dao.AdminDao;
import com.vino.lecture.dao.LectureDao;
import com.vino.lecture.dao.ReserveDao;
import com.vino.lecture.dao.UserDao;

/**
 * service�Ļ��࣬������userDao,lectureDao����ע�룬��������ÿ���������ظ������ע��
 * @author Joker
 *
 */
@Service("baseService")
public class BaseService {
	@Resource
	protected UserDao userDao;
	@Resource
	protected LectureDao lectureDao;
	@Resource
	protected ReserveDao reserveDao;
	@Resource
	protected AdminDao adminDao;
	
	public AdminDao getAdminDao() {
		return adminDao;
	}
	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}
	public ReserveDao getReserveDao() {
		return reserveDao;
	}
	public void setReserveDao(ReserveDao reserveDao) {
		this.reserveDao = reserveDao;
	}
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
