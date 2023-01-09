package kr.or.ddit.di;

import org.springframework.beans.factory.FactoryBean;

public class StringArrayFactoryBean implements FactoryBean<String[]>{

	@Override
	public String[] getObject() throws Exception {
		return new String[] {"element1", "element2"};
	}

	@Override
	public Class<?> getObjectType() {
		return String[].class; //이런 클래스라는 건 존재하지 않는다. 그래서 별도의 빈으로 등록할 수 없었다
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
	
}
