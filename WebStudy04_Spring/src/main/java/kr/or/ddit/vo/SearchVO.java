package kr.or.ddit.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchVO { // 두개의 파라미터를 한번에 받을 수 있는 생성자를 추가하였다
	private String searchType;
	private String searchWord;
} 
