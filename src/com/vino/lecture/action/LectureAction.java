package com.vino.lecture.action;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.vino.lecture.domain.LectureInfo;
import com.vino.lecture.domain.PageBean;
import com.vino.lecture.domain.ReserveInfo;

public class LectureAction extends BaseAction {
	/**
	 * lecture相关的操作的控制类
	 */
	// 定义了setter和getter才能使用ongl或者直接与jsp交互数据
	private static final long serialVersionUID = 1L;
	private List<LectureInfo> lectureInfos;// 讲座信息列表
	private LectureInfo lectureInfo;
	private ReserveInfo reserveInfo;// 注入，预定信息
	@SuppressWarnings("rawtypes")
	private PageBean pageBean;
	@SuppressWarnings("rawtypes")
	public PageBean getPageBean() {
		return pageBean;
	}

	@SuppressWarnings("rawtypes")
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	private long id;// 输入讲座的id,在删除讲座方法中使用

	public ReserveInfo getReserveInfo() {
		return reserveInfo;
	}

	public void setReserveInfo(ReserveInfo reserveInfo) {
		this.reserveInfo = reserveInfo;
	}

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

	/**
	 * 查询全部讲座
	 * 
	 * @return
	 * @throws Exception
	 */

	public String queryAllLecture() throws Exception {
		 pageBean=lectureService.pageQuery(pageBean.getPageNo(),pageBean.getPageRecord());
		return SUCCESS;
	}

	/**
	 * 查询可预约的讲座
	 * 
	 * @return
	 * @throws Exception
	 */
	public String queryAvailableLecture() throws Exception {
		//lectureInfos = lectureService.queryAvailableLecture();
		pageBean=lectureService.pageQueryAvailable(pageBean.getPageNo(), pageBean.getPageRecord());
		return SUCCESS;
	}
	/**
	 * 查询已经预约的讲座
	 * 
	 * @return
	 * @throws Exception
	 */
	public String queryReservedLecture() throws Exception {
		
		//lectureInfos = lectureService.queryReservedLecture(user);
		
		pageBean=lectureService.pageQueryReserved(pageBean.getPageNo(), pageBean.getPageRecord(),user);
		return SUCCESS;
	}

	/**
	 * 添加讲座
	 * 
	 * @return 返回到主界面
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String addLecture() throws Exception {

		Map request = (Map) ActionContext.getContext().get("request");
		
		try{
			lectureService.addLecture(lectureInfo);
			request.put("Result", "success");
		}catch(RuntimeException e){
			request.put("Result", "fail");
		}		
		return SUCCESS;
	}

	/**
	 * 修改讲座信息
	 * 
	 * @return 返回到主界面
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String updateLecture() throws Exception {
		Map request = (Map) ActionContext.getContext().get("request");
		try{
			lectureService.updateLecture(lectureInfo,id);
			request.put("Result", "success");
		}catch(RuntimeException e){
			request.put("Result", "fail");
		}		
		return SUCCESS;
	}

	/**
	 * 删除讲座
	 * 
	 * @return 返回主界面
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String deleteLecture() throws Exception {
		Map request = (Map) ActionContext.getContext().get("request");
		try{
			lectureService.deleteLecture(id);
			request.put("Result", "success");
		}catch(RuntimeException e){
			request.put("Result", "fail");
		}		
		return SUCCESS;
		
	}

	/**
	 * 预定讲座
	 * 
	 * @return
	 * @throws Exception
	 */
	public String reserveLecture() throws Exception {
		System.out.println("reserveLecture执行");	
		try{
			if (reserveService.reserveLecture(reserveInfo).equals("success")){
				addActionMessage("预约成功");
				reserveService.updateCurrentPeople(reserveInfo);//更新现有人数
			}			
			else if (reserveService.reserveLecture(reserveInfo).equals("repeat"))
				addActionMessage("已经预约过了");
			else if (reserveService.reserveLecture(reserveInfo).equals("overflow"))
				addActionMessage("预约人数已满");
		}catch(RuntimeException e){
			    addActionMessage("预约失败");
		}		
		return SUCCESS;
	}

	public String cancelReserveLecture() throws Exception {
		System.out.println("cancelreserveLecture执行");
		try{
		if (reserveService.cancelReserveLecture(reserveInfo).equals("success")){
			addActionMessage("取消预约成功");
			reserveService.updateCurrentPeople(reserveInfo);//更新现有人数
		}		
		else if(reserveService.cancelReserveLecture(reserveInfo).equals("alread_cancel"))
			addActionMessage("已经取消了");
		}catch(RuntimeException e){
			addActionMessage("取消预约失败");
		}
		return SUCCESS;
	}

}
