

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.study.model.AuthorVO;
import com.study.service.AuthorService;


@WebAppConfiguration//폼버전을 업그레이드 했을때 잘되던 junit테스트가 안된다면 이 어노테이션을 작성해보라
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class AuthorServiceTest {
	/*AuthorService 의존성 주입*/
	
	@Autowired
	private AuthorService service;
	
	/*
	 * @Test public void authorEnrollTest() throws Exception {
	 * 
	 * AuthorVO author = new AuthorVO();
	 * 
	 * author.setAuthorId(1); author.setAuthorName("테스트2");
	 * author.setNationId("01"); author.setNationName("테스트2");
	 * author.setAuthorIntro("테스트 소개2");
	 * 
	 * service.authorEnroll(author); }
	 */
	
	/*작가 상세 페이지*/
	/*
	 * @Test public void authorGetDetailTest() throws Exception{
	 * 
	 * int authorId = 3;
	 * 
	 * service.authorGetDetail(authorId); }
	 */
	/*작가정보 수정*/
	/*
	 * @Test public void authorModifyTest() throws Exception{
	 * 
	 * AuthorVO author = new AuthorVO();
	 * 
	 * author.setAuthorId(4); System.out.println("(service)수정 전 ....." +
	 * service.authorGetDetail(author.getAuthorId()));
	 * 
	 * author.setAuthorName("(service)수정"); author.setNationId("02");
	 * author.setAuthorIntro("(service)소개 수정 하였습니다.");
	 * 
	 * service.authorModify(author); System.out.println("(service)수정 후......" +
	 * service.authorGetDetail(author.getAuthorId()));
	 * 
	 * 
	 * 
	 * }
	 */
}
