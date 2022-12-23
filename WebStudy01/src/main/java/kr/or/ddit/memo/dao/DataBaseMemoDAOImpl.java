package kr.or.ddit.memo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.MemoVO;

public class DataBaseMemoDAOImpl implements MemoDAO {
	
	public DataBaseMemoDAOImpl() {
		super();
	}

	private static DataBaseMemoDAOImpl instance; 
	//싱글톤을 사용하는 이유 : 메모리를 절약하기 위해서
	//싱글톤이 가능한 건 생성되는 인스턴스가 모두 동일한 경우에 ex)전역변수가 없는 경우.상태가 기록하지 않는 객체의 경우
	//전역변수로 많이 사용하는 것을 싱글톤으로 묶으면 데이터가 어그러질 수 있다.
	public static DataBaseMemoDAOImpl getInstance() {
		if(instance==null) {
			instance = new DataBaseMemoDAOImpl();
		}
		return instance;
	}
	
	@Override
	public List<MemoVO> selectMemoList() {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT CODE, WRITER, CONTENT, \"DATE\" ");
		sql.append(" FROM TBL_MEMO ");
		
		try (
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			
			ResultSet rs = pstmt.executeQuery();
			List<MemoVO> memoList = new ArrayList<>();
			
			while(rs.next()) {
				MemoVO memo = new MemoVO();
				memoList.add(memo);
				memo.setCode(rs.getInt("CODE"));
				memo.setWriter(rs.getString("writer"));
				memo.setContent(rs.getString("CONTENT"));
				memo.setDate(rs.getString("DATE"));
			}
			return memoList;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int insertMemo(MemoVO memo) {
		StringBuffer sql = new StringBuffer();
		sql.append(" INSERT INTO TBL_MEMO (");
		sql.append(" CODE, WRITER, CONTENT ");
		sql.append(")VALUES (MEMO_SEQ.NEXTVAL, ?, ?)");
		
		try (
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			int i=1;
			pstmt.setString(i++, memo.getWriter());
			pstmt.setString(i++, memo.getContent());
			return pstmt.executeUpdate();
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int updateMemo(MemoVO memo) {
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE tbl_memo   ");
		sql.append(" SET               ");
		sql.append(" WRITER = ?,       ");
		sql.append(" CONTENT = ?       ");
		sql.append(" WHERE CODE = ?    ");
		
		try (
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			int i = 1;
			pstmt.setString(i++, memo.getWriter());
			pstmt.setString(i++, memo.getContent());
			pstmt.setInt(i++, memo.getCode());
			
			return pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int deleteMemo(int code) {
		StringBuffer sql = new StringBuffer();
		sql.append(" DELETE FROM tbl_memo   ");
		sql.append(" WHERE CODE = ?    ");
		
		try (
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			int i = 1;
			pstmt.setInt(i++, code);
			
			return pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


}
