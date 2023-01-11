package kr.or.ddit.auth;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MemberVOWrapper;

public class GeneratePrincipalFilter implements Filter{
//	 프린시펄객체를 만들어야하는데, 언제 만들어야하냐면 현재 사용자가 인증된 사용자일 때, (세션에 어스멤버가 있을 때)

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request; //zotmxld
		HttpSession session = req.getSession(false);
		MemberVO authMember = null;
		if(session!=null) {
			authMember = (MemberVO) session.getAttribute("authMember");
		}
		if(authMember!=null) { //어스멤버가 멤버가 아닐 때
			//요청이 널로 들어오는 것에 대한 프린시펄을 만들어줘야한다.
//			MemberVOWrapper wrapper = new MemberVOWrapper(authMember); //어스멤버는 프린시펄 안에 들어있는 것이 되었다. wrapper가 곧 유저 프린시펄이 됨. 가급적 지역변수를 만들지 않으려고 지웠음
			//wrapper가 모든 걸 소유하고 있으면서 memId까지 가지고 있다
//			req.set //req에 넣어야 프론트, 핸들러에 다 영향을 받을 수 있다, req자체를 건드려서 셋터를 넣어선 안된다. -> 어댑터 사용 개념이 필요해짐
//			new HttpServletRequestWrapper(req); // 이렇게 생성하면 원본이랑 똑같다.
			HttpServletRequest modifiedReq = new HttpServletRequestWrapper(req) {
				//겟유저프린시펄이 널을 받환하고 있어서
				@Override
				public Principal getUserPrincipal() {
//					return super.getUserPrincipal(); 이걸 지우고 
					HttpServletRequest adaptee = (HttpServletRequest) getRequest(); //어댑터 안에서 어댑티를 꺼내는 것과 같다
					HttpSession session = adaptee.getSession(false); //아직 세션이 없으면 만들지 말라는 의미다
//					MemberVO realMember = (MemberVO) adaptee.getSession().getAttribute("authMember");
					if(session!=null) {
						MemberVO realMember = (MemberVO) session.getAttribute("authMember");
//						return wrapper; //우리가 만든 웨퍼로 반환하면 우리가 원하는 의도로 값을 반환시킬 수 있다
						return new MemberVOWrapper(realMember); 
					}else {
						return super.getUserPrincipal();
					}
				}
			};
//			chain.doFilter(request, response);//이렇게 넘기면 안만든거나 마찬가지 
			chain.doFilter(modifiedReq, response);//이렇게 전달하면 중간에 필터링한 것을 넘기는 것이다.
		} else {
			chain.doFilter(request, response); //기존에 있는 원본 요청과 원본 응답을 그대로 넘기면 된다. 멤버니까
			//리퀘스트를 콘트롤러-뷰까지 보내려면 리퀘스트를 바꿔치기 해야한다.
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
