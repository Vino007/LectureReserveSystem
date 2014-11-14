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
		addActionMessage("����û��ɹ�");
		}catch(RuntimeException e){
			addActionMessage("����û�ʧ��");
		}
		
		return SUCCESS;
	}
	public String deleteUser() throws Exception{
		try {
		userService.deleteUser(user.getUsername());
		addActionMessage("ɾ���û��ɹ�");
		}catch(RuntimeException e){
			addActionMessage("ɾ���û�ʧ��");
		}
		
		return SUCCESS;
	}
	/**
	 * �ӱ�(��session�л�ȡuser��ΪĬ�ϲ���)�д���user
	 * @return
	 * @throws Exception
	 */
	public String updateUser() throws Exception{
		try {
		userService.updateUser(user);
		addActionMessage("�����û���Ϣ�ɹ�");
		ActionContext.getContext().getSession().put("user", user);
		}catch(RuntimeException e){
			addActionMessage("�����û���Ϣʧ��");
		}
		
		return SUCCESS;
	}
	
}
