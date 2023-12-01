package com.study.controller;

import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.study.model.MemberrVO;
import com.study.service.MemberrService;


@Repository 
@Controller
@RequestMapping(value = "/memberr")
public class MemberrController {
	
	@Autowired
	private MemberrService memberrservice;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private BCryptPasswordEncoder pwEncoder;
	
	private static final Logger logger = LoggerFactory.getLogger(MemberrController.class);
	
	//메인 페이지 이동
	@RequestMapping(value = "/mains", method = RequestMethod.GET)
	public void mainPageGET() {
		logger.info("메인 페이지 진입");
	}
	
	
	
	//회원가입 페이지 이동
	@RequestMapping(value="joins", method=RequestMethod.GET)
	public void loginGET() {
		
		logger.info("회원가입 페이지 진입");
	}
	
	//회원가입
	@RequestMapping(value="joins", method=RequestMethod.POST)
	public String joinPOST(MemberrVO memberr) throws Exception{
		//logger.info("join 진입");
		
		//회원가입 서비스 실행
		//memberrservice.memberJoin(memberr);
		//logger.info("join service 성공");
		String rawPw = "";	//인코딩 전 비밀번호
		String encodePw = "";	//인코딩 후 비밀번호
		
		rawPw = memberr.getMemberrPw();
		encodePw = pwEncoder.encode(rawPw);
		memberr.setMemberrPw(encodePw);
		
		/*회원가입 쿼리 실행*/
		memberrservice.memberJoin(memberr);
		
		
		
		
		return "redirect:/memberr/mains";//회원가입 버튼을 눌렀을때 페이지를 못찾는다 하면 여기를 확인하라
	}
	//로그인 페이지 이동
	@RequestMapping(value="/logins", method = RequestMethod.GET)
	public void joinGET() {
		
		logger.info("로그인 페이지 진입");
		
	}
	
	//아이디 중복 검사//이것을 치니 스팬부분에 글씨가 보이지 않게 되었다.
	@RequestMapping(value = "/memberrIdChk", method = RequestMethod.POST)
	@ResponseBody
	public String memberrIdChkPOST(String memberrId) throws Exception{
		logger.info("memberrIdChk()진입");
		
		int result = memberrservice.idCheck(memberrId);
		
		logger.info("결과값 = " + result);
		
		if(result !=0) {
			
			return "fail";//중복 아이디가 존재
			
		}else {
			
			return "success";// 중복 아이디 x
			
		}
	}//memberrIdChk
	
	/*이메일 인증*/
	@RequestMapping(value="/mailCheck", method=RequestMethod.GET)
	@ResponseBody
	public String mailCheckGET(String email) throws Exception{
		/*뷰(View)로 부터 넘어온 데이터 확인*/
		
		logger.info("이메일 데이터 전송 확인");
		logger.info("인증번호 : " + email);
		
		/*인증번호(난수) 생성*/
		Random random = new Random();
		int checkNum = random.nextInt(888888) + 111111;
		logger.info("인증번호" + checkNum);
		
		/*이메일 보내기*/
		String setFrom = "jyd2887@naver.com";//루트 컨택트 부분의 아이디와 일치해야 한다.
		String toMail = email;
		String title = "회원가입 인증 이메일 입니다.";
		String content = 
				"홈페이지를 방문해주셔셔 감사합니다." +
					"<br><br>" +
						"인증 번호는" + checkNum + "입니다." +
					"<br>" +
						"해당 인증번호를 인증번호 확인란에 기입하여 주세요.";
		
		 try {
	            
	            MimeMessage message = mailSender.createMimeMessage();
	            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
	            helper.setFrom(setFrom);
	            helper.setTo(toMail);
	            helper.setSubject(title);
	            helper.setText(content,true);
	            mailSender.send(message);
	            
	        }catch(Exception e) {
	            e.printStackTrace();
	        }
		 
		 String num = Integer.toString(checkNum);
		
		 return num;
	}
	
	/*로그인*/
	//@RequestMapping(value="/logins", method=RequestMethod.POST)변경전
	@RequestMapping(value="/logins.do", method=RequestMethod.POST)
	public String loginPOST(HttpServletRequest request, MemberrVO memberr, RedirectAttributes rttr) throws Exception{
		
		//System.out.println("login 메서드 진입");
		//System.out.println("전달된 데이터 : " + memberr);
		
		//HttpSession session = request.getSession();
		//MemberrVO lvo = memberrservice.memberLogin(memberr);
		
		//if(lvo == null) {		//일치하지 않는 아이디, 비밀번호
			
			//int result = 0;
			//rttr.addFlashAttribute("result",result);
			//return "redirect:/memberr/logins";
			
		//}
		//session.setAttribute("memberr", lvo);	//일치하는 아이디, 비밀번호
		
		//return "redirect:/memberr/mains";
		
		HttpSession session = request.getSession();
		String rawPw = "";
		String encodePw = "";
		
		MemberrVO lvo = memberrservice.memberLogin(memberr);	//제출한 아이디와 일치하는 아이디 있는지
		
		if(lvo != null) {	//일치하는 아이디 존재시
			
			rawPw = memberr.getMemberrPw();	//사용자가 제출한 비밀번호
			encodePw = lvo.getMemberrPw();	// 데이터베이스에 저장한 인코딩된 비밀번호
			
			if(true == pwEncoder.matches(rawPw, encodePw)) {	// 비밀번호 일치 여부 판단
					
				lvo.setMemberrPw("");	//인코딩된 비밀번호 정보 지움
				session.setAttribute("memberr", lvo);	//session에 사용자의 정보저장
				return "redirect:/memberr/mains";	//메인페이지로 이동
			}	else {
				
				rttr.addFlashAttribute("result", 0);
				return "redirect:/memberr/logins";	//로그인 페이지로 이동
				
			}
			
		}else {	//일치하는 아이디가 존재하지 않을 시 (로그인 실패)
			
			rttr.addFlashAttribute("result",  0);
			return "redirect:/memberr/logins";	//로그인 페이지로 이동
		}
		
		
	}
	/* 메인페이지 로그아웃*/
	@RequestMapping(value="logout.do", method=RequestMethod.GET)
	public String logoutMainGET(HttpServletRequest request) throws Exception{
		logger.info("logoutMainGET메서드 진입");
		
		HttpSession session = request.getSession();
		
		session.invalidate();
		
		return "redirect:/memberr/mains";
	}
	
	/*비동기방식 로그아웃 메서드*/
	@RequestMapping(value="logout.do", method=RequestMethod.POST)
	@ResponseBody
	public void logoutPOST(HttpServletRequest request) throws Exception{
		
		logger.info("비동기 로그아웃 메서드 진입");
		
		HttpSession session = request.getSession();
		
		session.invalidate();
	}
	
	
	
	
	
	
	
	
}
