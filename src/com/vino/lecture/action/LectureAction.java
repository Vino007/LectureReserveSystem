package com.vino.lecture.action;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.vino.lecture.domain.LectureInfo;



public class LectureAction extends BaseAction{
	/**
	 * 
	 */
	//定义了setter和getter才能使用ongl或者直接与jsp交互数据
	private static final long serialVersionUID = 1L;
	private List<LectureInfo> lectureInfos;
	private LectureInfo lectureInfo;
	private long id;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public LectureInfo getLectureInfo() {
		return lectureInfo;
	}
	public void setLectureInfo(LectureInfo lectureInfo) {
		this.lectureInfo = lectureInfo;
	}
	public List<LectureInfo> getLectureInfos() {
		return lectureInfos;
	}
	public void setLectureInfos(List<LectureInfo> lectureInfos) {
		this.lectureInfos = lectureInfos;
	}
	@Override
	public String execute() throws Exception {
		lectureInfos=lectureService.queryAllLecture();
		return SUCCESS;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String addLecture() throws Exception{
		
		Map request=(Map) ActionContext.getContext().get("request");
		if(lectureService.addLecture(lectureInfo)){			
			request.put("Result", "success");}
		else
			request.put("Result", "fail");
		return SUCCESS;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String updateLecture() throws Exception{
		Map request=(Map) ActionContext.getContext().get("request");
		if(lectureService.updateLecture(lectureInfo,id)){			
			request.put("Result", "success");}
		else
			request.put("Result", "fail");
		return SUCCESS;
	}
	public String deleteLecture() throws Exception{
		if(lectureService.deleteLecture(id))
			addActionMessage("success");
		else
			addActionMessage("fail");
		return SUCCESS;
	}
	

}
