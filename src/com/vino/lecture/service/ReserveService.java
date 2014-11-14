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
	 * Ԥ�����������жϸ��û��Ƿ��Ѿ�ԤԼ���ˣ��ٽ���ԤԼ
	 * @param reserveInfo ԤԼ��Ϣ
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
			return "repeat";//�ظ�ԤԼ		
	}
	public String cancelReserveLecture(ReserveInfo reserveInfo){
		if(reserveDao.checkReserveInfo(reserveInfo))
			return "alread_cancel";
		else
		return reserveDao.cancelReserveLecture(reserveInfo);
	}
}
