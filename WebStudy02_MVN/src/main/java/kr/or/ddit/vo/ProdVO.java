package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 상품 하나의 정보(분류명, 거래처 정보)를 담기 위한 객체
 * PROD (1) :  BUYER(1) -> has a
 */
@Data
@EqualsAndHashCode(of="prodId")
@ToString(exclude="prodDetail") //CLOB 타입이 용량이 너무 커서 투스트링으로 뽑아내면 서버 다운될 가능성이 있다. 미리 제외시키기.
public class ProdVO implements Serializable{
	
	private int rnum;
	
	private String prodId;
	private String prodName;
	
	private String prodLgu;
	private String lprodNm;
	
	private String prodBuyer;
	private BuyerVO buyer; // has a
	private int cartQty;
	
	private String prodCost;
	private String prodPrice;
	private String prodSale;
	private String prodOutline;
	private String prodDetail;
	private String prodImg;
	private String prodTotalstock;
	private String prodInsdate;
	private String prodProperstock;
	private String prodSize;
	private String prodColor;
	private String prodDelivery;
	private String prodUnit;
	private String prodQtyin;
	private String prodQtysale;
	private String prodMileage;
	
//	private MemberVO member; <- 이렇게 쓰면 has a 한 사람당 한 상품밖에 가져올 수밖에 없어서 안됨.
	private Set<MemberVO> memberSet; // has many 관계
}
