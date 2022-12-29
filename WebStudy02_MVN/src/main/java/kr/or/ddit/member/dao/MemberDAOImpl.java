package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mybatis.MybatisUtils;
import kr.or.ddit.vo.MemberVO;

public class MemberDAOImpl implements MemberDAO {
		
//의존관계를 형성해줄 코드가 필요함	
//	private MemberVO member = new MemberVO();
	private SqlSessionFactory SqlSessionFactory = MybatisUtils.getSqlSessionFactory();
	
	
	@Override
	public int insertMember(MemberVO member) {
		try(
			SqlSession sqlSession = SqlSessionFactory.openSession();
		){
			MemberDAO mapperProxy = sqlSession.getMapper(MemberDAO.class);
			int rowcnt = mapperProxy.insertMember(member);
			sqlSession.commit();
			return rowcnt;
		}
	}
	

	@Override
	public List<MemberVO> selectMemberList() {
		try(
			SqlSession sqlSession = SqlSessionFactory.openSession();
		){
			MemberDAO mapperProxy = sqlSession.getMapper(MemberDAO.class);
			return mapperProxy.selectMemberList();
		}
	}

	@Override
	public MemberVO selectMember(String memId) {
		try(
			SqlSession sqlSession = SqlSessionFactory.openSession();
		){
			MemberDAO mapperProxy = sqlSession.getMapper(MemberDAO.class);
			return mapperProxy.selectMember(memId);
		}
	}

	@Override
	public int updateMember(MemberVO member) {
		try(
			SqlSession sqlSession = SqlSessionFactory.openSession(); //트렌젝션 시작
		){
			MemberDAO mapperProxy = sqlSession.getMapper(MemberDAO.class);
			int rowcnt = mapperProxy.updateMember(member);
			sqlSession.commit(); //트렌젝션 종료
			return rowcnt;
		}
	}

	@Override
	public int deleteMember(String memId) {
		try(
			SqlSession sqlSession = SqlSessionFactory.openSession(); //트렌젝션 시작
		){
			MemberDAO mapperProxy = sqlSession.getMapper(MemberDAO.class); //여기서 받아오는 프록시를 전역화시킬 것이다
			int rowcnt = mapperProxy.deleteMember(memId);
			sqlSession.commit(); //트렌젝션 종료
			return rowcnt;
		}
	}

}
