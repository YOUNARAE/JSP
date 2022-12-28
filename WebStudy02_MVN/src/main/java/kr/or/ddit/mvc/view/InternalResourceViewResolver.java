package kr.or.ddit.mvc.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InternalResourceViewResolver implements ViewResolver {
	private String prefix;
	private String suffix;
	
	//슈퍼클래스도 생성해놓는다, 두번쨰 생성자가 필요없는 사람도 있으니까
	public InternalResourceViewResolver() {
		this("", "");
	}

	public InternalResourceViewResolver(String prefix, String suffix) {
		super();
		this.prefix = prefix;
		this.suffix = suffix;
		//생성자를 통해 두개값을 같이 생성
	}



	@Override
	public void resolveView(String viewName, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//5. 뷰 이동
		if(viewName.startsWith("redirect:")) {
			viewName = viewName.substring("redirect:".length());
			resp.sendRedirect(req.getContextPath() + viewName);
		}else {
			req.getRequestDispatcher(prefix + viewName + suffix).forward(req, resp);
		}	
	}

}
