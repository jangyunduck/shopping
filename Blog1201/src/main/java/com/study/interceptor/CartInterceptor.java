package com.study.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.study.model.MemberrVO;

public class CartInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
	HttpSession session = request.getSession();
		
		MemberrVO mvo = (MemberrVO)session.getAttribute("memberr");
		
		if(mvo == null) {
			response.sendRedirect("/memberr/mains");
			return false;
		} else {
			return true;
		}
		
	}

	
	
}
