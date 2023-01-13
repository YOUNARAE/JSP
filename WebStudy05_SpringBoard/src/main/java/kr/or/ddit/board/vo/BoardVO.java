package kr.or.ddit.board.vo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(of="boNo")
@ToString(exclude= {"boContent","boPass"})
public class BoardVO implements Serializable {
	
	private int rnum;
	
	private String boNo;
	private String boTitle;
	private String boWriter;
	private String boIp;
	private String boMail;
	@JsonIgnore
	private transient String boPass;
	private String boContent;
	private String boDate;
	private String boHit;
	
	// 보드 VO가 ATTACHVO 를 매니로 갖고 있어야한다
	private List<AttatchVO> attatchList; //has many
	
	private int[] delAttNos; // 게시글 수정시 삭제할 첨부파일 번호 유지.
	
	private int attCount;
}
