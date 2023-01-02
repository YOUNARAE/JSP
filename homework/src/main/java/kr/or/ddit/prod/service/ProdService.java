package kr.or.ddit.prod.service;

import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;
public interface ProdService {
	/**
	 * 
	 * @param prodId
	 * @return 조재하지 않을 경우 런타임익셉션을 발생
	 */
	public ProdVO retrieveProd(String prodId);
	
	
	/**
	 * 
	 * call by reference 구조에 따라  totalRecord와 dataList를 pagingVO에 넣어줌
	 * @param pagingVO
	 * 
	 */
	public void retrieveProdList(PagingVO<ProdVO> pagingVO); //리턴타입이 void라는 것은 반환값을 가져가지 않겠다는 의미

}
