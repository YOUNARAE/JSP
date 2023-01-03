package kr.or.ddit.prod.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import kr.or.ddit.vo.BuyerVO;
/**
 * 상품 분류 선택 UI와 거래처 선택 UI 완성을 위한 데이터 조회
 */
public interface OthersDAO {
	public List<Map<String, Object>> selectLprodList(); //VO가 하나도 없어도 쓸 수 있게 만들어주는 건 Map이다, 타입이 스트링일 수도 있고 바차일 수도 있어서
	
	public List<BuyerVO> selecttBuyerList(@Param("buyerLgu") String buyerLgu);
	//다 맵쓰지 말고 적절하게 섞어서 사용한다 . 다양한 케이스를 보기 위해, buyerLgu를 파라미터로 받으면 특정 거래처 정보만 넘겨줄 수 있다
	//buyerLgu라는 이름을 쓸 수 있게 된다. 파람으로 만들어줘서
}
