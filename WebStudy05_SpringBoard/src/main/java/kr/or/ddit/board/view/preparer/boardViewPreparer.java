package kr.or.ddit.board.view.preparer;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;

import kr.or.ddit.board.dao.BoardDAO;
import kr.or.ddit.vo.MenuVO;

public class boardViewPreparer implements ViewPreparer{
	
	@Inject
	private BoardDAO dao;

	@Override
	public void execute(Request req, AttributeContext attrContext) {
//		게시글 작성 : /board/boardInsert.do
//		게시글 목록 조회 : /board/boardList.do
		MenuVO menu1 = new MenuVO("게시글작성", "/board/boardInsert.do");
		MenuVO menu2 = new MenuVO("목록", "/board/boardList.do");
		List<MenuVO> menuList = Arrays.asList(menu1,menu2);
		//한 세션 안에서 상단의 상품관리나 자유게시판을 클릭했을지 모르기때문에, 리퀘스트 스코프에 넣어야한다.
		Map<String, Object> scope = req.getContext(Request.REQUEST_SCOPE); //리퀘스트가 우리가 알던 리퀘스트형태가 아니기때문에 getContext로 받아주고맵으로 돌아온다.
		scope.put("menuList", menuList);
	
	}
}
