package com.kh.spring.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.kh.spring.member.model.vo.Member;

public class LoginInterceptor extends HandlerInterceptorAdapter{
	private Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		
		Member loginUser = (Member)session.getAttribute("loginUser");
		
		if(loginUser == null) {
			logger.info("비로그인 상태에서 [" + request.getRequestURI() + "] 접근하려고 함!!!");
			
			session.setAttribute("msg", "로그인 후 이용할 수 있는 서비스입니다.");
			response.sendRedirect("home.do");
			
			return false;
		
		}
		return super.preHandle(request, response, handler);
	}

}
