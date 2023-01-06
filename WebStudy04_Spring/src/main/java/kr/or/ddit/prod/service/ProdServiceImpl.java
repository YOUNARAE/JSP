package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.UserNotFoundException;
import kr.or.ddit.prod.dao.ProdDAO;
import kr.or.ddit.prod.dao.ProdDAOImpl;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;
import oracle.net.ano.AuthenticationService;

public class ProdServiceImpl implements ProdService {
	
	private ProdDAO prodDAO = new ProdDAOImpl();

	@Override
	public ProdVO retrieveProd(String prodId) {
		ProdVO prod = prodDAO.selectProd(prodId);
		if(prod==null) {
			throw new RuntimeException(String.format(prodId+"에 해당하는 상품이 없음"));
		}
		return prod;
	}

	@Override
	public void retrieveProdList(PagingVO<ProdVO> pagingVO) { //call by reference를 인지하려고 void로 했다. 필요하다면 pagingVO로 반환시켜도 상관없다
		int totalRecord = prodDAO.selectTotalRecord(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<ProdVO> dataList = prodDAO.selectProdList(pagingVO);
		pagingVO.setDetaList(dataList);
// 내가 풀은 것
//		pagingVO.setTotalRecord(prodDAO.selectTotalRecord(pagingVO));
//		List<ProdVO> prodList = prodDAO.selectProdList(pagingVO);
//		
//		pagingVO.setDetaList(prodList);
	}

	@Override
	public ServiceResult createProd(ProdVO prod) { //클라이언트가 아직 입력하지 않는다는 가정.
		int rowcnt = prodDAO.insertProd(prod);
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
//		ServiceResult result = null;
//		try {
//			retrieveProd(prod.getProdId());
//			result = ServiceResult.PKDUPLICATED;
//		}catch(UserNotFoundException w) {
//			int rowcnt = prodDAO.insertProd(prod);
//			result = rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
//		}
//		return result;
	}

	@Override
	public ServiceResult modifyProd(ProdVO prod) {
		ProdVO inputData = new ProdVO();
		inputData.setProdId(prod.getProdId());
		ServiceResult result = modifyProd(inputData);

		if(ServiceResult.OK.equals(result)) {
			int rowcnt = prodDAO.updateProd(prod);
			result = rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		}
		return result;
	}

}
