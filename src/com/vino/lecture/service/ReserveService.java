package com.vino.lecture.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vino.lecture.domain.ReserveInfo;

@Service
public class ReserveService extends BaseService {
	private List<Object> condition = new ArrayList<Object>();

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void updateCurrentPeople(ReserveInfo reserveInfo) {
		// reserveDao.updateCurrentPeople(reserveInfo);
		String hql = "select count(*) from ReserveInfo r where r.lectureId=?";
		condition.add(reserveInfo.getLectureId());
		long currentPeople = (long) reserveDao.query(hql, condition);
		condition.clear();
		hql = "update LectureInfo l set l.currentPeople=? where l.id=?";
		condition.add((int) currentPeople);
		condition.add(reserveInfo.getLectureId());
		reserveDao.update(hql, condition);
		condition.clear();
	}

	/**
	 * Ԥ�����������жϸ��û��Ƿ��Ѿ�ԤԼ���ˣ��ٽ���ԤԼ
	 * 
	 * @param reserveInfo
	 *            ԤԼ��Ϣ
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public String reserveLecture(ReserveInfo reserveInfo)
			throws RuntimeException {
		try {
			if (checkReserveInfo(reserveInfo)) {
				if (checkCurrentPeople(reserveInfo)) {
					reserveDao.add(reserveInfo);
					return "success";
				} else
					return "overflow";// ��������
			} else
				return "repeat";// �ظ�ԤԼ
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	/**
	 * ȡ��ԤԼ
	 * @param reserveInfo
	 * @return
	 * @throws RuntimeException
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public String cancelReserveLecture(ReserveInfo reserveInfo)
			throws RuntimeException {
		try {
			if (checkReserveInfo(reserveInfo))
				return "alread_cancel";
			else {

				String hql = "delete ReserveInfo r where r.username=? and  r.lectureId=?";
				condition.clear();
				condition.add(reserveInfo.getUsername());
				condition.add(reserveInfo.getLectureId());
				reserveDao.delete(hql, condition);
				condition.clear();
				return "success";
			}
		} catch (Exception e) {
			throw new RuntimeException();
		}

	}

	/**
	 * ��鵱ǰ�û��Ƿ�ԤԼ���Ľ���
	 * 
	 * @param reserveInfo
	 * @return true û��
	 */
	public boolean checkReserveInfo(ReserveInfo reserveInfo) {

		String hql = "from ReserveInfo r where r.username=? and r.lectureId=?";
		condition.clear();
		condition.add(reserveInfo.getUsername());
		condition.add(reserveInfo.getLectureId());
		Object obj = reserveDao.query(hql, condition);
		condition.clear();
		if (obj == null)
			return true;
		else
			return false;
	}

	/**
	 * ��鵱ǰ�������Ƿ���������������
	 * 
	 * @param reserveInfo
	 * @return true ���Խ���ԤԼ false �����Խ���ԤԼ
	 * 
	 */
	public boolean checkCurrentPeople(ReserveInfo reserveInfo) {
		String hql = "select  currentPeople,maxPeople  from LectureInfo l where l.id=?";
		condition.clear();
		condition.add(reserveInfo.getLectureId());
		Object[] obj = (Object[]) reserveDao.query(hql, condition);
		condition.clear();
		// ����ѯ���ֶ������������������ϵ�ʱ��list���װ����object[]����
		int currentPeople = (int) obj[0];
		int maxPeople = (int) obj[1];
		if (currentPeople < maxPeople)
			return true;
		else
			return false;

	}

}
