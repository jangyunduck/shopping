package com.study.mapper;

import com.study.model.MemberrVO;

public interface MemberrMapper {
	
	//회원가입
	public void memberJoin(MemberrVO memberr) throws Exception;
	
	//아이디 중복 검사
	public int idCheck(String memberrId);
	
	//로그인
	public MemberrVO memberLogin(MemberrVO memberr);
	
	/* 주문자 주소 정보 */
	public MemberrVO getMemberInfo(String memberrId);
}
