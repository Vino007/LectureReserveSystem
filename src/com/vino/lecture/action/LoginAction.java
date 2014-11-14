package com.vino.lecture.action;

import com.opensymphony.xwork2.ActionContext;

public class LoginAction extends BaseAction  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String execute() throws Exception {
		
		user=userService.login(user);
		
		if(user==null){
			addActionError("用户名密码错误");
		return LOGIN;
		}
		else{
			//将user保存到session域中，方便各个action获取user
			ActionContext.getContext().getSession().put("user", user);
		return SUCCESS;}
	}

	

}
