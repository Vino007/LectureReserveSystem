package com.vino.lecture.action;

import com.opensymphony.xwork2.ActionContext;


public class LoginAction extends BaseAction  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String type; //��¼������
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String execute() throws Exception {
		if(type.equals("user")){
		user=userService.login(user);
		if(user==null){
			addActionError("�û����������");
			return LOGIN;
		}
		else{
			 ActionContext.getContext().getSession().put("user", user);
			 return SUCCESS;
		}
				
		}
		else {
			admin=adminService.login(user.getUsername(),user.getPassword());
			if(admin==null){
				addActionError("�û����������");
				return LOGIN;
			}
			else{
				 ActionContext.getContext().getSession().put("admin", admin);
				 return SUCCESS;
			}
		}
		
		
	}

	

}
