package com.study.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.mapper.MemberrMapper;
import com.study.model.MemberrVO;

@Service
public class MemberrServiceImpl implements MemberrService{
	
	@Autowired
	MemberrMapper memberrmapper;
	
	@Override
	public void memberJoin(MemberrVO memberr) throws Exception {
		memberrmapper.memberJoin(memberr);
		
	}
	
	@Override
	public int idCheck(String memberrId) throws Exception{
		
		return memberrmapper.idCheck(memberrId);
	}
	
	//로그인
	@Override
	public MemberrVO memberLogin(MemberrVO memberr) throws Exception{
		
		return memberrmapper.memberLogin(memberr);
	}
	
	/* 주문자 정보 */
	@Override
	public MemberrVO getMemberInfo(String memberrId) {
		
		return memberrmapper.getMemberInfo(memberrId);
		
	}
	
	
	
	
}





