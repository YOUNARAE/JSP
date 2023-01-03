package kr.or.ddit.mvc;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.commons.IndexController;
import kr.or.ddit.login.controller.LoginProcessController;
import kr.or.ddit.login.controller.LogoutController;
import kr.or.ddit.member.controller.MemberInsertController;
import kr.or.ddit.member.controller.MemberListController;
import kr.or.ddit.member.controller.MemberViewController;
import kr.or.ddit.mvc.annotation.HandlerAdapter;
import kr.or.ddit.mvc.annotation.HandlerMapping;
import kr.or.ddit.mvc.annotation.RequestMappingHandlerAdapter;
import kr.or.ddit.mvc.annotation.RequestMappingHandlerMapping;
import kr.or.ddit.mvc.annotation.RequestMappingInfo;
import kr.or.ddit.mvc.view.InternalResourceViewResolver;
import kr.or.ddit.mvc.view.ViewResolver;
import kr.or.ddit.prod.controller.ProdInsertContoller;
import kr.or.ddit.prod.controller.ProdListController;

public class DispatcherServlet extends HttpServlet{
	private ViewResolver viewResolver;
	private HandlerMapping handlerMapping;
	private HandlerAdapter handlerAdapter;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config); //라이프사이클 객체라서 싱글톤으로 한번만 실행되면 된다
		viewResolver = new InternalResourceViewResolver("/WEB-INF/views/", ".jsp");
		handlerMapping = new RequestMappingHandlerMapping("kr.or.ddit"); //핸들러 맵핑에 있는 객체에 주소 담기
		handlerAdapter = new RequestMappingHandlerAdapter();
	}
	
	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		super.service(req, res);
//		이 서비스에서는 do 계열 메서드를 사용하지 않겠다는 의미다. 이제부터는 각 웨이터가 접대했다면, 정문에 웨이터를 한명만 놓고,실제적으로 김치찌개를 나르지 않기때문에 do계열의 메서드가 필요없다
		req.setCharacterEncoding("UTF-8");
		
//		String requestURI = req.getRequestURI(); //컨텍스트패스가 포함되어 있
//		requestURI = requestURI.substring(req.getContextPath().length());
		String requestURI = req.getServletPath();
//		--
//		AbstractController controller = null;
//		if("/member/memberList.do".equals(requestURI)) {
//			controller = new MemberListController(); //프론트가 어떤 리스트를 사용할지를 정할 수 있게 해야한다.
//		} else if("/prod/prodList.do".equals(requestURI)) {
//			controller = new ProdListController(); //프론트가 어떤 리스트를 사용할지를 정할 수 있게 해야한다.
//		} else if("/member/memberView.do".equals(requestURI)) {
//			controller = new MemberViewController(); //프론트가 어떤 리스트를 사용할지를 정할 수 있게 해야한다.
//		} else if("/index.do".equals(requestURI)) {
//			controller = new IndexController();
//		} else if("/login/loginProcess.do".equals(requestURI)) {
//			controller = new LoginProcessController();
//		} else if("/login/logout.do".equals(requestURI)) {
//			controller = new LogoutController();
//		} else if("/prod/prodInsert.do".equals(requestURI)) {
//			controller = new ProdInsertContoller(); //이 한줄에 의해서 의존관계와 결합력이 생긴다.
//		} 
		//문제점 : 여기서만이 서블릿의 역할을 확인할 수 있다.
		//이 안의 이프문들을 핸들러 맵이 가져간거나 마찬가지라서 if문이 필요없어진다.
//		--
		RequestMappingInfo mappingInfo = handlerMapping.findCommandHandler(req); //handlerMapping 의 유일한 메서드에 가지고 있는 req값을 넘긴다.
		//RequestMappingInfo애는 핸들러메소드가 모든 정보가 가지고 있다.
	
		
		
//		if(controller==null) {
		if(mappingInfo==null) { //실행할 핸들러 메서드는 메핑인포에 모두 들어있다.
			//위 조건에 포함되지 않는 것, 일식과 한식은 팔지만 중식은 안 팔아!
			//클라이언트 상태 오류 404
			resp.sendError(404, requestURI+"는 처리할 수 없는 자원입니다(Not found).");
			return;
		}
		
//		String viewName = controller.process(req, resp); //요청은 프론트가 받았지만 처리하는 건 프로드리스트컨트롤러
		String viewName = handlerAdapter.invokeHandler(mappingInfo, req, resp); //여기에 주소가 들어감
		//디스패쳐에 있던 권한들이 다 책임관계가 나눠지게 되었다.
		
		// 컨트롤러마다 필요한 뷰네임이달라서 여기서 결정할 수 있다
		if(viewName == null) {
			if(!resp.isCommitted()) resp.sendError(500, "논리적인 뷰 네임은 null일 수 없음");
		} else {
			viewResolver.resolveView(viewName, req, resp);	
		}		
	}
}
