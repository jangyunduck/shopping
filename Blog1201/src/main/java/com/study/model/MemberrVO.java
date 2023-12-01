package com.study.model;

public class MemberrVO {
	//회원 id
	private String  memberrId;
	//회원 비밀번호
	private String memberrPw;
	//회원 이름 
	private String memberrName;
	//회원 이메일
	private String memberrMail;
	//회원 우편번호
	private String memberrAddr1;
	//회원 주소
	private String memberrAddr2;
	//회원 상세주소
	private String memberrAddr3;
	//관리자 구분 (0:일반사용자, 1: 관리자)
	private int adminCk;
	//등록일자
	private int regDate;
	//회원 돈
	private int money;
	//회원 포인트
	private int point;
	public String getMemberrId() {
		return memberrId;
	}
	public void setMemberrId(String memberrId) {
		this.memberrId = memberrId;
	}
	public String getMemberrPw() {
		return memberrPw;
	}
	public void setMemberrPw(String memberrPw) {
		this.memberrPw = memberrPw;
	}
	public String getMemberrName() {
		return memberrName;
	}
	public void setMemberrName(String memberrName) {
		this.memberrName = memberrName;
	}
	public String getMemberrMail() {
		return memberrMail;
	}
	public void setMemberrMail(String memberrMail) {
		this.memberrMail = memberrMail;
	}
	public String getMemberrAddr1() {
		return memberrAddr1;
	}
	public void setMemberrAddr1(String memberrAddr1) {
		this.memberrAddr1 = memberrAddr1;
	}
	public String getMemberrAddr2() {
		return memberrAddr2;
	}
	public void setMemberrAddr2(String memberrAddr2) {
		this.memberrAddr2 = memberrAddr2;
	}
	public String getMemberrAddr3() {
		return memberrAddr3;
	}
	public void setMemberrAddr3(String memberrAddr3) {
		this.memberrAddr3 = memberrAddr3;
	}
	public int getAdminCk() {
		return adminCk;
	}
	public void setAdminCk(int adminCk) {
		this.adminCk = adminCk;
	}
	public int getRegDate() {
		return regDate;
	}
	public void setRegDate(int regDate) {
		this.regDate = regDate;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	@Override
	public String toString() {
		return "MemberrVO [memberrId=" + memberrId + ", memberrPw=" + memberrPw + ", memberrName=" + memberrName
				+ ", memberrMail=" + memberrMail + ", memberrAddr1=" + memberrAddr1 + ", memberrAddr2=" + memberrAddr2
				+ ", memberrAddr3=" + memberrAddr3 + ", adminCk=" + adminCk + ", regDate=" + regDate + ", money="
				+ money + ", point=" + point + "]";
	}
	
	
}
