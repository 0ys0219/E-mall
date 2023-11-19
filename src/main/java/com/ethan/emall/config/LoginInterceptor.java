package com.ethan.emall.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ethan.emall.model.Member;
@Component
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("preHandle執行中");

		HttpSession session = request.getSession();

		Member member = (Member) session.getAttribute("member");

		if (member != null) {
			System.out.println("session: "+session);
			System.out.println("帳號為 "+member.getAccount());
			System.out.println("已登入");
			return true;
		}
		System.out.println(member);
		System.out.println("未登入");
		return false;

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("執行postHeandle");
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("執行afterCompletion");
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

}
