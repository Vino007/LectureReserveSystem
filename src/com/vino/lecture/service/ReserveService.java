package com.vino.lecture.service;

import org.springframework.stereotype.Service;


import com.vino.lecture.domain.ReserveInfo;
@Service
public class ReserveService extends BaseService {
	public boolean updateCurrentPeople(ReserveInfo reserveInfo){
		reserveDao.updateCurrentPeople(reserveInfo);
		return true;
	}
	/**
	 * 预定讲座，先判断该用户是否已经预约过了，再进行预约
	 * @param reserveInfo 预约信息
	 * @return
	 */
	public String reserveLecture(ReserveInfo reserveInfo){
		if(reserveDao.checkReserveInfo(reserveInfo)){
		 if(reserveDao.checkCurrentPeople(reserveInfo))
				return reserveDao.reserveLecture(reserveInfo);
			else 
				return "overflow";
		 }
		else
			return "repeat";//重复预约		
	}
	public String cancelReserveLecture(ReserveInfo reserveInfo){
		if(reserveDao.checkReserveInfo(reserveInfo))
			return "alread_cancel";
		else
		return reserveDao.cancelReserveLecture(reserveInfo);
	}
}
