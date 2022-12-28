package kr.or.ddit.vo;

import java.io.Serializable;

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
 */
public class MemberVO implements Serializable{
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
	@Pattern(regexp="\\d{4}-\\d{2}-\\d{2}")
	private String memMemorial;
	private String memMemorialday;
	@Min(0)
	//value라고 써있으면 값을 '' 없이 넣을 수 있다.
	//낫블랭크는 스트랑타입으로 쓸 수는 없다
	private Integer memMileage;
	private String memDelete;
	
	
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemPass() {
		return memPass;
	}
	public void setMemPass(String memPass) {
		this.memPass = memPass;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getMemRegno1() {
		return memRegno1;
	}
	public void setMemRegno1(String memRegno1) {
		this.memRegno1 = memRegno1;
	}
	public String getMemRegno2() {
		return memRegno2;
	}
	public void setMemRegno2(String memRegno2) {
		this.memRegno2 = memRegno2;
	}
	public String getMemBir() {
		return memBir;
	}
	public void setMemBir(String memBir) {
		this.memBir = memBir;
	}
	public String getMemZip() {
		return memZip;
	}
	public void setMemZip(String memZip) {
		this.memZip = memZip;
	}
	public String getMemAdd1() {
		return memAdd1;
	}
	public void setMemAdd1(String memAdd1) {
		this.memAdd1 = memAdd1;
	}
	public String getMemAdd2() {
		return memAdd2;
	}
	public void setMemAdd2(String memAdd2) {
		this.memAdd2 = memAdd2;
	}
	public String getMemHometel() {
		return memHometel;
	}
	public void setMemHometel(String memHometel) {
		this.memHometel = memHometel;
	}
	public String getMemComtel() {
		return memComtel;
	}
	public void setMemComtel(String memComtel) {
		this.memComtel = memComtel;
	}
	public String getMemHp() {
		return memHp;
	}
	public void setMemHp(String memHp) {
		this.memHp = memHp;
	}
	public String getMemMail() {
		return memMail;
	}
	public void setMemMail(String memMail) {
		this.memMail = memMail;
	}
	public String getMemJob() {
		return memJob;
	}
	public void setMemJob(String memJob) {
		this.memJob = memJob;
	}
	public String getMemLike() {
		return memLike;
	}
	public void setMemLike(String memLike) {
		this.memLike = memLike;
	}
	public String getMemMemorial() {
		return memMemorial;
	}
	public void setMemMemorial(String memMemorial) {
		this.memMemorial = memMemorial;
	}
	public String getMemMemorialday() {
		return memMemorialday;
	}
	public void setMemMemorialday(String memMemorialday) {
		this.memMemorialday = memMemorialday;
	}
	public Integer getMemMileage() {
		return memMileage;
	}
	public void setMemMileage(Integer memMileage) {
		this.memMileage = memMileage;
	}
	public String getMemDelete() {
		return memDelete;
	}
	public void setMemDelete(String memDelete) {
		this.memDelete = memDelete;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((memId == null) ? 0 : memId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MemberVO other = (MemberVO) obj;
		if (memId == null) {
			if (other.memId != null)
				return false;
		} else if (!memId.equals(other.memId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "MemberVO [memId=" + memId + ", memName=" + memName + ", memBir=" + memBir + ", memZip=" + memZip
				+ ", memAdd1=" + memAdd1 + ", memAdd2=" + memAdd2 + ", memHometel=" + memHometel + ", memComtel="
				+ memComtel + ", memHp=" + memHp + ", memMail=" + memMail + ", memJob=" + memJob + ", memLike="
				+ memLike + ", memMemorial=" + memMemorial + ", memMemorialday=" + memMemorialday + ", memMileage="
				+ memMileage + ", memDelete=" + memDelete + "]";
	}
	
	
}