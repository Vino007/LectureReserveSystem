package com.vino.lecture.action;

import com.opensymphony.xwork2.ActionContext;

public class UserAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String addUser() throws Exception{
		try {
		userService.addUser(user);
		addActionMessage("添加用户成功");
		}catch(RuntimeException e){
			addActionMessage("添加用户失败");
		}
		
		return SUCCESS;
	}
	public String deleteUser() throws Exception{
		try {
		userService.deleteUser(user.getUsername());
		addActionMessage("删除用户成功");
		}catch(RuntimeException e){
			addActionMessage("删除用户失败");
		}
		
		return SUCCESS;
	}
	/**
	 * 从表单(从session中获取user作为默认参数)中传递user
	 * @return
	 * @throws Exception
	 */
	public String updateUser() throws Exception{
		try {
		userService.updateUser(user);
		addActionMessage("更新用户信息成功");
		ActionContext.getContext().getSession().put("user", user);
		}catch(RuntimeException e){
			addActionMessage("更新用户信息失败");
		}
		
		return SUCCESS;
	}
	
}
