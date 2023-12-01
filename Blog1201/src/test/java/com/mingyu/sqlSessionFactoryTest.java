package com.mingyu;




import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})  //root-context.xml  파일을 가져와서 사용.
public class sqlSessionFactoryTest {

	
	private static final Logger logger = LoggerFactory.getLogger(sqlSessionFactoryTest.class);
	
	@Inject
	private SqlSessionFactory sqlFactory;
	
	
	@Test
	public void testSqlFactory() {
		
	
		System.out.print("sqlFactory!:"+sqlFactory);
		logger.info("테스트\n");
	}
	
	  @Test 
	  public void sesstionTest() throws Exception{
		  try(SqlSession sqlSessionTemplate = sqlFactory.openSession()) {
			       
			  
			  
			  	System.out.println(sqlSessionTemplate); 
			 }catch (Exception e) { 
				 System.out.println("연결실패");
				 e.printStackTrace();
			 }
		 }

}
