package com.vino.lecture.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.vino.lecture.dao.LectureDao;
import com.vino.lecture.domain.LectureInfo;

public class LectureTimerTask extends TimerTask{
	private int index;
	private List<LectureInfo> lectureInfos;
	private LectureDao lectureDao;
	private Timer timer;
	public LectureTimerTask(int index,List<LectureInfo> lectureInfos,LectureDao lectureDao,Timer timer) {
		this.index=index;
		this.lectureInfos=lectureInfos;
		this.lectureDao=lectureDao;
		this.timer=timer;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		List<Object> condition=new ArrayList<Object>();
		condition.add(lectureInfos.get(index).getId());
		
		lectureDao.updateTimer("update LectureInfo l set l.available=0 where id=?",condition);
		System.out.println("时间到啦时间到啦");
		timer.cancel();//销毁定时器
	}

}
