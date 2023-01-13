package kr.or.ddit.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.mybatis.MybatisUtils;
import kr.or.ddit.vo.MemberVO;

public class MemberDAOImpl implements MemberDAO {

	private SqlSessionFactory SqlSessionFactory = MybatisUtils.getSqlSessionFactory();
	
	@Override
	public List<MemberVO> selectMemberList() {
		try(
			SqlSession sqlSession = SqlSessionFactory.openSession();
		){
			MemberDAO mapperProxy = sqlSession.getMapper(MemberDAO.class);
			return mapperProxy.selectMemberList();
		}
	}

}
