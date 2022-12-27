package kr.or.ddit.vo;

import java.io.Serializable;

public class MemoVO implements Serializable{
	//직렬화하려는 객체를 직렬화할 떈  프로퍼티도 직렬화가 가능해야한다
//	private transient Object prop; // 보호를 해야하는 민감한 데이터가 있을 때에는 transient를 붙여준다
//	public Object getProp() {
//		return prop;
//	}
//	public void setProp(Object prop) {
//		this.prop = prop;
//	}
	private Integer code;
	private String writer;
	private String content;
	private String date;
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
	@Override
	public String toString() {
		return "MemoVO [code=" + code + ", writer=" + writer + ", content=" + content + ", date=" + date + "]";
	}
	
}
