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
	 * 分页查询预约清单
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
	 * 预定讲座，先判断该用户是否已经预约过了，再进行预约
	 * 
	 * @param reserveInfo
	 *            预约信息
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
					return "overflow";// 超过人数
			} else
				return "repeat";// 重复预约
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	/**
	 * 取消预约
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
	 * 检查当前用户是否预约过改讲座
	 * 
	 * @param reserveInfo
	 * @return true 已经预约过  false 未预约
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
	 * 检查当前的人数是否小于最大容纳人数
	 * 
	 * @param reserveInfo
	 * @return true 可以进行预约 false 不可以进行预约
	 * 
	 */
	public boolean isCurrentPeopleSmallerThanMaxPeople(ReserveInfo reserveInfo) {
		String hql = "select  currentPeople,maxPeople  from LectureInfo l where l.id=?";
		condition.clear();
		condition.add(reserveInfo.getLectureId());
		Object[] obj = (Object[]) reserveDao.query(hql, condition);
		condition.clear();
		// 当查询的字段有两个或者两个以上的时候，list里封装的是object[]对象
		int currentPeople = (int) obj[0];
		int maxPeople = (int) obj[1];
		if (currentPeople < maxPeople)
			return true;
		else
			return false;

	}

}
