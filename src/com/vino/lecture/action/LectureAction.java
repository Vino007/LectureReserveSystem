package com.vino.lecture.action;

import java.util.List;
import java.util.Map;
import com.opensymphony.xwork2.ActionContext;
import com.vino.lecture.domain.LectureInfo;
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
		lectureInfos = lectureService.queryAllLecture();
		return SUCCESS;
	}

	/**
	 * 查询可预约的讲座
	 * 
	 * @return
	 * @throws Exception
	 */
	public String queryAvailableLecture() throws Exception {
		lectureInfos = lectureService.queryAvailableLecture();
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
		if (lectureService.addLecture(lectureInfo)) {
			request.put("Result", "success");
		} else
			request.put("Result", "fail");
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
		if (lectureService.updateLecture(lectureInfo, id)) {
			request.put("Result", "success");
		} else
			request.put("Result", "fail");
		return SUCCESS;
	}

	/**
	 * 删除讲座
	 * 
	 * @return 返回主界面
	 * @throws Exception
	 */
	public String deleteLecture() throws Exception {
		if (lectureService.deleteLecture(id))
			addActionMessage("success");
		else
			addActionMessage("fail");
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
		if (lectureService.reserveLecture(reserveInfo).equals("success"))
			addActionMessage("success");
		else if (lectureService.reserveLecture(reserveInfo).equals("fail"))
			addActionMessage("fail");
		else if (lectureService.reserveLecture(reserveInfo).equals("repeat"))
			addActionMessage("已经预约过了");
		return SUCCESS;
	}

	public String cancelReserveLecture() throws Exception {
		System.out.println("cancelreserveLecture执行");
		if (lectureService.cancelReserveLecture(reserveInfo))
			addActionMessage("success");
		else
			addActionMessage("fail");

		return SUCCESS;
	}

}
