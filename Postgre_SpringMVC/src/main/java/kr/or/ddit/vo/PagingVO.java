package kr.or.ddit.vo;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 페이징과 관련한 모든 데이터를 가진 객체
 */
@Getter
@NoArgsConstructor
@ToString
public class PagingVO<T> { //<T>타입이라서 어떤 도메인을 기준으로 해도 적용이 가능하다
	
	public PagingVO(int screenSize, int blockSize) {
		super();
		this.screenSize = screenSize;
		this.blockSize = blockSize;
	}

	private int totalRecord; // DB 조회
	private int screenSize=10; // 임의 설정(개발자가 정할 수 있음)
	private int blockSize=5; // 임의 설정(개발자가 정할 수 있음)
	
	private int currentPage; // 클라이언트 파라미터
	
	private int totalPage;
	private int startRow; 
	private int endRow;
	private int startPage;
	private int endPage;
	
	private List<T> dataList;
	
	private SearchVO simpleCondition; //단순검색용(검색할 때 조건 넣고 키워드 받을 수 있기 위해서 만들음)
	private T detailCondition; // 상세 검색용이 가능한 객체, ProdVO으로 하면 멤버할 때 또 못 써먹기때문에 제너릭이 필요하다
	
	public void setDetailCondition(T detailCondition) {
		this.detailCondition = detailCondition;
	}
	
	public void setSimpleCondition(SearchVO simpleCondition) {
		this.simpleCondition = simpleCondition;
	}
	
	public void setDetaList(List<T> dataList) {
		this.dataList = dataList;
	}
	
	// 두개의 세터를 제외한 것들은 연산으로 처리한다
	public void setTotalRecord(int totalRecord) { // 한번은 반드시 호출되어야한다. 이때 계산됨
		this.totalRecord = totalRecord;
		totalPage = (totalRecord + (screenSize - 1)) / screenSize;
	}
	
	public void setCurrentPage(int currentPage) { // 이 메서드가 호출되는 순간 커런트 페이지가 생성된다
		this.currentPage = currentPage;
		endRow = currentPage * screenSize; //20
		startRow = endRow - (screenSize - 1); //11
		endPage = ((currentPage + (blockSize - 1)) / blockSize) * blockSize;
		// 5 - > 9 -> 1 -> 9
		startPage = endPage - (blockSize -1);
	} 
	
//	private final String APATTERN = "<a href='?page=%d'>%s</a>";
	private final String APATTERN = "<a class='paging' href='#' data-page='%d'>%s</a>"; //data-page는 키가 page가 된다
	
	public String getPagingHTML() {
		//html을 반환받을 꺼라서 String타입
		StringBuffer html = new StringBuffer();
		
		if( startPage > blockSize ) { 
			//이전 페이지가 존재할 때
			//다음으로 이동하기 위한 for문
			html.append(
				String.format(APATTERN, startPage-blockSize, "이전" )
			);
		}
		endPage = endPage > totalPage ? totalPage : endPage;
		for(int page=startPage; page<=endPage; page++) {
			if(page==currentPage) { //현재 페이지가 현재페이지라면
				html.append(
					"<a href='#'>"+page+"</a>"
				);
			} else {
				html.append(
					String.format(APATTERN, page, page+"")
				);
			}
		}
		
		if( endPage < totalPage ) { //마지막 페이지가 전체페이지보다 작을때의 경우의 수만 생각하면 됨
			html.append(
				String.format(APATTERN, endPage+1, "다음" )
			);
		}
		
		return html.toString(); //html은 문자라서 toString
	}
	
}
