import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.study.mapper.AuthorMapper;
import com.study.model.AuthorVO;
import com.study.model.Criteria;

@WebAppConfiguration//폼버전을 업그레이드 했을때 잘되던 junit테스트가 안된다면 이 어노테이션을 작성해보라
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class AuthorMapperTest {
	
	@Autowired
	private AuthorMapper mapper;
	
	/*작가 등록 테스트*/
	/*
	 * @Test public void authorEnroll() throws Exception{ AuthorVO author = new
	 * AuthorVO();
	 * 
	 * author.setAuthorId(1); author.setAuthorName("테스트"); author.setNationId("01");
	 * author.setNationName("테스트"); author.setAuthorIntro("테스트 소개");
	 * 
	 * //AuthorMapper에 있는 변수랑 이 변수랑 갯수가 같아야 확인이 가능하다.
	 * 
	 * mapper.authorEnroll(author); }
	 */
	/*작가 목록 테스트*/
	/*
	 * @Test public void authorGetListTest() throws Exception{
	 * 
	 * Criteria cri = new Criteria(1, 10); cri.setKeyword("코난"); List<AuthorVO> list
	 * = mapper.authorGetList(cri);
	 * 
	 * 
	 * 
	 * for(int i = 0; i < list.size(); i++) { System.out.println("list" + i +
	 * "........" + list.get(i)); }
	 * 
	 * }
	 */
	
	/*작가 총 수*/
	/*
	 * @Test public void authorGetTotalTesst() throws Exception{
	 * 
	 * Criteria cri = new Criteria(); cri.setKeyword("코난");
	 * 
	 * int total = mapper.authorGetTotal(cri);
	 * 
	 * System.out.println("total......" + total);
	 * 
	 * }
	 */
	
	/*작가 상세 페이지*/
	/*
	 * @Test public void authorGetDetailTest() {
	 * 
	 * int authorId = 3;
	 * 
	 * AuthorVO author = mapper.authorGetDetail(authorId);
	 * 
	 * System.out.println("author....." + author); }
	 */
	
	/*작가 정보 수정*/
	/*
	 * @Test public void authorModifyTest() { AuthorVO author = new AuthorVO();
	 * 
	 * author.setAuthorId(3); System.out.println("수정전 ......"
	 * +mapper.authorGetDetail(author.getAuthorId()));
	 * 
	 * author.setAuthorName("수정"); author.setNationId("02");
	 * author.setAuthorIntro("소개 수정 하였습니다.");
	 * 
	 * mapper.authorModify(author); System.out.println("수정 후......."
	 * +mapper.authorGetDetail(author.getAuthorId()));
	 * 
	 * }
	 */

}
