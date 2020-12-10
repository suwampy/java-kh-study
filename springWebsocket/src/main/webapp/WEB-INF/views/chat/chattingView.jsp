<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- jquery 추가 -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script> 

<!-- cdn 방식으로 sockjs 불러오기 -->
<script src="http://cdn.jsdelivr.net/sockjs/1/sockjs.min.js"></script>

<!-- 부트스트랩 추가 -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<title>실시간 채팅</title>
<script>
	// SockJS 객체 생성 보낼 url 경로를 매개변수로 등록
	var sock = new SockJS("<c:url value='/echo'/>");
	sock.onmessage = onMessage;
	// new SockJS().onmessage = function(evt)
	// 웹소켓 처리 객체에서 전달 받은 TextMessage를 처리하는 함수
	// 전송 된 메세지 내용은 매개변수 객체에 담겨서 전달
	sock.onclose = onClose;
	// new SockJS().onclose = function(evt)
	// 소켓 종료시 동작하는 함수
	var today = null;
	$(function(){
		$("#sendBtn").click(function(){
			console.log("send message...");
			sendMessage(); // 채팅창에 작성한 메세지 전송
			$("#message").val(''); // 전송 후 작성 창 초기화
		});
		
		$("#exitBtn").click(function(){
			console.log("exit message...");
			sock.onclose();
		});
		
	});
	function sendMessage(){
		sock.send($("#message").val()); // 매핑 된 핸들러 객체의 handleTextMessage 메소드가 실행
		// new SockJS().send() 메세지를 페이지에서 웹소켓 처리 객체로 전송하는 함수
	}

	function onMessage(evt){
		var data = evt.data; // new text 객체로 보내준 값을 받아 옴
		var host = null; // 메세지를 보낸 사용자 ip 저장
		var strArray = data.split("|"); // 데이터 파싱 처리
		var userName = null; // 대화명 저장
		
		// 전송 된 데이터 출력해보기
		for(var i = 0; i < strArray.length; i++){
			console.log('str['+i+'] : ' + strArray[i]);
		}
		
		today = new Date();
		printDate = today.getFullYear() + "/" + (today.getMonth()+1) + "/"
		+ today.getDate() + " " + today.getHours() + ":" + today.getMinutes()
		+ ":" + today.getSeconds();
		
		if(strArray.length > 1){
			// 채팅 창에 글을 입력한 경우
			sessionId = strArray[0];
			message = strArray[1];
			host = strArray[2].substr(1,strArray[2].indexOf(":")-1);
			userName = strArray[3];
			
			var ck_host = '${host}'; // 컨트롤러에서 확인한 ip
			
			console.log(sessionId);
			console.log(message);
			console.log('host : ' + host);
			console.log('ck_host : ' + ck_host);
			
			if(host == ck_host || (host == 0 && ck_host.includes('0:0:'))){ 
				// 2개 아이피가 일치 ==> 내가 보낸 메세지로 출력
				// localhost로 접속한 경우 host가 0 ck_host가 0:0:0:0:0:0:0:1
				var printHTML = "<div class='well' style='margin-left:30%'>";
				printHTML += "<div class='alert alert-info'>";
				printHTML += "<sub>"+printDate+"</sub><br>";
				printHTML += "<strong>["+userName+"] : " + message + "</strong>";
				printHTML += "</div>";
				printHTML += "</div>";
				$("#chatdata").append(printHTML);
			}else{
				// 그 외의 경우는 상대 메세지 출력
				var printHTML = "<div class='well' style='margin-left:-5%;margin-right:30%;'>";
				printHTML += "<div class='alert alert-warning'>";
				printHTML += "<sub>"+printDate+"</sub><br>";
				printHTML += "<strong>["+userName+"] : " + message + "</strong>";
				printHTML += "</div>";
				printHTML += "</div>";
				$("#chatdata").append(printHTML);
			}
			
			
			
		}else{
			// 나가기를 클릭한 경우
			message = strArray[0];
			var printHTML = "<div class='well' style='margin-left:30%'>";
			printHTML += "<div class='alert alert-danger'>";
			printHTML += "<sub>" + printDate + "</sub><br>";
			printHTML += "<strong>[서버관리자] : " + message + "</strong>";
			printHTML += "</div>";
			printHTML += "</div>";
			$("#chatdata").append(printHTML);
		}
	}
	
	function onClose(evt){
		location.href='${pageContext.request.contextPath}';
	}
	
</script>
<style>
	div{ padding:5%; }
</style>
</head>
<body>
	<h2>채팅 만들기</h2>
	<div class='form-group'>
		<h3><label id="sessionuserid">${ userName }님이 입장하셨습니다.</label></h3><br>
		<!-- 메세지 작성 부분 -->
		<textarea rows="2" cols="100" name="message" id="message"></textarea>
		<button class="btn btn-primary" id="sendBtn">전송</button>
		<button class="btn btn-primary" id="exitBtn">나가기</button>
		<!-- 대화 내용이 출력되는 부분 -->
		<div class="panel panel-default">
			<div id="chatdata" class="panel-body"></div>
		</div>
	</div>
</body>
</html>