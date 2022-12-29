package kr.or.ddit.prod.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.mybatis.MybatisUtils;
import kr.or.ddit.vo.ProdVO;

public class ProdDAOImpl implements ProdDAO {
//	MyBatis와의 의존관계 형성
	private SqlSessionFactory SqlSessionFactory = MybatisUtils.getSqlSessionFactory();
	
	@Override
	public ProdVO selectProd(String prodId) {
		try(
			SqlSession sqlSession = SqlSessionFactory.openSession();
		){
			ProdDAO mapperProxy = sqlSession.getMapper(ProdDAO.class);
			return mapperProxy.selectProd(prodId);
		}
	}

}
