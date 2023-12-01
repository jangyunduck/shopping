

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.study.mapper.MemberrMapper;
import com.study.model.MemberrVO;




@WebAppConfiguration 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"}) //root-context.xml파일 로딩 위치 
public class MemberrMappertest {
	
	
	@Autowired//안될때는 루트 컨택트 들어가서 설정을 살펴보아라
	 private MemberrMapper memberrmapper;
	 
	
	
	
	/*
	 * @Test public void memberJoins() throws Exception{ MemberrVO members = new
	 * MemberrVO();
	 * 
	 * members.setMemberrId("test"); members.setMemberrPw("test");
	 * members.setMemberrName("test"); members.setMemberrMail("test");
	 * members.setMemberrAddr1("test"); members.setMemberrAddr2("test");
	 * members.setMemberrAddr3("test");
	 * 
	 * memberrmapper.memberJoin(members); }
	 */
	
	@Test
	public void memberrIdChk() throws Exception{
		String id = "test"; // 존재하는 아이디
		String id2 = "test123"; // 존재하지 않는 아이디
		
		memberrmapper.idCheck(id);
		memberrmapper.idCheck(id2);
	}
	
	@Test
	public void memberLogin() throws Exception{
		MemberrVO memberr = new MemberrVO(); //MemberrVO 변수 선언 및 초기화
		
		/*올바른 아이디 비번 입력경우*/
		memberr.setMemberrId("ss");
		memberr.setMemberrPw("ss");
		
		memberrmapper.memberLogin(memberr);
		System.out.println("결과 값 : " + memberrmapper.memberLogin(memberr));
	}
	
}










