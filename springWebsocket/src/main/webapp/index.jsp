<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/chatting.do" method="post">
		<label>이름 입력 : <input type="text" name="userName" id="userName"></label>
		<input type="submit" value="입장"/>
	</form>

</body>
</html>






