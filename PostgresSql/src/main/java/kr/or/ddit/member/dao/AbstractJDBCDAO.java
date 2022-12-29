package kr.or.ddit.member.dao;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.text.CaseUtils;

import kr.or.ddit.db.ConnectionFactory;

public abstract class AbstractJDBCDAO {
	
	public <T> T selectOne(String sql, Class<T> resultType, Object...params) {
		try(
			Connection conn = makeConnection();
			PreparedStatement pstmt = makePreparedStatement(conn, sql);
		){
			//쿼리 파라미터를 셋팅하는 작업
			queryParameterSetting(pstmt, params); //근데 여기 단계에서 하는 작업은 다 다르기때문에 코드화시킬 수 없다
			ResultSet rs = exeuteQuery(pstmt);
			T resultObject = null;//3
			if(rs.next()) {
				resultObject = resultBindingForOnRecord(rs, resultType); //어떤 레코드를 가져올지는 리절트타입에 있음 , 이 부분도 미리 코도화시킬 수 없다
			}
			return resultObject;
			}catch(SQLException e) {
				throw new RuntimeException(e);
		}
	}

	
	//순서를 결정할 애가 필요하다
	public <T> List<T> selectList(String sql, Class<T> resultType, Object...params) { //쿼리문이 있어야해서 파라미터로 쿼리문을 받아온다, + 가변 파라미터를 받는다, 쿼리문은 바뀔 수 있어서
		// 타입을 고정시킬 수는 없고 파라미터에 리턴타입의 타입을 넣어준다(Class<T> resultType)
		// 리턴타입을 이렇게 사용하려면 T에 대한 정의가 필요하다. 그래서 List<T> list 를 생성 -3
		try(
			Connection conn = makeConnection();
			PreparedStatement pstmt = makePreparedStatement(conn, sql);
		){
			//쿼리 파라미터를 셋팅하는 작업
			queryParameterSetting(pstmt, params); //근데 여기 단계에서 하는 작업은 다 다르기때문에 코드화시킬 수 없다
			ResultSet rs = exeuteQuery(pstmt);
			List<T> list = new ArrayList<>();//3
			while(rs.next()) {
				T recordObject = resultBindingForOnRecord(rs, resultType); //어떤 레코드를 가져올지는 리절트타입에 있음 , 이 부분도 미리 코도화시킬 수 없다
				list.add(recordObject);
			}
			return list;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}


	//커넥션 객체 생성
	private Connection makeConnection() throws SQLException{
		return ConnectionFactory.getConnection();
	}
	
	//쿼리 객체 생성
	private PreparedStatement makePreparedStatement(Connection conn, String sql) throws SQLException { //커넥션과 쿼리문
		return conn.prepareStatement(sql);
	}

	//쿼리문 실행
	private ResultSet exeuteQuery(PreparedStatement pstmt) throws SQLException {
		return pstmt.executeQuery();
	}
	
	
	//d여기서 하는 작업은 다 달라야해서 추상메서드로 만든다.
	public void queryParameterSetting(PreparedStatement pstmt, Object...params) throws SQLException {
		try {
			//쿼리파라미터가 필요한 경우에 필요한 메서드
			if(params.length>0) {
				for(int idx=0; idx<params.length; idx++) {
					Object param = params[idx];
					if(param.getClass().equals(int.class)) {
						pstmt.setInt(idx+1, (Integer) param);
					}else {
						pstmt.setString(idx+1, param.toString()); 					
					}
				}
			}
		}catch (Exception e) {
			throw new SQLException(e);
		}
	}

	
	public <T> T resultBindingForOnRecord(ResultSet rs, Class<T> resultType) throws SQLException{
		try {
			T resulObject = resultType.newInstance();
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();//조회된 커리문에 조회된 갯수가 몇개냐
			for(int idx=1; idx<=count; idx++) {
			//		MEM_ID -> memId이라는 카멜케이스가 필요하다 -> setMemId(rs.getString("MEM_ID")) --이거 하나 받은 거나 마찬가지임
			//		MEM_Mileage -> memMileage이라는 카멜케이스가 필요하다 -> setMemMileage(rs.getInt("MEM_MILEAGE")) -- 전역변수에 다른 타입이 필요해진다 int
				String columnName = rsmd.getColumnName(idx);		
				String propertyName = CaseUtils.toCamelCase(columnName, false, '_');
				PropertyDescriptor pd = new PropertyDescriptor(propertyName, resultType); //카멜케이스 이름과 만들어진 객체의 타입은 Class<T> resultType
				Method setter = pd.getWriteMethod();
				Class<?> propertyType = pd.getPropertyType();
				
				if(propertyType.equals(Integer.class)) {
					//Integer
					setter.invoke(resulObject, rs.getInt(columnName));
					
				} else {
					//String
					setter.invoke(resulObject, rs.getString(columnName));					
				}
			}
			return resulObject;
		} catch(Exception e) {
			throw new SQLException(e);
		}
	}; //바디가 있어선 안된다 코드화 시킬 수 없어서 , 계속 변해서


}
