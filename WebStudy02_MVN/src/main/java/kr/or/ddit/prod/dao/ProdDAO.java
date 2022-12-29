package kr.or.ddit.prod.dao;

import org.apache.ibatis.annotations.Param;

import kr.or.ddit.vo.ProdVO;

public interface ProdDAO {
	/**
	 * 
	 * @param prodId
	 * @return 존재하지 않으면, null 반환 -- 이건 우리가 정하는 게 아니고, 마이바티스가 그렇게 정한거다
	 */
	public ProdVO selectProd(@Param("prodId") String prodId);
	//상품아이디 한개로 조회하는 것, 레코드 한개가 필요하니까 ProdVO가 된다
	//파람을 만들면 우리 눈에 안 보이는 맵이 만들어지는 것이다.
}
