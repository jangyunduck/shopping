package com.mingyu.databaseTest;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;



@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration //mvc를 테스트 하기 위해 필요한 어노테이션  
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"}) //root-context.xml파일 로딩 위치 
public class ControllerTest {

	
	    // pom.xml에서 servlet 버전 높여 주어야됨
		private static final Logger logger= LoggerFactory.getLogger(ControllerTest.class);
	
	
		 @Inject  //스프링에서 주입
		 private WebApplicationContext webAppCtx;  //컨택스트 객체
		 private MockMvc mocMvc; //컨테이너에서 브라우저에 요청과 응답을 하는 객체 
		 
		 
		 //테스트 실행전에 실행 
		 @Before
		 public void setup() {
			 this.mocMvc = MockMvcBuilders.webAppContextSetup(this.webAppCtx).build();
			 logger.info("호출");
		 }
		 
		 
		
		@Test
		public void testController() throws Exception{
			mocMvc.perform(MockMvcRequestBuilders.get("/"));
		}
		 
		 
}

