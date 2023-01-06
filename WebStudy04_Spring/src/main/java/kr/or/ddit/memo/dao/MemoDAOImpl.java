package kr.or.ddit.memo.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.mybatis.MybatisUtils;
import kr.or.ddit.vo.MemoVO;

public class MemoDAOImpl implements MemoDAO {
	//원래는 의존성이 없었는데, 의존성이 생겼다 마이바티스에게
	private SqlSessionFactory sqlSessionFactory = MybatisUtils.getSqlSessionFactory(); 
	//SqlSessionFactory 싱글톤이라 이렇게 받아왔는데, 
	//sqlSession은 지역적으로 사용하는게 달라서 싱글톤으로 만들면 안된다
	
	@Override
	public List<MemoVO> selectMemoList() {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			MemoDAO mapperProxy = sqlSession.getMapper(MemoDAO.class);
			return mapperProxy.selectMemoList(); 
//			return sqlSession.selectList("kr.or.ddit.memo.dao.MemoDAO.selectMemoList"); //어떤 쿼리에 접근하려면 키로 접근해야한다.
			//내부적으로는 sql 맵퍼가 생긴다
			//셀렉트는 조회쿼리라서 트랜젝션을 관리할 필요가 없다
		}
	}

	@Override
	public int insertMemo(MemoVO memo) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession(); //트렌젝션 시작
		){
			MemoDAO mapperProxy = sqlSession.getMapper(MemoDAO.class);  //우리가 사용하는 인터페이스 하나가 가짜 형태인 프록시로 생성이 된다.
			int rowcnt = mapperProxy.insertMemo(memo);//시그니처에 따라 정해져있는 파라미터를 반드시 넘겨줘야한다.
//			int rowcnt = sqlSession.insert("kr.or.ddit.memo.dao.MemoDAO.insertMemo", memo); //어떤 쿼리에 접근하려면 키로 접근해야한다. 
//			이 코드로 하면 메모를 안 넘겨도 오류가 안난다, 그래서 맵퍼 프록시를 만들었다. 
//			밑에 방식은 매번 쿼리문 아이디를 넣어주는데 오타 발생의 여지를 준다.
//			시그니처에 제약이 없어서 memo를 안 넘겨도 오류를 알 수 없는데 프록시 타입을 넣으면 오류를 알 수 있다
			//내부적으로는 sql 맵퍼가 생긴다
			sqlSession.commit(); //필요에 따라서 커밋한다. 인서트,업데이트,딜리트는 트렌젝션을 관리해야해서 커밋을 넣어준다. ->커밋을 하는 순간 트랜잭션 종료
			return rowcnt; // 반환받는다
		}
	}

	@Override
	public int updateMemo(MemoVO memo) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();  //트렌젝션 시작
		){
//			int rowcnt = sqlSession.update("kr.or.ddit.memo.dao.MemoDAO.updateMemo", memo); //어떤 쿼리에 접근하려면 키로 접근해야한다.
			MemoDAO mapperProxy = sqlSession.getMapper(MemoDAO.class);
			int rowcnt = mapperProxy.updateMemo(memo); 
			//내부적으로는 sql 맵퍼가 생긴다
			sqlSession.commit(); //트랜잭션 종료
			return rowcnt;
		}
	}

	@Override
	public int deleteMemo(int code) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession(); //트렌젝션 시작
		){
			MemoDAO mapperProxy = sqlSession.getMapper(MemoDAO.class);
			int rowcnt = mapperProxy.deleteMemo(code); 
//			int rowcnt = sqlSession.delete("kr.or.ddit.memo.dao.MemoDAO.deleteMemo", code); //어떤 쿼리에 접근하려면 키로 접근해야한다.
			//내부적으로는 sql 맵퍼가 생긴다
			sqlSession.commit(); //트랜잭션 종료
			return rowcnt;
		}
	}

}
