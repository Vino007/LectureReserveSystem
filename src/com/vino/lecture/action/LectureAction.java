package com.vino.lecture.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	//���ajax����Ľ��
	private Map<String,String> resultMap=new HashMap<String, String>();
	public Map<String, String> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, String> resultMap) {
		this.resultMap = resultMap;
	}


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
		pageBean = lectureService.pageQuery(pageBean.getPageNo(),
				pageBean.getPageRecord());
		return SUCCESS;
	}

	/**
	 * ��ѯ��ԤԼ�Ľ���
	 * 
	 * @return
	 * @throws Exception
	 */
	public String queryAvailableLecture() throws Exception {
		// lectureInfos = lectureService.queryAvailableLecture();
		pageBean = lectureService.pageQueryAvailable(pageBean.getPageNo(),
				pageBean.getPageRecord());
		
		return SUCCESS;
	}

	/**
	 * ��ѯ�Ѿ�ԤԼ�Ľ���
	 * 
	 * @return
	 * @throws Exception
	 */
	public String queryReservedLecture() throws Exception {
		
		pageBean = lectureService.pageQueryReserved(pageBean.getPageNo(),
				pageBean.getPageRecord(), user);
		return SUCCESS;
	}
	public String queryLectureById() throws Exception {

		// lectureInfos = lectureService.queryReservedLecture(user);
		
		lectureInfo=lectureService.queryLectureById(lectureInfo.getId());
		return SUCCESS;
	}
	
	
	public String queryReserveList() throws Exception {
	
		pageBean=reserveService.queryReserveList(pageBean.getPageNo(), 10, reserveInfo.getLectureId());
		return SUCCESS;
	}


	/**
	 * ��ӽ���
	 * 
	 * @return ���ص�������
	 * @throws Exception
	 */
	
	public String addLecture() throws Exception {


		
		try {
			lectureService.addLecture(lectureInfo);
			resultMap.put("result","success");
	
		} catch (RuntimeException e) {

			resultMap.put("result","fail");
		}
		return SUCCESS;
	}

	/**
	 * �޸Ľ�����Ϣ
	 * 
	 * @return ���ص�������
	 * @throws Exception
	 */

	public String updateLecture() throws Exception {
	
		try {
			lectureService.updateLecture(lectureInfo);	
			resultMap.put("result","update_success");
		} catch (RuntimeException e) {		
			resultMap.put("result","update_fail");
		}
		return SUCCESS;
	}

	/**
	 * ɾ������
	 * 
	 * @return ����������
	 * @throws Exception
	 */

	public String deleteLecture() throws Exception {
	//	Map request = (Map) ActionContext.getContext().get("request");
		try {
			lectureService.deleteLecture(lectureInfo.getId());
		//	request.put("Result", "success");
			resultMap.put("result","delete_success");
		} catch (RuntimeException e) {
		//	request.put("Result", "fail");
			resultMap.put("result","delete_fail");
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

		try {
			if (reserveService.reserveLecture(reserveInfo).equals("success")) {
				addActionMessage("ԤԼ�ɹ�");
				
				resultMap.put("result","reserve_success");
		
				reserveService.updateCurrentPeople(reserveInfo);// ������������
			} else if (reserveService.reserveLecture(reserveInfo).equals(
					"repeat")) {
				addActionMessage("�Ѿ�ԤԼ����");
				
				resultMap.put("result","repeat");
		
			} else if (reserveService.reserveLecture(reserveInfo).equals(
					"overflow")) {
				addActionMessage("overflow");
				
				resultMap.put("result","overflow");
		
			}
		} catch (RuntimeException e) {
			addActionMessage("ԤԼʧ��");
			
			resultMap.put("result","fail");
		
		}
		return SUCCESS;
	}

	public String cancelReserveLecture() throws Exception {


		System.out.println("cancelreserveLectureִ��");
		try {
			if (reserveService.cancelReserveLecture(reserveInfo).equals(
					"success")) {
				addActionMessage("ȡ��ԤԼ�ɹ�");		
				resultMap.put("result", "cancel_success");
				reserveService.updateCurrentPeople(reserveInfo);// ������������
			} else if (reserveService.cancelReserveLecture(reserveInfo).equals(
					"alread_cancel")) {
				addActionMessage("�Ѿ�ȡ����");
				resultMap.put("result", "alread_cancel");
		
			}
		} catch (RuntimeException e) {
			addActionMessage("ȡ��ԤԼʧ��");		
			resultMap.put("result", "fail");
		}
		return SUCCESS;
	}

}
