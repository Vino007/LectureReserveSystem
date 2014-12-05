package com.vino.lecture.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.vino.lecture.domain.Admin;


public class AdminInterceptor  extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Admin admin=(Admin) ActionContext.getContext().getSession().get("admin");
		if(admin==null)
		return "login";//���ص�login��ͼ��login��action���Ѿ�����
		else{
			String result=invocation.invoke();//����action�����ǵ�����һ�������������ص��ַ���Ϊaction��ִ�н��	
			return result;
		}
	}

}
