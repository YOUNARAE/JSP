package kr.or.ddit.buyer.dao;

import java.util.List;

import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

public class BuyerDAOImpl implements BuyerDAO {

	@Override
	public BuyerVO selectBuyer(String buyerId) {
		return null;
	}

	@Override
	public int selectTotalRecord(PagingVO<ProdVO> pagingVO) {
		return 0;
	}

	@Override
	public List<BuyerVO> selectBuyerList(PagingVO<BuyerVO> pagingVO) {
		return null;
	}

	@Override
	public int insertBuyer(BuyerVO buyer) {
		return 0;
	}

	@Override
	public int updateBuyer(BuyerVO buyer) {
		return 0;
	}

}
