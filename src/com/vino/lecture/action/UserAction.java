package com.vino.lecture.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.vino.lecture.domain.PageBean;
import com.vino.lecture.domain.User;

public class UserAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;// 输入用户的id,在删除讲座方法中使用
	private List<User> users;
	private List<Long> ids;
	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}
	private PageBean<User> pageBean;
	private Map<String,String> resultMap=new HashMap<String, String>();
	public Map<String, String> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, String> resultMap) {
		this.resultMap = resultMap;
	}

	public PageBean<User> getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean<User> pageBean) {
		this.pageBean = pageBean;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String addUser() throws Exception {
		try {
			if(userService.addUser(user).equals("success"))
				resultMap.put("result", "success");
			else if(userService.addUser(user).equals("repeat"))
				resultMap.put("result", "repeat");//用户名重复
		} catch (RuntimeException e) {
			resultMap.put("result", "fail");
		}

		return SUCCESS;
	}

	public String deleteUser() throws Exception {
		try {
			// User user=(User)
			// ActionContext.getContext().getSession().get("user");
			userService.deleteUser(id);
			resultMap.put("result", "success");
			
		} catch (RuntimeException e) {
			resultMap.put("result", "fail");
		}

		return SUCCESS;
	}
	public String deleteUsers() throws Exception {
		try {
			// User user=(User)
			// ActionContext.getContext().getSession().get("user");
			userService.deleteUsers(ids);
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
	public String updateUser() throws Exception {
		try {
			userService.updateUser(user);
			resultMap.put("result", "success");
			//ActionContext.getContext().getSession().put("user", user);
		} catch (RuntimeException e) {
			resultMap.put("result", "fail");
		}

		return SUCCESS;
	}
	public String queryAllUser() throws Exception {
		
			users=userService.queryAllUser();						
		
		return SUCCESS;
	}
	public String pageQueryUser() throws Exception {
		int pageRecord=pageBean.getPageRecord();
		int pageNo=pageBean.getPageNo();
		pageBean=userService.pageQueryUser(pageNo, pageRecord);					
		
	return SUCCESS;
}

}
