package kr.or.ddit.buyer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

public interface BuyerDAO {
	
	/**
	 * @param buyerId
	 * @return 존재하지 않으면, null반환 --
	 */
	public BuyerVO selectBuyer(@Param("buyerId") String buyerId);
	
	/**
	 * @param pagingVO
	 * @return 검색조건에 맞는 레코드 수 반환
	 */
	public int selectTotalRecord(PagingVO<ProdVO> pagingVO);
	

	/**
	 * @param pagingVO
	 * @return
	 */
	public List<BuyerVO> selectBuyerList(PagingVO<BuyerVO> pagingVO);
	
	/**
	 * @param pagingVO
	 * @return 등록된 거래처 수
	 */
	public int insertBuyer(BuyerVO buyer);
	

	/**
	 * @param buyer
	 * @return 수정된 거래처 수
	 */
	public int updateBuyer(BuyerVO buyer);

}
