<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
      <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/resources/css/memberr/mains.css">
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
</head>
<body>

<div class="wrapper">
	<div class="wrap">
		<div class="top_gnb_area">
			<ul class="list">
				<c:if test = "${memberr == null }">	<!-- 로그인 x -->
				<li>
					<a href="/memberr/logins">로그인</a>
				</li>
				<li>
					<a href="/memberr/joins">회원가입</a>
				</li>
				</c:if>
				<c:if test="${memberr != null }">	<!-- 로그인 o -->
					<c:if test="${memberr.adminCk == 1 }"><!-- 관리자계정 -->
						<li><a href="/admins/mains">관리자 페이지</a></li>
					</c:if>
					<li>
						<a id="gnb_logout_button">로그아웃</a>
					</li>
					<li>
						마이룸
					</li>
					<li>
						<a href="/cart/${memberr.memberrId}">장바구니</a>
					</li>
				</c:if>
				<li>
					고객센터
				</li>
			</ul>
		</div>
		<div class="top_area">
			<div class="logo_area">
				<a href="/memberr/mains"><img src="/resources/img/book.jpg"></a>
			</div>
			<div class="search_area">
				
				<div class="search_wrap">
                		<form id="searchForm" action="/search" method="get">
                			<div class="search_input">
                			
                			<select name="type">
                					<option value="T">책 제목</option>
                					<option value="A">작가</option>
                				</select>
                			
                				<input type="text" name="keyword">
                    			<button class='btn search_btn'>검 색</button>                				
                			</div>
                		</form>
                	</div>
				
			</div>
			<div class="login_area">
			<!-- 로그인 하지 않은 상태 -->
			<c:if test = "${memberr == null }">
				<div class="login_button"><a href="/memberr/logins">로그인</a></div>
				<span><a href="/memberr/joins">회원가입</a></span>
				</c:if>
				
				<!-- 로그인한 상태 -->
				<c:if test="${memberr !=null }">
					<div class="login_success_area">
						<span>회원 : ${memberr.memberrName}</span>
						
						<span>충전금액 :<fmt:formatNumber value="${memberr.money}" pattern="\#,###.##"/></span>
						
						<span>포인트 : <fmt:formatNumber value="${memberr.point}" pattern="#,###"/></span>
						<a href="/memberr/logout.do">로그아웃</a>
					</div>
				</c:if>
				
			</div>
			<div class="clearfix"></div>			
		</div>
		
			
			
			<div class="navi_bar_area">
			<div class="dropdown">
			    <button class="dropbtn">국내 
			      <i class="fa fa-caret-down"></i>
			    </button>
			    <div class="dropdown-content">
			    	<a href="/search?type=C&cateCode=202001">교양</a>
			    	
			     <c:forEach items="${cate1}" var="cate"> 
		    		<a href="/search?type=C&cateCode=${cate.cateCode}">${cate.cateName}</a>
		    	</c:forEach>    		      		      
			    </div>			
			</div>
			<div class="dropdown">
			    <button class="dropbtn">국외 
			      <i class="fa fa-caret-down"></i>
			    </button>
			    <div class="dropdown-content">
			     <c:forEach items="${cate2}" var="cate"> 
		    		<a href="search?type=C&cateCode=${cate2.cateCode}">${cate2.cateName}</a>
		    	</c:forEach>  	      		      		      
			    </div>			
			</div>
		</div>
			
		<div class="content_area">
			<h1>content area</h1>
		</div>
		
		<!-- Footer 영역 -->
        <div class="footer_nav">
            <div class="footer_nav_container">
                <ul>
                    <li>회사소개</li>
                    <span class="line">|</span>
                    <li>이용약관</li>
                    <span class="line">|</span>
                    <li>고객센터</li>
                    <span class="line">|</span>
                    <li>광고문의</li>
                    <span class="line">|</span>
                    <li>채용정보</li>
                    <span class="line">|</span>
                </ul>
            </div>
        </div> <!-- class="footer_nav" -->
        

		
	</div>
</div>

  

<script>

/*gnb_area 로그아웃 버튼 작동*/

 $("#gnb_logout_button").click(function(){
	// alert("버튼작동");
	$.ajax({
		type:"POST",
		url:"/memberr/logout.do",
		success:function(data){
			alert("로그아웃 성공");
			document.location.reload();
		}
	}); //ajax
 });

</script>

</body>
</html>