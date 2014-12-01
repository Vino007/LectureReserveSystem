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
	 * 查询所有的讲座信息
	 * 
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<LectureInfo> queryAllLecture() {
		String hql = "from LectureInfo";
		return lectureDao.query(hql);
	}

	/**
	 * 查询允许预约的讲座 available 非0时为可预约！
	 * 
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<LectureInfo> queryAvailableLecture() {
		String hql = "from LectureInfo l where l.available>0";
		return lectureDao.query(hql);
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
		condition = new ArrayList<Object>();
		condition.add(user.getUsername());
		lectureDao.queryWithCondition(hql, condition);
		return lectureDao.queryWithCondition(hql, condition);
	}
	 /**
	  * 通过id来查询讲座
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
	 * 分页查询
	 * @param pageNo 当前页码
	 * @param pageRecord 每页记录数
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public PageBean<LectureInfo> pageQuery(int pageNo,int pageRecord) {		
		return lectureDao.queryPage(pageNo, pageRecord,"LectureInfo");		
	}
	
	/**
	 * 分页查询可预约的讲座
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
	 * 分页查询用户已预约的讲座
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
	 * 根据id删除讲座
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
	 * 设置一个定时器
	 * @param lectureInfo
	 * @throws ParseException
	 */
	public void timer(final LectureInfo lectureInfo) throws ParseException {
		/*
		 * 业务逻辑： 只添加和更新的时候设置定时器 从数据库中选取所有available>0的lectureinfo
		 * lectureinfo.getTime
		 * 启动多个timer：时间为time-2，任务：lectureinfo.setAvailable=0;
		 */
		String time = null;
		time = lectureInfo.getTime();
		Date date = DateConvertUtils.StringToDate(time);

		/*
		 * 定时，若时间已过则立即执行
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
				System.out.println("时间到啦时间到啦");
				timer.cancel();
			}
		}, date);


	}
	/**
	 * 设置多个定时器
	 */
	public void timer() throws ParseException {
		/*
		 * 业务逻辑： 只添加和更新的时候设置定时器 从数据库中选取所有available>0的lectureinfo
		 * lectureinfo.getTime
		 * 启动多个timer：时间为time-2，任务：lectureinfo.setAvailable=0;
		 */
		String time = null;
		String hql = "from LectureInfo l where l.available>0";
		final List<LectureInfo> lectureInfos = lectureDao.query(hql);

		if (lectureInfos != null && lectureInfos.size() > 0) {
			for (int i = 0; i < lectureInfos.size(); i++) {
				time = lectureInfos.get(i).getTime();
				Date date = DateConvertUtils.StringToDate(time);
				/*
				 * 定时，若时间已过则立即执行 定时器启动后任务若被删除了，会执行定时器的内容但是不会出现异常
				 */
				final Timer timer = new Timer();
				timer.schedule(new LectureTimerTask(i, lectureInfos,
						lectureDao, timer), date);

			}
		}

	}

}
