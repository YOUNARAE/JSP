package kr.or.ddit.commons.wrapper;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class CookieHttpServletRequestWrapper extends HttpServletRequestWrapper {
//돼지코 만들어야함. 어댑터는 어댑티 없이는 생성될 수 없다
//생성자를 만들어줘야한다(밑에)
	private Map<String, Cookie> cookieMap;
	
	public CookieHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
		cookieMap = new HashMap<>();
		Cookie[] cookies =  request.getCookies();
		if(cookies!=null){
			for(Cookie tmp : cookies) {
				cookieMap.put(tmp.getName(), tmp);
			}
		}
	}
	
	public Cookie getCookie(String name) {
		return cookieMap.get(name);
	}
	
	public String getCookieValue(String name) {
		Cookie finded = getCookie(name);
		return Optional.ofNullable(finded)
						.map(cookie-> {
							try {
								return URLDecoder.decode(cookie.getValue(), "UTF-8");					
							} catch (UnsupportedEncodingException e) {
								throw new RuntimeException(e);
							}
						})
						.orElse(null);
		//있으면 쿠키가 반환되고 없으면 널이 반환된다.
	}
	
}
