package com.vino.lecture.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vino.lecture.domain.PageBean;
import com.vino.lecture.domain.ReserveInfo;

@Service
public class ReserveService extends BaseService {
	private List<Object> condition = new ArrayList<Object>();
	/**
	 * ��ҳ��ѯԤԼ�嵥
	 * @param pageNo
	 * @param pageRecord
	 * @param id
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public PageBean<ReserveInfo> queryReserveList(int pageNo,int pageRecord,long id){
		String hql_count="select count(*) from ReserveInfo r where r.lectureId=?";
		String hql_list="from ReserveInfo r where r.lectureId=?";
		condition = new ArrayList<Object>();
		condition.add(id);
		
		return reserveDao.queryPageWithCondition(pageNo, pageRecord, condition, hql_count, hql_list);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public PageBean<ReserveInfo> queryAttenceList(int pageNo,int pageRecord,long id){
		String hql_count="select count(*) from ReserveInfo r where r.lectureId=? and r.attence=1";
		String hql_list="from ReserveInfo r where r.lectureId=? and r.attence=1";
		condition = new ArrayList<Object>();
		condition.add(id);
		
		return reserveDao.queryPageWithCondition(pageNo, pageRecord, condition, hql_count, hql_list);
	}

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
		reserveDao.updateWithCondition(hql, condition);
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
			if (!isAlreadyReserve(reserveInfo)) {
				if (isCurrentPeopleSmallerThanMaxPeople(reserveInfo)) {
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
			if (!isAlreadyReserve(reserveInfo))
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
	 * @return true �Ѿ�ԤԼ��  false δԤԼ
	 */
	public boolean isAlreadyReserve(ReserveInfo reserveInfo) {

		String hql = "from ReserveInfo r where r.username=? and r.lectureId=?";
		condition.clear();
		condition.add(reserveInfo.getUsername());
		condition.add(reserveInfo.getLectureId());
		Object obj = reserveDao.query(hql, condition);
		condition.clear();
		if (obj == null)
			return false;
		else
			return true;
	}

	/**
	 * ��鵱ǰ�������Ƿ�С�������������
	 * 
	 * @param reserveInfo
	 * @return true ���Խ���ԤԼ false �����Խ���ԤԼ
	 * 
	 */
	public boolean isCurrentPeopleSmallerThanMaxPeople(ReserveInfo reserveInfo) {
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
