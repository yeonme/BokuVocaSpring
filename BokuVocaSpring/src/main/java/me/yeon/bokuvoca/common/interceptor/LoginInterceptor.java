package me.yeon.bokuvoca.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	//customer/updateForm
	
	//컨트롤러의 메서드가 실행되기 전에 처리되는 부분
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//로그인이 되어 있는지 판단해서
		//세션 획득
		HttpSession session = request.getSession();
		String loginId = (String)session.getAttribute("userName");
		if(loginId == null){
			//비로그인
			//무조건 절대 경로를 지정해야 한다.
			String path = request.getContextPath();
			response.sendRedirect(path+"/login");
			
			//포워드형: RequestDispatcher rd =  
			return false;
		}
		
		//로그인이 되어 있지 않은 경우 로그인 폼으로 이동
		//로그인이 되어 있을 경우
		//원래 요청 주소로 이동
		return super.preHandle(request, response, handler);
	}
	
}