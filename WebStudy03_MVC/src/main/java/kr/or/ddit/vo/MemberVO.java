package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

//랭스와 사이즈 중에 프레임워크의 종속성을 최대한 갖지 않도록 하기 위해 size로 선택했다.
import com.fasterxml.jackson.annotation.JsonIgnore;

import kr.or.ddit.validate.DeleteGroup;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * VO(Value Object), DTO(Data Transger Object), JavaBean, Model
 * 
 * JavaBean 규약 
 * 1. 값을 담을 수 있는 property 정의
 * 2. property 캡슐화
 * 3. 캡슐화된 프로퍼티에 접근할 수 있는 인터페이스 제공 getter/setter
 * 		get[set] + 프로퍼티명의 첫문자를 대문자로 -> camel case
 * 4. 객체의 상태 비교 방법 제공 : equals
 * 		==, equalss
 * 5. 객체의 상태 확인 방법 제공 : toString // 코드를 짜고 객체의 상태가 적절하게 변경됬는지 확인하려고 할 떄,객체의 상태를 확인하려해도 객체의 상태를 노출시켜서는 안되는 것들이 있다.ex)password같은.
 *                                 // 이렇 부분들을 제외하고 toString을 만들어야한다.
 *                                 
 * 6. 객체 직렬화 가능 
 * 
 * 회원관리를 위한 Domain Layer
 * : 한 사람의 회원 정보(구매기록 포함)를 담기 위한 객체.
 *   MEMBER(1) : PROD(N) -> Has many 관계로 표현이 되어야한다.
 *   1 : 1 -> Has A 관계로 표현되어야한다.
 *   
 * **  데이터매퍼나 ORM 을 이용한 테이블 조인 방법
 * ex) 회원 정보 상세 조회시 구매 상품 기록을 함께 조회함.
 * 1. 대상 테이블 선택(대상이 되는 테이블을 고른다.)
 * 		MEMBER, CART(CART_MEMBER, CART_PROD), PROD //CARTVO가 필요없던 건 양쪽 테이블에 정보들이 들어있어서. 
 *      - 금액 총액을 구하라고 하면 케이스가 달라짐. cart도 VO를 만들어서 거기서 금액을 가져왔어야한다.
 * 2. 각 테이블로부터 데이터를 바인딩할 VO를 설계
 * 	    - MemberVO, ProdVO
 * 3. 각 테이블의 relation을 VO 사이에 has 관계로 반영
 * 		1(main Table) : N - has many, MemberVO has ProdVO(collection)
 * 		1(main Table) : 1 - has a   , ProdVO has a BuyerVO
 * 4. resultType 대신 resultMap 으로 바인딩 설정 (xml파일에서)
 *		has many : collection
 *		has a : association
 *
 * 
 */
//롬북은 모든 걸 어노테이션으로 설정한다
//사용하다가 옆에 아웃라인에 아무것도 안 들어가고 있다면 초기에 봤던 이클립스의 init 파일을 확인해서 설치되어있는지 봐야한다
//@Getter
//@Setter
@ToString(exclude= {"memPass", "memRegno1", "memRegno2"})
@EqualsAndHashCode(of="memId")
@Data //직접적인 자바빈 규약을 만족시키는 어노테이션(이것만 써도 된다)
@NoArgsConstructor //기본생성자를 생성한다
public class MemberVO implements Serializable{
	
	//아이디랑 비번만 받는 생성자를 생성해서 기본생성자가 없어서 lombok으로 생성해준다
	public MemberVO(@NotBlank(groups = { Default.class, DeleteGroup.class }) String memId,
			@NotBlank(groups = { Default.class, DeleteGroup.class }) @Size(min = 4, max = 8, groups = { Default.class,
					DeleteGroup.class }) @Size String memPass) {
		super();
		this.memId = memId;
		this.memPass = memPass;
	}
	
	private int rnum;
	
	// 그룹이 2개가 필요한데 가입할 때 검증해야하는 거, 가입,수정할 때 검증해야하는 것
	@NotBlank(groups= {Default.class, DeleteGroup.class}) //자카르타 벨리데이션 (groups= {InsertGroup.class, UpdateGroup.class})
	private String memId;
	@NotBlank(groups= {Default.class, DeleteGroup.class})
	@Size(min=4, max=8, groups={Default.class, DeleteGroup.class}) // 4글자 이상 8글자 이하
	@Size
	@JsonIgnore
	private transient String memPass;
	@NotBlank
	private String memName;
	@JsonIgnore
	private transient String memRegno1;
	@JsonIgnore
	private transient String memRegno2;
	@Pattern(regexp="\\d{4}-\\d{2}-\\d{2}", groups=InsertGroup.class)
	//regexp : 정규표현식이라는 뜻
	@NotBlank(groups=InsertGroup.class) //가입할 때만 검증하는 것
	private String memBir;
	@NotBlank
	private String memZip;
	@NotBlank
	private String memAdd1;
	@NotBlank
	private String memAdd2;
	private String memHometel;
	private String memComtel;
	private String memHp;
	@Email
	private String memMail;
	private String memJob;
	private String memLike;
	private String memMemorial;
	@Pattern(regexp="\\d{4}-\\d{2}-\\d{2}")
	private String memMemorialday;
	@Min(0)
	//value라고 써있으면 값을 '' 없이 넣을 수 있다.
	//낫블랭크는 스트랑타입으로 쓸 수는 없다
	private Integer memMileage;
	private boolean memDelete;
	private int cartCount; //Integer를 쓰면 0인 사람들을 null로 받게 되는 여지를 주게 되기때문에 그냥 int로 하는 게 낫다.
	
	private List<ProdVO> prodList; //이렇게 넣으면 1사람이 딱 1상품만 구입할 수 있다.->그래서 타입을 List나 배열로
//	has many 관계 (1:N) , 갖는다라고 표현하기때문에 has관계라고 표현해준다
	
	//롬북은 이클립스에 써야하는 플러그인이라서 메이븐에서 추가하는 것이 아님
	private String memRole;
}