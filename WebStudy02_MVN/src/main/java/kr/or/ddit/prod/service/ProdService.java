package kr.or.ddit.prod.service;

import kr.or.ddit.vo.ProdVO;
public interface ProdService {
	/**
	 * 
	 * @param prodId
	 * @return 조재하지 않을 경우 런타임익셉션을 발생
	 */
	public ProdVO retrieveProd(String prodId);

}
