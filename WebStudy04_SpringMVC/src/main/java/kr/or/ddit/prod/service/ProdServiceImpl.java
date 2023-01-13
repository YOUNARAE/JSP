package kr.or.ddit.prod.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.prod.dao.ProdDAO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor //생성자 필요하다
@Service
public class ProdServiceImpl implements ProdService {
	
	private final ProdDAO prodDAO;
	
	@Inject
	private WebApplicationContext context;
	private File saveFolder;
	
	@PostConstruct //마지막에 실행되라
	private void init() throws IOException { //예외는 컨테이너 -> 웹용이라 콘테이너
		String saveFolderURL = "/recources/prodImages";
//		ServletContext application = req.getServletContext(); 
//		String saveFolderPath = application.getRealPath(saveFolderURL);
		Resource saveFolderRes = context.getResource(saveFolderURL);
		saveFolder = saveFolderRes.getFile();
		if(!saveFolder.exists()) 
			saveFolder.mkdirs(); 
	}
	
	private void processProdImage(ProdVO prod) {
		try {
			if(1==1) throw new RuntimeException("트랜잭션 관리 여부 확인을 위한 강제 발생 예외");
			prod.saveTo(saveFolder);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} 		
	}

	@Override
	public ProdVO retrieveProd(String prodId) {
		ProdVO prod = prodDAO.selectProd(prodId);
		if(prod==null)
			throw new RuntimeException(String.format("%s 는 없는 상품", prodId));
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
	@Inject
	private SqlSessionFactory sqlSessionFactory;

	@Override
	public ServiceResult createProd(ProdVO prod) { //클라이언트가 아직 입력하지 않는다는 가정.
//		session oepn
		try(
		SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			int rowcnt = prodDAO.insertProd(prod, sqlSession);
			
			processProdImage(prod);
			
			sqlSession.commit();
			
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
	}

	@Override
	public ServiceResult modifyProd(ProdVO prod) {
		retrieveProd(prod.getProdId()); //존재하지 않는지 확인하려면 리트리브PROD만 건드리면 된다
		int rowcnt = prodDAO.updateProd(prod);
		
		processProdImage(prod);
		
		ServiceResult result = rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		return result;
	}

}
