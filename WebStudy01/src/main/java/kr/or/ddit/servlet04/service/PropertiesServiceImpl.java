package kr.or.ddit.servlet04.service;

import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import kr.or.ddit.servlet01.DescriptionServlet;

public class PropertiesServiceImpl implements PropertiesService {

	@Override
	public Properties retrieveData() {
		Properties properties = new Properties();
		try(
			InputStream is  = DescriptionServlet.class.getResourceAsStream("/kr/or/ddit/props/DataStore.properties");
		){
			properties.load(is);
		}catch(Exception e) {
			throw new RuntimeException(e);//주의할 점,e를 안 넣으면 완전히 다른 예외가 되어버린다
		}
		return properties;
	}
	
	public void resourceBundle() {
		String baseName = "kr.or.ddit.props.Message";
		ResourceBundle bundle = ResourceBundle.getBundle(baseName, Locale.ENGLISH);
	}

}
