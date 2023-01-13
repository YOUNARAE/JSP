package kr.or.ddit.prod.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

@Mapper
public interface ProdDAO {
	/**
	 * 
	 * @param prodId
	 * @return 존재하지 않으면, null 반환 -- 이건 우리가 정하는 게 아니고, 마이바티스가 그렇게 정한거다
	 */
	public ProdVO selectProd(@Param("prodId") String prodId);
	//상품아이디 한개로 조회하는 것, 레코드 한개가 필요하니까 ProdVO가 된다
	//파람을 만들면 우리 눈에 안 보이는 맵이 만들어지는 것이다.
	
	/**
	 * @param pagingVO
	 * @return 검색조건에 맞는 레코드 수 반환
	 */
	public int selectTotalRecord(PagingVO<ProdVO> pagingVO);
	

	/**
	 * @param pagingVO
	 * @return
	 */
	public List<ProdVO> selectProdList(PagingVO<ProdVO> pagingVO); //모든 목록 리스트가 페이징 처리가 되는 쿼리문 안에 있기때문에 타입은  pagingVO 가 된다
	
	/**
	 * 상품 신규등록
	 * @param prod
	 * @return 등록된 상품 수
	 */
	public int insertProd(ProdVO prod, SqlSession sqlSession);
	
	/**
	 * 상품 수정
	 * @param prod
	 * @return 수정된 상품 수
	 */
	public int updateProd(ProdVO prod);
	
	/**
	 * 상품 삭제
	 * @param prodId
	 * @return
	 */
//	public int deleteProd(String prodId);
}
