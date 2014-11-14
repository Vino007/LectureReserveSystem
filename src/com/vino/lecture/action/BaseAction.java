package com.vino.lecture.action;

import com.opensymphony.xwork2.ActionSupport;
import com.vino.lecture.domain.User;
import com.vino.lecture.service.LectureService;
import com.vino.lecture.service.ReserveService;
import com.vino.lecture.service.UserService;

public class BaseAction extends ActionSupport{
	
	/**
	 * action�Ļ��࣬ע����userService��user,lectureService����
	 * ����action������ע�뱾��Ŀ������xml��ʽ�������������ע�������ע�ⷽʽ
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected UserService userService;
	protected User user;
	protected LectureService lectureService;
	protected ReserveService reserveService;
	public ReserveService getReserveService() {
		return reserveService;
	}

	public void setReserveService(ReserveService reserveService) {
		this.reserveService = reserveService;
	}

	public LectureService getLectureService() {
		return lectureService;
	}

	public void setLectureService(LectureService lectureService) {
		this.lectureService = lectureService;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	

}
