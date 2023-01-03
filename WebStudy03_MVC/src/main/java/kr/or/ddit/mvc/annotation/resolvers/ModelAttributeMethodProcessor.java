package kr.or.ddit.mvc.annotation.resolvers;

import java.io.IOException;
import java.lang.reflect.Parameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.CaseUtils;

/**
 * @ModelAttribute 어노테이션을 가진 command object(not 기본형) 인자 하나를 해결.
 * ex) @ModelAttribute MemberVO member (O)
 *     @ModelAttribute int cp (x); 
 * 
 */
public class ModelAttributeMethodProcessor implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(Parameter parameter) {
		//제일 먼저 어노테이션을 가지고 있는지 확인하고 기본형이 아닌지 확인해야한다
		Class<?> parameterType = parameter.getType();
		ModelAttribute modelAttribute = parameter.getAnnotation(ModelAttribute.class);
		boolean support = modelAttribute!=null
				&&
				!( //여기에 !를 넣어서 반대 케이스로 만든다.
					parameterType.isPrimitive() 
					||
					String.class.equals(parameterType) //기본형이 아니가서 스트링 파라미터 타입이면 이 조건이라는 의미
					||
					(	
						parameterType.isArray() 
						&&  
						(
							parameterType.getComponentType().isPrimitive() 
							|| 
							parameterType.getComponentType().equals(String.class)
						  )
					)//배열타입인지 확인, 인트의 배열, 불린의 배열, 스트링의 배열
				);
		return support;
	}

	@Override
	public Object resolveArgument(Parameter parameter, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Class<?> parameterType = parameter.getType();
		ModelAttribute modelAttribute = parameter.getAnnotation(ModelAttribute.class);
		
		try{
//			MemberVO member = new MemberVO();
			Object commandObject = parameterType.newInstance();
			
//			req.setAttribute("member", member);
			String attrName = modelAttribute.value();
//			CoC (Convention over Configuration)
			if(StringUtils.isBlank(attrName)) {
				attrName = CaseUtils.toCamelCase(parameterType.getSimpleName(), false, ' '); //ture-첫문자를 이니셜라이징한다는 건 대문자가 된다는 뜻.false-소문자
				//simpleName은 MemberVO<-이 이름
			}
			req.setAttribute(attrName, commandObject);

//			Map<String, String[]> parameterMap = req.getParameterMap();

//			try {
//				BeanUtils.populate(member, parameterMap);
//			} catch (IllegalAccessException | InvocationTargetException e) {
			BeanUtils.populate(commandObject, req.getParameterMap());
			
			return commandObject;
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}

}
