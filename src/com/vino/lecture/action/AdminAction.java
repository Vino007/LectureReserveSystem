package com.vino.lecture.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.vino.lecture.domain.Admin;
import com.vino.lecture.domain.PageBean;


public class AdminAction extends BaseAction{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private List<Integer> ids=new ArrayList<Integer>();
	private PageBean<Admin> pageBean;
	private Map<String,String> resultMap=new HashMap<String, String>();
	public List<Integer> getIds() {
		return ids;
	}
	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}
	
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
			System.out.println(admin.getUsername());
			System.out.println(admin.getPassword());
			if(adminService==null)
				System.out.println("adminService==null");
			String result=adminService.addAdmin(admin);
			if(result.equals("success"))
				resultMap.put("result", "success");
			else if(result.equals("repeat"))
				resultMap.put("result", "repeat");//用户名重复
		} catch (RuntimeException e) {
			e.printStackTrace();
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
