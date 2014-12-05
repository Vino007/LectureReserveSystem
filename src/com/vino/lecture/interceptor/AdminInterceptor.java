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
		return "login";//返回到login视图，login在action中已经配置
		else{
			String result=invocation.invoke();//调用action或者是调用下一个拦截器，返回的字符串为action的执行结果	
			return result;
		}
	}

}
