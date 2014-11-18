package com.vino.lecture.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.vino.lecture.domain.PageBean;
import com.vino.lecture.domain.User;

public class UserAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;// �����û���id,��ɾ������������ʹ��
	private List<User> users;
	private PageBean<User> pageBean;
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
			addActionMessage("����û��ɹ�");
			else if(userService.addUser(user).equals("repeat"))
				addActionMessage("�û����ظ�");
		} catch (RuntimeException e) {
			addActionMessage("����û�ʧ��");
		}

		return SUCCESS;
	}

	public String deleteUser() throws Exception {
		try {
			// User user=(User)
			// ActionContext.getContext().getSession().get("user");
			userService.deleteUser(id);
			addActionMessage("ɾ���û��ɹ�");
		} catch (RuntimeException e) {
			addActionMessage("ɾ���û�ʧ��");
		}

		return SUCCESS;
	}

	/**
	 * �ӱ�(��session�л�ȡuser��ΪĬ�ϲ���)�д���user
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateUser() throws Exception {
		try {
			userService.updateUser(user);
			addActionMessage("�����û���Ϣ�ɹ�");
			ActionContext.getContext().getSession().put("user", user);
		} catch (RuntimeException e) {
			addActionMessage("�����û���Ϣʧ��");
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
