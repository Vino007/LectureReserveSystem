package com.vino.lecture.action;

import java.util.List;
import java.util.Map;
import com.opensymphony.xwork2.ActionContext;
import com.vino.lecture.domain.LectureInfo;
import com.vino.lecture.domain.ReserveInfo;

public class LectureAction extends BaseAction {
	/**
	 * lecture��صĲ����Ŀ�����
	 */
	// ������setter��getter����ʹ��ongl����ֱ����jsp��������
	private static final long serialVersionUID = 1L;
	private List<LectureInfo> lectureInfos;// ������Ϣ�б�
	private LectureInfo lectureInfo;
	private ReserveInfo reserveInfo;// ע�룬Ԥ����Ϣ
	private long id;// ���뽲����id,��ɾ������������ʹ��

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
	 * ��ѯȫ������
	 * 
	 * @return
	 * @throws Exception
	 */
	public String queryAllLecture() throws Exception {
		lectureInfos = lectureService.queryAllLecture();
		return SUCCESS;
	}

	/**
	 * ��ѯ��ԤԼ�Ľ���
	 * 
	 * @return
	 * @throws Exception
	 */
	public String queryAvailableLecture() throws Exception {
		lectureInfos = lectureService.queryAvailableLecture();
		return SUCCESS;
	}

	/**
	 * ��ӽ���
	 * 
	 * @return ���ص�������
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
	 * �޸Ľ�����Ϣ
	 * 
	 * @return ���ص�������
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
	 * ɾ������
	 * 
	 * @return ����������
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
	 * Ԥ������
	 * 
	 * @return
	 * @throws Exception
	 */
	public String reserveLecture() throws Exception {
		System.out.println("reserveLectureִ��");
		if (lectureService.reserveLecture(reserveInfo).equals("success"))
			addActionMessage("success");
		else if (lectureService.reserveLecture(reserveInfo).equals("fail"))
			addActionMessage("fail");
		else if (lectureService.reserveLecture(reserveInfo).equals("repeat"))
			addActionMessage("�Ѿ�ԤԼ����");
		return SUCCESS;
	}

	public String cancelReserveLecture() throws Exception {
		System.out.println("cancelreserveLectureִ��");
		if (lectureService.cancelReserveLecture(reserveInfo))
			addActionMessage("success");
		else
			addActionMessage("fail");

		return SUCCESS;
	}

}
