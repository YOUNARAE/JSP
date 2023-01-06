package kr.or.ddit.vo;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.Part;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import kr.or.ddit.mvc.multipart.MultipartFile;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.UpdateGroup;
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
	
	private int rnum; //null인 경우를 빼야해서 Integer가 아닌 int다
	@NotBlank(groups=UpdateGroup.class)
	private String prodId;
	@NotBlank(groups=InsertGroup.class) //인서트할때만 검증한다. 상품 이름은 수정할 땐 바뀌면 안되니까
	private String prodName;
	
	@NotBlank(groups=InsertGroup.class)
	private String prodLgu;
	private String lprodNm; // lprod네임만 가져와도 되서
	
	@NotBlank(groups=InsertGroup.class)
	private String prodBuyer;
	private BuyerVO buyer; // has a , buyer를 이용해 갖고올 데이터들이 많다. 단순 바인딩 구조가 아니라서 xml에서 헤즈관계를 만들어준다
	
	@NotNull
	@Min(0)
	private Integer prodCost;
	@NotNull
	@Min(0)
	private Integer prodPrice;
	@NotNull
	@Min(0)
	private Integer prodSale;
	@NotBlank
	private String prodOutline;
	private String prodDetail;
	
	@NotBlank(groups=InsertGroup.class)
	private String prodImg; // 데이타베이트 PROD 테이블 조회용 프로버피, 여기서 온  prodImage를 받는다고 하면,
	
	private MultipartFile prodImage; //이 인터페이스의 타입으로 받으면 서블릿 스펙 버전에 대해 고려할 필요없다
	public void setProdImage(MultipartFile prodImage) {
		if(prodImage!=null && !prodImage.isEmpty() 
				&& prodImage.getContentType().startsWith("image/")) { //이 세가지 조건을 만족하면, 
			this.prodImage = prodImage;
			this.prodImg = UUID.randomUUID().toString(); //DB와 커뮤니케이션을 위해 만들어놓는다.
		}
	}
	public void saveTo(File saveFolder) throws IOException {
		//이 안에서 저장하기 위한 모든 것을 담을 수 있다.
		if(prodImage==null || prodImg==null) return;//저장할 대상이 없음 
		File saveFile = new File(saveFolder, prodImg);	
		prodImage.transferTo(saveFile);
	}
	
	@NotNull
	@Min(0)
	private Integer prodTotalstock;
	private String prodInsdate;
	@NotNull
	@Min(0)
	private Integer prodProperstock;
	private String prodSize;
	private String prodColor;
	private String prodDelivery;
	private String prodUnit;
	private Integer prodQtyin;
	private Integer prodQtysale;
	private Integer prodMileage;
	
//	private MemberVO member; <- 이렇게 쓰면 has a 한 사람당 한 상품밖에 가져올 수밖에 없어서 안됨.
	private Set<MemberVO> memberSet; // has many 관계
	
	private int memCount;
}
