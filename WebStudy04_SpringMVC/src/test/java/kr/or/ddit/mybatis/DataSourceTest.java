package kr.or.ddit.mybatis;

import static org.junit.Assert.*;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import kr.or.ddit.member.dao.MemberDAO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/datasource-context.xml")
public class DataSourceTest {
	@Inject
	private MemberDAO memberDAO;

	private DataSource dataSource;
	@Test
	public void test() {
		log.info("주입된 객체 : {}",dataSource);
	}

}
