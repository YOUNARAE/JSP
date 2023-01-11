package kr.or.ddit.member.vo;

import java.io.IOException;
import java.io.Serializable;
import java.util.Base64;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

import org.springframework.web.multipart.MultipartFile;

//랭스와 사이즈 중에 프레임워크의 종속성을 최대한 갖지 않도록 하기 위해 size로 선택했다.
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@EqualsAndHashCode(of="mId")
@Data
@NoArgsConstructor
public class MemberVO implements Serializable{
	@NotBlank
	private Integer mId;
	@NotBlank
	private String mNm;
	private String tel;
	private String ymd;
	
}