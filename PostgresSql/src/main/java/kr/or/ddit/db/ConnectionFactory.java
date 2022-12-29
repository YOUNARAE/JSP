package kr.or.ddit.db;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

/*
 * Factory Object[Method] Pattern
 *  : 필요 객체의 생성을 전담하는 객체를 별도 운영하는 구조.
 *  
 * 
 */
public class ConnectionFactory {
	private static String url;
	private static String user;
	private static String password;
	
	private static DataSource ds;
	
	static { // 여기에 담으면 클래스가 실행될 때 1번만 실행된다.
		
//		String path = "/kr/or/ddit/db/dbInfo.properties"; //클래스패스리소스의 주소, 논리경로에서 물리경로로 접근해야한다
		String path = "/kr/or/ddit/db/dbInfo.properties";
		
		try(
			InputStream is = ConnectionFactory.class.getResourceAsStream(path);		
		) {
			Properties dbInfo = new Properties();
			dbInfo.load(is);
			
			url = dbInfo.getProperty("url");
			user = dbInfo.getProperty("user");
			password = dbInfo.getProperty("password");
			
//			Class.forName(dbInfo.getProperty("driverClassName"));
			
			BasicDataSource bds = new BasicDataSource();
			bds.setDriverClassName(dbInfo.getProperty("driverClassName"));
			bds.setUrl(url);
			bds.setUsername(user);
			bds.setPassword(password);
			
			bds.setInitialSize(Integer.parseInt(dbInfo.getProperty("initicalSize")));
			
			bds.setMaxTotal(Integer.parseInt(dbInfo.getProperty("maxTotal"))); //풀링 : 미리 만들어놓는다, 객체를 재활용도 가능하다. 반납이 안되면 11번째 손님이 왔을 때 그때는 불가능);
			bds.setMaxWaitMillis(Integer.parseInt(dbInfo.getProperty("maxWait")));
			bds.setMaxIdle(Integer.parseInt(dbInfo.getProperty("maxIdle"))); //최대로 올릴 수 있는, //롱으로 바꿔야함
			//맨 처음에 5개 만들어놓고 손님이 오기전까진 5개로 놀다가 최대치인 10개까지는 해놓는데, MAXIdle과 Initial은 같아야함.
			
			ds = bds;
			
		} catch (Exception e1) {
			throw new RuntimeException(e1);
		}
	}
	
	public static Connection getConnection() throws SQLException {
//		Connection conn =  DriverManager.getConnection(url, user, password);
		return ds.getConnection();
	}
}
