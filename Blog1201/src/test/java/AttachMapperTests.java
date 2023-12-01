import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.study.mapper.AttachMapper;

@WebAppConfiguration // 폼버전을 업그레이드 했을때 잘되던 junit테스트가 안된다면 이 어노테이션을 작성해보라
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
public class AttachMapperTests {
	
	@Autowired
	private AttachMapper attachMapper;
	
	
	/*이미지 정보 반환*/
	@Test
	public void getAttachListTests() {
		
		int bookId = 18;
		
		System.out.println("이미지 정보 : " + attachMapper.getAttachList(bookId));
		
		
	}
}
