<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<a href="/tt/upload/input.do">업로드 테스트</a>
<a href="/admin/shop/product/list">상품목록</a>
<a href="/tt/pdf/list.do">PDF</a>

<a href="/tt/chart/chart1.do">구글차트(json)</a>
<a href="/tt/chart/chart2.do">구글차트(db)</a>
<a href="/tt/jchart/chart1.do">JFreeChart1(png)</a>
<a href="/tt/jchart/chart2.do">JFreechart2(pdf)</a>

<a href="/tt/email/write.do">이메일 발송</a>

<div style="text-align:right;">
<c:choose>
	<c:when test="${sessionScope.userid == null }">
		<a href="/members/login">로그인</a>
		<a href="/tt/admin/login.do">관리자 로그인</a>
	</c:when>
	<c:otherwise>
		${sessionScope.name }님이 로그인중입니다.
		<a href="/members/logout">로그아웃</a>
		<a href="/tt/admin/logout">관리자 로그아웃</a>
	</c:otherwise>
</c:choose>
</div>
<hr>