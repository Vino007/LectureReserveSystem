package com.vino.lecture.action;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.vino.lecture.domain.LectureInfo;
import com.vino.lecture.domain.PageBean;
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
		 pageBean=lectureService.pageQuery(pageBean.getPageNo(),pageBean.getPageRecord());
		return SUCCESS;
	}

	/**
	 * ��ѯ��ԤԼ�Ľ���
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
	 * ��ѯ�Ѿ�ԤԼ�Ľ���
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
	 * ��ӽ���
	 * 
	 * @return ���ص�������
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
	 * �޸Ľ�����Ϣ
	 * 
	 * @return ���ص�������
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
	 * ɾ������
	 * 
	 * @return ����������
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
	 * Ԥ������
	 * 
	 * @return
	 * @throws Exception
	 */
	public String reserveLecture() throws Exception {
		System.out.println("reserveLectureִ��");	
		try{
			if (reserveService.reserveLecture(reserveInfo).equals("success")){
				addActionMessage("ԤԼ�ɹ�");
				reserveService.updateCurrentPeople(reserveInfo);//������������
			}			
			else if (reserveService.reserveLecture(reserveInfo).equals("repeat"))
				addActionMessage("�Ѿ�ԤԼ����");
			else if (reserveService.reserveLecture(reserveInfo).equals("overflow"))
				addActionMessage("ԤԼ��������");
		}catch(RuntimeException e){
			    addActionMessage("ԤԼʧ��");
		}		
		return SUCCESS;
	}

	public String cancelReserveLecture() throws Exception {
		System.out.println("cancelreserveLectureִ��");
		try{
		if (reserveService.cancelReserveLecture(reserveInfo).equals("success")){
			addActionMessage("ȡ��ԤԼ�ɹ�");
			reserveService.updateCurrentPeople(reserveInfo);//������������
		}		
		else if(reserveService.cancelReserveLecture(reserveInfo).equals("alread_cancel"))
			addActionMessage("�Ѿ�ȡ����");
		}catch(RuntimeException e){
			addActionMessage("ȡ��ԤԼʧ��");
		}
		return SUCCESS;
	}

}
