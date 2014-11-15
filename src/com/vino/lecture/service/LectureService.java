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
	 * ��ѯ���еĽ�����Ϣ
	 * 
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<LectureInfo> queryAllLecture() {
		String hql = "from LectureInfo";
		return lectureDao.queryAll(hql);
	}

	/**
	 * ��ѯ����ԤԼ�Ľ��� available ��0ʱΪ��ԤԼ��
	 * 
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<LectureInfo> queryAvailableLecture() {
		String hql = "from LectureInfo l where l.available>0";
		return lectureDao.queryAll(hql);
	}

	/**
	 * ��ѯ�û���ԤԼ�Ľ��� ʹ���Ӳ�ѯ��
	 * 
	 * @param user
	 *            �û���Ϣ
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
	 * ��ӽ���
	 * 
	 * @param lectureInfo
	 * @throws RuntimeException
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addLecture(LectureInfo lectureInfo) throws RuntimeException {

		lectureInfo.setAvailable(1);// Ĭ�������Ľ�������ԤԼ
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
	 *            ���������id��������lectureInfo�е�id
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
	 * ����idɾ������
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
