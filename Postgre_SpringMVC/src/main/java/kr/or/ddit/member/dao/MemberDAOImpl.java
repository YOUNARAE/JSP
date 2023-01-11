package kr.or.ddit.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.mybatis.MybatisUtils;

@Repository
public class MemberDAOImpl implements MemberDAO {
		
//의존관계를 형성해줄 코드가 필요함	
//	private MemberVO member = new MemberVO();
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
