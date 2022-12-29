package kr.or.ddit.servlet09.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.DataBasePropertyVO;

public class DataBasePropertyDAOImpl implements DataBasePropertyDAO {

	@Override
	public List<DataBasePropertyVO> selectPropertyList(String propertyName) {
		// --
		// 3. driver class loading : oracle.jdbc.driver.OracleDriver.class
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT PROPERTY_NAME, PROPERTY_VALUE, DESCRIPTION ");
		sql.append(" FROM DATABASE_PROPERTIES ");
		if(StringUtils.isNotBlank(propertyName)) {
			sql.append(" WHERE PROPERTY_NAME = ? "); //연산자가 들어가있으면 안되서
		}
		
		try(
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString()); // Prepared는 statment와 다르게 이미 컴파일된 객체로 만들어지기 때문에 보안에 취약하지 않을 수 있다
//			Statement stmt =  conn.createStatement();
		){
			
//			
//			ResultSet rs = stmt.executeQuery(sql.toString());
			if(StringUtils.isNotBlank(propertyName)) {
				pstmt.setString(1, propertyName); //parameterIndex 물음표의 갯수
			}
			ResultSet rs = pstmt.executeQuery();
			List<DataBasePropertyVO> list= new ArrayList<>();
			
			while(rs.next()){
				DataBasePropertyVO vo = new DataBasePropertyVO();
				list.add(vo);
				vo.setPropertyName(rs.getString("PROPERTY_NAME"));
				vo.setPropertyValue(rs.getString("PROPERTY_VALUE"));
				vo.setDescription(rs.getString("DESCRIPTION"));
			}
			return list;
			
		}catch(SQLException e){
			//8. 500
			throw new RuntimeException(e);
		}
		// ---여기까지가 모델을 확보하기 위한 과정 
	}

}
