package com.vino.lecture.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vino.lecture.domain.LectureInfo;
import com.vino.lecture.domain.User;

@Service
public class LectureService extends BaseService {
	/**
	 * 查询所有的讲座信息
	 * 
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<LectureInfo> queryAllLecture() {
		String hql = "from LectureInfo";
		return lectureDao.queryAll(hql);
	}

	/**
	 * 查询允许预约的讲座 available 非0时为可预约！
	 * 
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<LectureInfo> queryAvailableLecture() {
		String hql = "from LectureInfo l where l.available>0";
		return lectureDao.queryAll(hql);
	}

	/**
	 * 查询用户已预约的讲座 使用子查询！
	 * 
	 * @param user
	 *            用户信息
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<LectureInfo> queryReservedLecture(User user) {

		String hql = "from LectureInfo l where l.id in (select lectureId from ReserveInfo r where r.username=?)";
		List<Object> condition = new ArrayList<Object>();
		condition.add(user.getUsername());
		lectureDao.queryList(hql, condition);
		return lectureDao.queryList(hql, condition);
	}

	/**
	 * 添加讲座
	 * 
	 * @param lectureInfo
	 * @throws RuntimeException
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addLecture(LectureInfo lectureInfo) throws RuntimeException {

		lectureInfo.setAvailable(1);// 默认新增的讲座可以预约
		try {
			lectureDao.add(lectureInfo);
		} catch (Exception e) {
			throw new RuntimeException();

		}

	}

	/**
	 * 
	 * @param lectureInfo
	 * @param id
	 *            表单中输入的id，并不是lectureInfo中的id
	 * @throws RuntimeException
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void updateLecture(LectureInfo lectureInfo, long id)
			throws RuntimeException {
		String hql = "update LectureInfo set title=?,lecturer=?,time=?,address=?"
				+ ",maxPeople=?,available=?,content=? where id=?";
		List<Object> condition = new ArrayList<Object>();
		condition.add(lectureInfo.getTitle());
		condition.add(lectureInfo.getLecturer());
		condition.add(lectureInfo.getTime());
		condition.add(lectureInfo.getAddress());
		condition.add(lectureInfo.getMaxPeople());
		condition.add(lectureInfo.getAvailable());
		condition.add(lectureInfo.getContent());
		condition.add(id);
		try {
			lectureDao.update(hql, condition);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	/**
	 * 根据id删除讲座
	 * 
	 * @param id
	 * @throws RuntimeException
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteLecture(long id) throws RuntimeException {
		try {
			lectureDao.delete(LectureInfo.class,id);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

}
