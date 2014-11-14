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
			addActionError("�û����������");
		return LOGIN;
		}
		else{
			//��user���浽session���У��������action��ȡuser
			ActionContext.getContext().getSession().put("user", user);
		return SUCCESS;}
	}

	

}
