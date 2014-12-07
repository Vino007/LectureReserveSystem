package com.vino.lecture.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.vino.lecture.domain.Admin;
import com.vino.lecture.domain.User;

public class LoginInterceptor extends AbstractInterceptor {

	/**
	 * ��¼����������ֹ�û�����½ֱ�ӷ��ʺ�̨
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		User user=(User) ActionContext.getContext().getSession().get("user");
		Admin admin=(Admin) ActionContext.getContext().getSession().get("admin");
		if(user==null&&admin==null)
		return "login";//���ص�login��ͼ��login��action���Ѿ�����
		else{
			String result=invocation.invoke();//����action�����ǵ�����һ�������������ص��ַ���Ϊaction��ִ�н��	
			return result;
		}
		
	}

}
