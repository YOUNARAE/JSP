package kr.or.ddit.servlet07;

import java.io.File;
import java.util.Optional;

import javax.servlet.ServletContext;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class FileWrapper {
	@JsonIgnore //마샬링할 때 밑에 adaptee는 무시해라는 의미
 	private File adaptee;

	public FileWrapper(File adaptee, ServletContext application) { //파일이 없이는 만들수 없는 생성자 구조가 되었다
		super();
		this.adaptee = adaptee;
		this.name = adaptee.getName(); 
		this.contentType = Optional.ofNullable(application.getMimeType(name)) //contentType을 써야하기 때문에 어플리션을 매개변수로 추가하였다
		//여기 안에서 옵셔널 객체가 만들어졌다.
				 .orElse("application/octet-stream");
		//톰캣이 인지하고 있는 확장자라면 정상적으로 마임으로 contentType에 들어왔지만 그렇지 않다면 application/octet-stream로 간다
	}
	
	private String name; //모든 것은 adaptee가 가지고 있음
	private String contentType;


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

}
