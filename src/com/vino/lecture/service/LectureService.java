package com.vino.lecture.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vino.lecture.domain.LectureInfo;
import com.vino.lecture.domain.PageBean;
import com.vino.lecture.domain.User;
import com.vino.lecture.utils.DateConvertUtils;

@Service
public class LectureService extends BaseService {
	private List<Object> condition;
	/**
	 * ��ѯ���еĽ�����Ϣ
	 * 
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<LectureInfo> queryAllLecture() {
		String hql = "from LectureInfo";
		return lectureDao.query(hql);
	}

	/**
	 * ��ѯ����ԤԼ�Ľ��� available ��0ʱΪ��ԤԼ��
	 * 
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<LectureInfo> queryAvailableLecture() {
		String hql = "from LectureInfo l where l.available>0";
		return lectureDao.query(hql);
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
		condition = new ArrayList<Object>();
		condition.add(user.getUsername());
		lectureDao.queryWithCondition(hql, condition);
		return lectureDao.queryWithCondition(hql, condition);
	}
	 /**
	  * ͨ��id����ѯ����
	  * @param id
	  * @return
	  */
		@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
		public LectureInfo queryLectureById(long id) {

			String hql = "from LectureInfo l where l.id=?";
			condition = new ArrayList<Object>();
			condition.add(id);
			
			return (LectureInfo) lectureDao.query(hql, condition);
		}
	
	/**
	 * ��ҳ��ѯ
	 * @param pageNo ��ǰҳ��
	 * @param pageRecord ÿҳ��¼��
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public PageBean<LectureInfo> pageQuery(int pageNo,int pageRecord) {		
		return lectureDao.queryPage(pageNo, pageRecord,"LectureInfo");		
	}
	
	/**
	 * ��ҳ��ѯ��ԤԼ�Ľ���
	 * @param pageNo
	 * @param pageRecord
	 * @return PageBean
	 */
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public PageBean<LectureInfo> pageQueryAvailable(int pageNo,int pageRecord) {
		
		String hql_count="select count(*) from LectureInfo l where l.available>?";
		String hql_list="from LectureInfo l where l.available>?";
		condition=new ArrayList<Object>();
		condition.add(0);
		return lectureDao.queryPageWithCondition(pageNo, pageRecord, condition, hql_count, hql_list);		
	}
	/**
	 * ��ҳ��ѯ�û���ԤԼ�Ľ���
	 * @param pageNo
	 * @param pageRecord
	 * @param user
	 * @return PageBean
	 */
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public PageBean<LectureInfo> pageQueryReserved(int pageNo,int pageRecord,User user) {
		
		String hql_count="select count(*) from LectureInfo l where l.id in (select lectureId from ReserveInfo r where r.username=?)";
		String hql_list="from LectureInfo l where l.id in (select lectureId from ReserveInfo r where r.username=?)";
		condition = new ArrayList<Object>();
		condition.add(user.getUsername());
		return lectureDao.queryPageWithCondition(pageNo, pageRecord, condition, hql_count, hql_list);		
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
			String hql = "from LectureInfo where title=? and lecturer=? and time=? ";
		 condition = new ArrayList<Object>();
			condition.add(lectureInfo.getTitle());
			condition.add(lectureInfo.getLecturer());
			condition.add(lectureInfo.getTime());
			lectureInfo = (LectureInfo) lectureDao.query(hql, condition);
			timer();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();

		}

	}

	/**
	 * 
	 * @param lectureInfo
	 * 
	 *           
	 * @throws RuntimeException
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void updateLecture(LectureInfo lectureInfo)
			throws RuntimeException {
		String hql = "update LectureInfo set title=?,lecturer=?,time=?,address=?"
				+ ",maxPeople=?,available=?,content=? where id=?";
		 condition = new ArrayList<Object>();
		condition.add(lectureInfo.getTitle());
		condition.add(lectureInfo.getLecturer());
		condition.add(lectureInfo.getTime());
		condition.add(lectureInfo.getAddress());
		condition.add(lectureInfo.getMaxPeople());
		condition.add(lectureInfo.getAvailable());
		condition.add(lectureInfo.getContent());
		condition.add(lectureInfo.getId());
		try {
			lectureDao.updateWithCondition(hql, condition);
			timer();
			
		} catch (Exception e) {
			e.printStackTrace();
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
			lectureDao.delete(LectureInfo.class, id);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	/**
	 * ����һ����ʱ��
	 * @param lectureInfo
	 * @throws ParseException
	 */
	public void timer(final LectureInfo lectureInfo) throws ParseException {
		/*
		 * ҵ���߼��� ֻ��Ӻ͸��µ�ʱ�����ö�ʱ�� �����ݿ���ѡȡ����available>0��lectureinfo
		 * lectureinfo.getTime
		 * �������timer��ʱ��Ϊtime-2������lectureinfo.setAvailable=0;
		 */
		String time = null;
		time = lectureInfo.getTime();
		Date date = DateConvertUtils.StringToDate(time);

		/*
		 * ��ʱ����ʱ���ѹ�������ִ��
		 */
		final Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				List<Object> condition = new ArrayList<Object>();
				condition.add(lectureInfo.getId());

				lectureDao.updateTimer(
						"update LectureInfo l set l.available=0 where id=?",
						condition);
				System.out.println("ʱ�䵽��ʱ�䵽��");
				timer.cancel();
			}
		}, date);


	}
	/**
	 * ���ö����ʱ��
	 */
	public void timer() throws ParseException {
		/*
		 * ҵ���߼��� ֻ��Ӻ͸��µ�ʱ�����ö�ʱ�� �����ݿ���ѡȡ����available>0��lectureinfo
		 * lectureinfo.getTime
		 * �������timer��ʱ��Ϊtime-2������lectureinfo.setAvailable=0;
		 */
		String time = null;
		String hql = "from LectureInfo l where l.available>0";
		final List<LectureInfo> lectureInfos = lectureDao.query(hql);

		if (lectureInfos != null && lectureInfos.size() > 0) {
			for (int i = 0; i < lectureInfos.size(); i++) {
				time = lectureInfos.get(i).getTime();
				Date date = DateConvertUtils.StringToDate(time);
				/*
				 * ��ʱ����ʱ���ѹ�������ִ�� ��ʱ����������������ɾ���ˣ���ִ�ж�ʱ�������ݵ��ǲ�������쳣
				 */
				final Timer timer = new Timer();
				timer.schedule(new LectureTimerTask(i, lectureInfos,
						lectureDao, timer), date);

			}
		}

	}

}
