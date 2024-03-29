package kr.or.ddit.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.validate.InsertGroup;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/board/boardInsert.do")
public class BoardInsertController {
	private final BoardService service;
	
	@ModelAttribute("board")
	public BoardVO board() {
		return new BoardVO();
	}
	
	@GetMapping
	public String boardForm(@ModelAttribute("board") BoardVO board) {
		return "board/boardForm"; 
	}
	
	@PostMapping
	public String boardInsert(
			@Validated(InsertGroup.class) @ModelAttribute("board") BoardVO board
			, Errors errors
			, Model model //실패해서 돌아갈 때 값들을 데리고 가야해서
			, @RequestPart MultipartFile[] boFiles 
		) throws ServletException, IOException {
//		return null;
		//보트 폼으로도 갈 수 있고 보드 뷰로도 갈 수 있고
		String viewName = null;
		if(!errors.hasErrors()) {
			int rowcnt = service.createBoard(board);
			if(rowcnt>0) {
				viewName = "redirect:/board/boardView.do?what="+board.getBoNo();
			}else {
				model.addAttribute("message", "서버오류, 쫌따 다시!");
				viewName = "board/boardForm";
			}
		} else {
			viewName="board/boardForm";
		}
		return viewName;
	}
	
}
