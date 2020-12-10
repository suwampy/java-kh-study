package com.kh.websocket.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebChattingController {
	
	@RequestMapping(value="/chatting.do", method=RequestMethod.POST)
	public ModelAndView chat(ModelAndView mv, String userName,
			HttpServletRequest request, HttpSession session) {
		
		// 세션에 사용할 대화명을 저장
		session.setAttribute("userName", userName);
		System.out.println("controller : " + session.getAttribute("userName"));
		
		// 클라이언트 구분을 위한 ip 확인
		String host = request.getRemoteAddr();
		System.out.println("host 주소 : " + host);
		mv.addObject("host", host);
		
		// 출력할 뷰 선택
		mv.setViewName("chat/chattingView");
		
		return mv;
	}

}





