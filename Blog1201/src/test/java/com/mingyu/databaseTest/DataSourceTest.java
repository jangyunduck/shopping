package com.mingyu.databaseTest;




import java.sql.Connection;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})  //root-context.xml  파일을 가져와서 사용.
public class DataSourceTest {

	

	
	@Inject
	private DataSource dataSource;
	

	
	@org.junit.Test
	public void test() throws Exception{
	
		try(Connection conn = dataSource.getConnection()) {
			System.out.println("출력");
			System.out.println(conn);
			
			
	} catch (Exception e) {
			// TODO: handle exception
			System.out.println("실패");
			e.printStackTrace();
		}
		
		
		
	}

}
