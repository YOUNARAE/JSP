package kr.or.ddit.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
 *                                
 * 
 */

public class MemberVO implements Serializable{
	private String memId;
	@JsonIgnore
	private transient String memPass;
	private String memName;
	@JsonIgnore
	private transient String memRegno1;
	@JsonIgnore
	private transient String memRegno2;
	private String memBir;
	private String memZip;
	private String memAdd1;
	private String memAdd2;
	private String memHometel;
	private String memComtel;
	private String memHp;
	private String memMail;
	private String memJob;
	private String memLike;
	private String memMemorial;
	private String memMemorialday;
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