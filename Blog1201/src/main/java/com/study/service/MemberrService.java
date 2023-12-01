package com.study.service;

import com.study.model.MemberrVO;

public interface MemberrService {
	//회원가입
	public void memberJoin(MemberrVO memberr) throws Exception;
	
	//아이디 중복 검사
	public int idCheck(String memberrId) throws Exception;
	
	//로그인
	public MemberrVO memberLogin(MemberrVO memberr) throws Exception;
	
	/* 주문자 정보 */
	public MemberrVO getMemberInfo(String memberrId);
}
