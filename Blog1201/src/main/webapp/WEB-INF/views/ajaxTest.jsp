<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/resources/lib/jquery/jquery.min.js"></script>

<style type="text/css">

	#modifyDiv{
	width: 500px;
	height: 100px;
	background-color: gray;
	position: absolute;
	top: 20%;
	left: 30%;
	padding: 20px;
	z-index:100;
}


</style>

</head>
<body>


<h3>Reply  REST+Ajax Test</h3>


<div id="modifyDiv" style="display: none">
		
		<span class="title-dialog"></span>번 댓글

	<div>
	        수정내용 <br>
		<input type="text" id="reContent" size="65">
	</div>
	
	<div align="center">
		<button type="button" id="reModifyBtn">수정  </button>
		<button type="button" id="reDelBtn">삭제  </button>
		<button type="button" id="closeBtn">닫기  </button>
	</div>
	
</div>


<div>

	<div>
		작성자 : <input type="text" name="replyer" id="writer">
	</div>
	
	
	<div>
		&nbsp;&nbsp;&nbsp;댓글 : <input type="text" maxlength="50" size="50" name="replycontent" id="addReContent">
	</div>
	
	<br/>
	<button id="submitBtn" >댓글 작성</button>
	
</div>

<h4>댓글 리스트</h4>


<ul id="reply">

</ul>

<ul class="pgeNumList">


</ul>


<script type="text/javascript">

		var bid_tb =246;
		getPgeNum(1);
		
		

		function getPgeNum(page){
			$.getJSON("/replies/"+bid_tb+"/"+page, function(data){
				
			console.log(data.reList.length);
				
			
			var str = "";
			
			$(data.reList).each(function(){
				
				
				str += "<li data-rebid= '"+this.rebid+"' class='reList'>"
				 	+ this.rebid + " | " +this.replycontent
				 	+"<button>수정</button>"
					+"</li>";
				
				});
			
					showPageNum(data.pagingMaker);		
			
					$("#reply").html(str);
			});
		}//getPgeNum
		
		
		
		
		//댓글 페이지 아래 항목 
		function showPageNum(pagingMaker){
			
			var str = "";
			if(pagingMaker.prev){
				str += "<li><a href='"+(pagingMaker.startPage-1)+"'>◀</a></li>";
				
			}
			
			for(var i = pagingMaker.startPage, end = pagingMaker.endPage; i<end; i++ ){
				str += "<a href='"+i+"'><button type='button'>"+i+"</button> </a>";
			}
			
			
			if(pagingMaker.next){
				str += "<li><a href='"+(pagingMaker.endPage+1)+"'>▶</a></li>";
				
			}
		
			$(".pgeNumList").html(str);
			
			
		}//showPageNum
		
		
		
		var rePage =1;
		
		//댓글  페이지번호 클릭시 이벤트 발생                  a태그 아래 버튼 이벤트가 발생하면
		$(".pgeNumList").on("click","a button", function(e){
			
		e.preventDefault();  //화면 전환이 일어나지 않도록 하는 함수
		
		              //this 는 버튼임
				     //버튼 부모 a태그 
			repage = $(this).parent().attr("href");
			
		
			getPageNum(repage);
			
		});
		
		
		
		
		
		
		
		
		
		
		
		function reListAll(){
			
			
			$.getJSON("/replies/selectAll/"+bid_tb,function(data){
				
		/* 		console.log(data.length);
				console.log("안녕"); */
				 
				var str ="";
				
				$(data).each(function(){
					
					
					str += "<li data-rebid= '"+this.rebid+"' class='reList'>"
					 	+ this.rebid + " | " +this.replycontent
					 	+"<button>수정</button>"
						+"</li>";
					
				});
				
				
				$("#reply").html(str);
				
				
			});

		} // :ListAll
		
		
		
		
		
		$("#reply").on("click", ".reList button", function(){
			
			
			
			
			var li = $(this).parent(); /* 버튼의 부모 리스트 선택 */
			var rebid = li.attr("data-rebid");
			var replycontent = li.text();
			
			//alert( "댓글번호:"+rebid +": 수정할내용: "+ replycontent);
			$(".title-dialog").html(rebid);
			$("#reContent").val(replycontent);
			$("#modifyDiv").show("slow");
		});
		
		
		
		
		
		
		
    //쓰기
	$("#submitBtn").on("click",function(){
		
	
	
		
		var reWriter = $("#writer").val();
		var reContent = $("#addReContent").val();
		

		
		$.ajax({
			type: 'post',
			url: '/replies',
			headers: {
					"Content-Type" : "application/json",
					"X-HTTP-Method-Override" : "POST"
				
				
			},
			
			dataType : 'text', 
			data : JSON.stringify({
				
				bid_tb : bid_tb,
				replyer : reWriter,
				replycontent : reContent
				
			}),
			
			success : function(result) {
				if(result == "Success"){
					//alert("댓글등록 성공");
					reListAll();
				}
			}

		});
		
	});
	
	

	//삭제
	$("#reDelBtn").on("click",function(){
	
	
		var rebid = $(".title-dialog").html();
		var reContent = $("#reContent").val();
		
		console.log(rebid);
		
		$.ajax({
			
			type:'delete',
			url:'/replies/'+rebid,
			headers: {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "DELETE"
					},
			dataType: 'text',
 			
			success : function(result) {
				 console.log("result:", result);
				if(result == "Success"){
				    alert("삭제성공");
				    $("#modifyDiv").hide("slow");
				    reListAll();
				}
			} 
		
			
		});//ajax
			
	});
	
	
	//수정
	$("#reModifyBtn").on("click",function(){
	
	
		var rebid = $(".title-dialog").html();
		var reContent = $("#reContent").val();
		
		console.log(rebid);
		
		$.ajax({
			
			type:'put',
			url:'/replies/'+rebid,
			headers: {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "PUT"
				
					},
			data : JSON.stringify({
						
						replycontent : reContent
						
					}),
			dataType: 'text',
 			
			success : function(result) {
				 console.log("result:", result);
				if(result == "Success"){
				    alert("수정성공");
				    $("#modifyDiv").hide("slow");
				    reListAll();
				}
			} 
		
			
		});//ajax
	

	});

	
	





</script>




</body>
</html>