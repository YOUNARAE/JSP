package kr.or.ddit.board.vo;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(of="attNo")
@NoArgsConstructor
@ToString(exclude="realFile")
public class AttatchVO implements Serializable{
	@JsonIgnore
	private transient MultipartFile realFile;
	//첨부파일 한 건에 대한 객체
	//AttatchVO를 realFile파일의 어댑터처럼 사용할 수 있다.
	public AttatchVO(MultipartFile realFile) {
		super();
		this.realFile = realFile;
		this.attFilename = realFile.getOriginalFilename();
		//원본파일은 MultipartFile이 가지고 있다
		this.attSavename = UUID.randomUUID().toString(); //원본파일명을 그대로 사용하지 않아서 필요한 것
		this.attMime = realFile.getContentType();
		this.attFilesize = realFile.getSize();
		this.attFancysize = FileUtils.byteCountToDisplaySize(attFilesize);
	} // 멀티파트를 받으려하기 때문에 클라이언트한테 파일을 받으려고 필요한 생성자

	//기본형 생성자도 필요하다.
	
	private Integer attNo;
	private Integer boNo;
	private String attFilename;
	private String attSavename;
	private String attMime;
	private Long attFilesize;
	private String attFancysize;
	private Integer attDownload;
	
	public void saveTo(File saveFolder) throws IOException {
		if(realFile==null || realFile.isEmpty()) return;
		realFile.transferTo(new File(saveFolder, attSavename));
	}
}
