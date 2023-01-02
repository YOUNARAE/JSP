package kr.or.ddit.vo;

import static org.junit.Assert.*;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PagingVOTest {

	@Test
	public void test() {
		PagingVO pagingVO = new PagingVO(); // 10, 5
		pagingVO.setTotalRecord(108); //토탈페이지 연산 ->11이 됨
		pagingVO.setCurrentPage(3); //
		log.info("paging : {}", pagingVO);
		
		pagingVO.setCurrentPage(7);
		log.info("paging : {}", pagingVO);
	}

}
