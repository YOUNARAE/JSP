package kr.or.ddit.mybatis;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisUtils {
   private static SqlSessionFactory sqlSessionFactory;
   //SqlSessionFactory는 어플리케이션 전체를 통틀어서 1개만 생성되는 싱글톤 객체
   static {
      String configPath = "kr/or/ddit/mybatis/mybatis-config.xml";
      try (
         Reader reader = Resources.getResourceAsReader(configPath);
      ){
         sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }
   
   public static SqlSessionFactory getSqlSessionFactory() {
      return sqlSessionFactory;
   }
}