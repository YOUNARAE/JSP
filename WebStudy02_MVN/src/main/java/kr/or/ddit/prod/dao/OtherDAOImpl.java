package kr.or.ddit.prod.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.mybatis.MybatisUtils;
import kr.or.ddit.vo.BuyerVO;

public class OtherDAOImpl implements OthersDAO {
	private SqlSessionFactory SqlSessionFactory = MybatisUtils.getSqlSessionFactory();
	
	@Override
	public List<Map<String, Object>> selectLprodList() {
		try(
			SqlSession sqlSession = SqlSessionFactory.openSession();
		){
			OthersDAO mapperProxy = sqlSession.getMapper(OthersDAO.class);
			return mapperProxy.selectLprodList();
		}
	}

	@Override
	public List<BuyerVO> selecttBuyerList(String buyerLgu) {
		try(
			SqlSession sqlSession = SqlSessionFactory.openSession();
		){
			OthersDAO mapperProxy = sqlSession.getMapper(OthersDAO.class);
			return mapperProxy.selecttBuyerList(buyerLgu);
		}
	}

}
