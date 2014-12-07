package com.vino.lecture.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.vino.lecture.domain.Admin;
import com.vino.lecture.domain.User;

public class LoginInterceptor extends AbstractInterceptor {

	/**
	 * 登录拦截器，禁止用户不登陆直接访问后台
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		User user=(User) ActionContext.getContext().getSession().get("user");
		Admin admin=(Admin) ActionContext.getContext().getSession().get("admin");
		if(user==null&&admin==null)
		return "login";//返回到login视图，login在action中已经配置
		else{
			String result=invocation.invoke();//调用action或者是调用下一个拦截器，返回的字符串为action的执行结果	
			return result;
		}
		
	}

}
