package kr.or.ddit.memo.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import kr.or.ddit.memo.MemoTestView;
import kr.or.ddit.memo.dao.FileSystemMemoDAOImpl;
import kr.or.ddit.memo.dao.MemoDAO;
import kr.or.ddit.memo.service.MemoService;
@ComponentScan("kr.or.ddit.memo") //필터는 true인 상태
@Lazy //lazy-init을 true라고 해놓은 것과 다름 없다
public class MemoContextConfiguration { //Configuration 이걸 쓰는 구조라하면 당연히 어노테이션을 쓴다는 게 기본 값이라서 따로 어노테이션을 설정하지 않는다
//	@Bean //다오 한개를 빈으로 등록했다.
//	//스프링에서 빈은 기본적으로 싱글톤 구조로 되어있다.
//	@Scope("prototype") //얘 한정으로는 싱글톤이 아닌 prototype으로 한다, 
//	public MemoDAO memoDAO() {
//		return new FileSystemMemoDAOImpl();
//	}
//	
//	@Bean
//	public MemoService generateService(MemoDAO dao) {
//		return new MemoService(dao);
//	}
//	
//	@Bean("testView") //아이디 이름이 없으면 메소드명이 이름이 된다
//	public MemoTestView testView(MemoService service) {
//		MemoTestView view = new MemoTestView();
//		view.setMemoService(service);
//		return view;
//	}
}
