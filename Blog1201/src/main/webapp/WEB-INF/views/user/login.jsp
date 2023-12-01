<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="Dashboard">
  <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
  <title>Dashio - Bootstrap Admin Template</title>

  <!-- Favicons -->
  <link href="/resources/img/favicon.png" rel="icon">
  <link href="/resources/img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Bootstrap core CSS -->
  <link href="/resources/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <!--external css-->
  <link href="/resources/lib/font-awesome/css/font-awesome.css" rel="stylesheet" />
  <link rel="stylesheet" type="text/css" href="/resources/css/zabuto_calendar.css">
  <link rel="stylesheet" type="text/css" href="/resources/lib/gritter/css/jquery.gritter.css" />
  <!-- Custom styles for this template -->
  <link href="/resources/css/style.css" rel="stylesheet">
  <link href="/resources/css/style-responsive.css" rel="stylesheet">
  <script src="/resources/lib/chart-master/Chart.js"></script>
  <script src="/resources/lib/jquery/jquery.min.js"></script>
  
  <!-- =======================================================
    Template Name: Dashio
    Template URL: https://templatemag.com/dashio-bootstrap-admin-template/
    Author: TemplateMag.com
    License: https://templatemag.com/license/
  ======================================================= -->
</head>

<html>
<head>
<meta charset="UTF-8">
<title>로그인화면 </title>
<%@ include file = "../include/header.jsp" %>
</head>
<body>

	

      

	<h2>로그인페이지</h2>
	<span style="color:red;">${errMsg}</span>    <!-- security-context에 있는 login-processing-url 하고 action하고 일치시키고 userid,password 도 일치시켜야됨 security-context로냄 -->
	
	<form class="form-login" action="/user/login_check.jsp" method="post">
       	<table>
       	
       		<tr>
       			<td>아이디</td>
       			<td><input name="userid"> </td>
       		</tr>
       	    <tr>
       			<td>비밀번호</td>
       			<td>
       			<input  type="password" name="password"> 
       			<input  name="_spring_security_remember_me" type="checkbox">   <!-- security-context에 있는 remember-me랑 연동됨 --> 
       			</td>
       			
       		</tr>
       	
       	
       		<tr>
       			<td colspan="2" align="center">
       			<input type="submit" value="로그인">
       			<input type="button" value="회원가입 " onclick="join()" > 
       		
       		</tr>
       	
       	</table>
      </form> 

</body>
</html>

<script type="text/javascript">
          
          
   function join(){
	   alert("회원가입");
	   location.href="/user/login_check";
	   
	   
   } 
    
    

</script>
    
    
    
    
    

 