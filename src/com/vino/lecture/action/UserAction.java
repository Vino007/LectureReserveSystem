package com.vino.lecture.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.vino.lecture.domain.User;

public class UserAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;// 输入用户的id,在删除讲座方法中使用
	private List<User> users;
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
			userService.addUser(user);
			addActionMessage("添加用户成功");
		} catch (RuntimeException e) {
			addActionMessage("添加用户失败");
		}

		return SUCCESS;
	}

	public String deleteUser() throws Exception {
		try {
			// User user=(User)
			// ActionContext.getContext().getSession().get("user");
			userService.deleteUser(id);
			addActionMessage("删除用户成功");
		} catch (RuntimeException e) {
			addActionMessage("删除用户失败");
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
			addActionMessage("更新用户信息成功");
			ActionContext.getContext().getSession().put("user", user);
		} catch (RuntimeException e) {
			addActionMessage("更新用户信息失败");
		}

		return SUCCESS;
	}
	public String queryAllUser() throws Exception {
		
			users=userService.queryAllUser();						
		
		return SUCCESS;
	}

}
