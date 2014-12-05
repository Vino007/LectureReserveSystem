package com.vino.lecture.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.vino.lecture.domain.Admin;
import com.vino.lecture.domain.PageBean;

@Controller
public class AdminAction extends BaseAction{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private List<Integer> ids;
	public List<Integer> getIds() {
		return ids;
	}
	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}
	private PageBean<Admin> pageBean;
	private Map<String,String> resultMap=new HashMap<String, String>();
	public Map<String, String> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, String> resultMap) {
		this.resultMap = resultMap;
	}

	public PageBean<Admin> getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean<Admin> pageBean) {
		this.pageBean = pageBean;
	}




	public String addAdmin() throws Exception {
		try {
			if(adminService.addAdmin(admin).equals("success"))
				resultMap.put("result", "success");
			else if(adminService.addAdmin(admin).equals("repeat"))
				resultMap.put("result", "repeat");//用户名重复
		} catch (RuntimeException e) {
			resultMap.put("result", "fail");
		}

		return SUCCESS;
	}

	public String deleteAdmin() throws Exception {
		try {
			// User user=(User)
			// ActionContext.getContext().getSession().get("user");
			adminService.deleteAdmin(admin.getId());
			resultMap.put("result", "success");
			
		} catch (RuntimeException e) {
			resultMap.put("result", "fail");
		}

		return SUCCESS;
	}
	public String deleteAdmins() throws Exception {
		try {
			// User user=(User)
			// ActionContext.getContext().getSession().get("user");
			adminService.deleteAdmins(ids);
			resultMap.put("result", "delete_success");
			
		} catch (RuntimeException e) {
			resultMap.put("result", "delete_fail");
		}

		return SUCCESS;
	}

	/**
	 * 从表单(从session中获取user作为默认参数)中传递user
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateAdmin() throws Exception {
		try {
			adminService.updateAdmin(admin);
			resultMap.put("result", "success");
			ActionContext.getContext().getSession().put("admin", admin);
		} catch (RuntimeException e) {
			resultMap.put("result", "fail");
		}

		return SUCCESS;
	}

	public String pageQueryAdmin() throws Exception {
		int pageRecord=pageBean.getPageRecord();
		int pageNo=pageBean.getPageNo();
		pageBean=adminService.pageQueryAdmin(pageNo, pageRecord);					
		
	return SUCCESS;
}
	
	
}
