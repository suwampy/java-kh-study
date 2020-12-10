<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<style>
	div {
		border: 1px solid black;
		width:500px;
		min-height:200px;
	}
</style>
</head>
<body>
	<h1 align="center">Spring에서의 AJAX 사용 테스트 페이지</h1>
	
	<h3>1. 서버 쪽 컨트롤러로 값 보내기(Stream을 이용한 방식)</h3>
	<button id="test1">테스트</button>
	<br>
	<script>
		$(function(){
			$("#test1").click(function(){
				$.ajax({
					url:"test1.do",
					data:{name:"신사임당", age:47},
					type:"post",
					success:function(data){
						if(data == "success")
							alert("전송 값과 일치합니다.");
						else
							alert("전송 값과 일치하지 않습니다.");
					},
					error:function(e){
						alert("error code : " + e.status + "\n"
								+ "message : " + e.responseText);
					}
				});
			});
		});
	</script>
	
	
	<h3>2. 컨트롤러에서 리턴하는 JSON 객체 받아서 출력하기(@ResponseBody를 이용한 방식)</h3>
	<button id="test2">테스트</button>
	<div id="d2"></div>
	<br>
	<script>
		$(function(){
			$("#test2").on("click", function(){
				$.ajax({
					url:"test2.do",
					dataType:"json", 
					// text의 경우 dataType 지정 없이 리턴 타입 자동 처리 가능
					// json의 경우 기재해야 json 파싱 처리를 jquery에서 대신 수행
					// 생략 시 String 타입으로 받기 때문에 받은 후 'JSON.parse()' 파싱 처리 필요
					success:function(data){
						// data = JSON.parse(data);
						$("#d2").html("번호 : " + data.no
								+ "<br>제목 : " + data.title
								+ "<br>작성자 : " + data.writer
								+ "<br>내용 : " + data.content);
					},
					error:function(e){
						alert("error code : " + e.status + "\n"
								+ "message : " + e.responseText);
					}
				});
			});
			
		});
	
	
	</script>
	
	<h3>3. 컨트롤러에서 리턴하는 JSON 배열을 받아서 출력하기(Stream 이용한 jsonObject를 보내는 방식)</h3>
	<button id="test3">테스트</button>
	<div id="d3"></div>
	<br>
	<script>
		$(function(){
			$("#test3").on("click", function(){
				$.ajax({
					url:"test3.do",
					// dataType:"json",
					success:function(data){
						console.log(data.list);
						
						var value = '';
						
						for(var i in data.list){
							value += data.list[i].userId + ", "
								+ data.list[i].userPwd + ", "
								+ data.list[i].userName + ", "
								+ data.list[i].age + ", "
								+ data.list[i].email + ", "
								+ data.list[i].phone + "<br>";
						}
						
						$("#d3").html(value);
						
					},
					error:function(e){
						alert("error code : " + e.status + "\n"
								+ "message : " + e.responseText);
					}
				});
			});
		});
	
	</script>

	<h3>4. 컨트롤러에서 리턴하는 Map 객체를 받아서 출력하기(JsonView를 이용한 방식)</h3>
	<button id="test4">테스트</button>
	<div id="d4"></div>
	<br>
	
	<script>
		$(function(){
			$("#test4").on("click", function(){
				$.ajax({
					url:"test4.do",
					// dataType:"json",
					success:function(data){
						console.log(data);
						$("#d4").html("받은 맵 객체 안의 samp 객체 정보 확인 <br>"
								+ "이름 : " + data.samp.name
								+ ", 나이 : " + data.samp.age);
						
						
					},
					error:function(e){
						alert("error code : " + e.status + "\n"
								+ "message : " + e.responseText);
					}
				});
			});
		});
	
	</script>
	
	<h3>5. 뷰에서 JSON 객체를 컨트롤러로 보내기(@RequestBody를 이용한 방식)</h3>
	<button id="test5">테스트</button>
	<br>
	<script>
		$(function(){
			$("#test5").on("click", function(){
				// json 객체를 만들어서 서버 쪽 컨트롤러로 전송
				var obj = new Object();
				obj.name = "우별림";
				obj.age = 20;
				
				$.ajax({
					url : "test5.do",
					data : JSON.stringify(obj),
					// json 객체를 String 객체로 변환
					type:"post",
					contentType:"application/json; charset=utf-8",
					// contentType은 송신쪽에서 보낼 타입 지정
					success:function(data){
						alert("서버로 전송 성공! " + data);
					},
					error:function(e){
						alert("error code : " + e.status + "\n"
								+ "message : " + e.responseText);
					}
				});
			});
		});
	</script>
	
	<h3>6. 뷰에서 JSON 배열을 컨트롤러로 보내기(@RequestBody를 이용한 방식)</h3>
	<button id="test6">테스트</button>
	<br>
	<script>
		$(function(){
			$("#test6").on("click", function(){
				// json 객체를 만들어서 서버 쪽 컨트롤러로 전송
				var arr = [{name : "우별림", age:20},
					{name : "홍길동", age:30}];
				$.ajax({
					url : "test6.do",
					data : JSON.stringify(arr),
					// json 객체를 String 객체로 변환
					type:"post",
					contentType:"application/json; charset=utf-8",
					// contentType은 송신쪽에서 보낼 타입 지정
					success:function(data){
						alert("서버로 전송 성공! " + data);
					},
					error:function(e){
						alert("error code : " + e.status + "\n"
								+ "message : " + e.responseText);
					}
				});
			});
		});
	</script>
	
	<h3>7. 뷰에서 Json 객체를 보내고 map 객체 리턴 받기(Jackson lib를 이용한 방식)</h3>
	<button id="test7">테스트</button>
	<script>
		$(function(){
			$("#test7").on("click", function(){
				var obj = {name : "우별림", age : 20};
				
				$.ajax({
					url:"test7.do",
					type:"post",
					data:JSON.stringify(obj),
					contentType:"application/json; charset=utf-8",
					success:function(data){
						alert("서버로 전송 성공!");
						console.log(data);
					},
					error:function(e){
						alert("error code : " + e.status + "\n"
								+ "message : " + e.responeText);
					}
				});
			});
		});
	</script>
	
	<h3>8. 뷰에서 JSON 배열을 컨트롤러로 보내고 List 객체 리턴 받기(Jackson lib를 이용한 방식)</h3>
	<button id="test8">테스트</button>
	<script>
		$(function(){
			$("#test8").on("click", function(){
				var arr = [{name:"우별림", age:20},{name:"홍길동", age:30}];
				
				$.ajax({
					url:"test8.do",
					type:"post",
					data:JSON.stringify(arr),
					contentType:"application/json; charset=utf-8",
					success:function(data){
						alert("서버로 전송 성공!");
						console.log(data);
					},
					error:function(e){
						alert("error code : " + e.status + "\n"
								+ "message : " + e.responeText);
					}
				});
			});
		});
	</script>
	
	




</body>
</html>