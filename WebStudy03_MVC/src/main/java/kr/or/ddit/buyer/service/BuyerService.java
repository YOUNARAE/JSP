package kr.or.ddit.buyer.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.or.ddit.vo.BuyerVO;import kr.or.ddit.vo.PagingVO;

public interface BuyerService {

	/**
	 * @param buyerId
	 * @return 거래처 한명을 조회하는 메서드
	 */
	public BuyerVO retrieveBuyer(String buyerId);
	
	
	/**
	 * @param buyer
	 * @return 거래처 전체를 조회하는 메서드
	 */
	public List<BuyerVO> retrieveBuyer(PagingVO<BuyerVO> buyer);
	
}
