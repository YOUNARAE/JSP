package kr.or.ddit.di;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor //셋터인젝션만 받을 수 있음
@Data
public class DBInfoVO {
	private String driverClassName;
	private String url;
	private String user;
	private String password;
	
	private int initialSize;
	private int maxTotal;
	private int maxIdle;
	private long maxWait;
}
