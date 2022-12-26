package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

public class MemberDAOImpl extends AbstractJDBCDAO implements MemberDAO {
		
//의존관계를 형성해줄 코드가 필요함	
//	private MemberVO member = new MemberVO();

	@Override
	public int insertMember(MemberVO member) {
		return 0;
	}
	

	@Override
	public List<MemberVO> selectMemberList() {
		StringBuffer sql = new StringBuffer();
		// === 달라지는 부분1
		sql.append(" SELECT MEM_ID, MEM_NAME, MEM_MAIL, MEM_HP, MEM_ADD1, MEM_MILEAGE ");
		sql.append(" FROM MEMBER ");

		return selectList(sql.toString(), MemberVO.class); // 파람은 파라미터 없으니까 매개변수에서 지워도 된다
		
//		try(
//			Connection conn = ConnectionFactory.getConnection();
//			PreparedStatement pstmt = conn.prepareStatement(sql.toString());				
//		){
//			// === 달라지는 부분2
//			ResultSet rs = pstmt.executeQuery();
//			//지금 필요한 것은 리스트다. 
//			List<MemberVO> memberList = new ArrayList<>();
//			
//			while(rs.next()) {
//				// === 달라지는 부분3
//				MemberVO member = new MemberVO(); //한건 한건의 멤버 브이오가 생성이 되어야한다.
//				memberList.add(member);
//				member.setMemId(rs.getString("MEM_ID")); //스네이크 표기방식이 카멜표기 방식으로 바뀐다는 규칙성을 알 수 있음
//				member.setMemName(rs.getString("MEM_NAME"));
//				member.setMemMail(rs.getString("MEM_MAIL"));
//				member.setMemHp(rs.getString("MEM_HP"));
//				member.setMemAdd1(rs.getString("MEM_ADD1"));
//				member.setMemMileage(rs.getInt("MEM_MILEAGE"));
//			}
//			return memberList;
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		}
	}

	@Override
	public MemberVO selectMember(String memId) {
		StringBuffer sql = new StringBuffer();

		// === 달라지는 부분 1
		sql.append(" SELECT ");
	    sql.append(" mem_id,     mem_pass,   mem_name, ");
	    sql.append(" mem_regno1,    mem_regno2, ");
	    sql.append(" to_char(mem_bir, 'YYYY-MM-DD') mem_bir, ");
	    sql.append(" mem_zip,    mem_add1, mem_add2,   ");
	    sql.append(" mem_hometel, mem_comtel, mem_hp, ");
	    sql.append(" mem_mail,    mem_job,    mem_like, ");
	    sql.append(" mem_memorial, to_CHAR(mem_memorialday, 'YYYY-MM-DD') mem_memorialday, ");   
	    sql.append(" mem_mileage, ");
	    sql.append(" mem_delete ");
		sql.append(" FROM    member ");
		sql.append(" WHERE MEM_ID = ?");
		
		return selectOne(sql.toString(), MemberVO.class, memId);
		
		//쿼리 파라미터가 있다 물음표
//		try(
//			Connection conn = ConnectionFactory.getConnection();
//			PreparedStatement pstmt = conn.prepareStatement(sql.toString());				
//		){
//			// === 달라지는 부분2
//			//쿼리파라미터부분 달라짐
//			ResultSet rs = pstmt.executeQuery();
//			//지금 필요한 것은 
//			
//			return null;
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		}
	}

	@Override
	public int updatdMember(MemberVO member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMember(String memId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
