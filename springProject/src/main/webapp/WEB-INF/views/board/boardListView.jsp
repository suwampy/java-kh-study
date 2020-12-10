<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.wrap {
		width:50%;
		margin:auto;
	}
	#boardTable {
		width:100%;
		border:solid 1px white;
	}
	#boardTable th, #boardTable td {
		border:solid 1px white;
		text-align:center;
	}
</style>
</head>
<body>
	<jsp:include page="../common/menubar.jsp"/>
	
	<h1 align="center">게시글 목록</h1>

	<h3 align="center">
	총 게시글 개수 : ${ pi.listCount }
	<c:if test="${ !empty loginUser }">
		&nbsp;&nbsp;&nbsp;
		<button onclick="location.href='binsertView.do'">글쓰기</button>
	</c:if>
	</h3>
	
	<div class="wrap">
		<table id="boardTable">
			<tr>
				<th>번호</th>
				<th width="300">제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
				<th>첨부파일</th>
			</tr>
			<c:forEach var="b" items="${ list }">
			<tr>
				<td>${ b.bId }</td>
				<td>
					<c:if test="${ !empty loginUser }">
						<c:url var="bdetail" value="bdetail.do">
							<c:param name="bId" value="${ b.bId }"/>
							<c:param name="page" value="${ pi.currentPage }"/>
						</c:url>
						<a href="${ bdetail }">${ b.bTitle }</a>
					</c:if>
					<c:if test="${ empty loginUser }">
					${ b.bTitle }
					</c:if>
				</td>
				<td>${ b.bWriter }</td>
				<td>${ b.bCreateDate }</td>
				<td>${ b.bCount }</td>
				<td>
					<c:if test="${ !empty b.originalFileName }">
					◎
					</c:if>
				</td>
			</tr>
			</c:forEach>
			
			<!-- 페이징 처리 -->
			<tr align="center" height="20">
				<td colspan="6">
					<!-- [이전] -->
					<c:if test="${ pi.currentPage <= 1 }">
						[이전] &nbsp;
					</c:if>
					<c:if test="${ pi.currentPage > 1 }">
						<c:url var="before" value="blist.do">
							<c:param name="page" value="${ pi.currentPage - 1 }"/>
						</c:url>
						<a href="${ before }">[이전]</a> &nbsp;
					</c:if>
					
					<!-- 페이지 -->
					<c:forEach var="p" begin="${ pi.startPage }" end="${ pi.endPage }">
						<c:if test="${ p eq pi.currentPage }">
							<font color="red" size="4"><b>[${ p }]</b></font>
						</c:if>
						
						<c:if test="${ p ne pi.currentPage }">
							<c:url var="pagination" value="blist.do">
								<c:param name="page" value="${ p }"/>
							</c:url>
							<a href="${ pagination }">${ p }</a> &nbsp;
						</c:if>
					</c:forEach>
					
					<!-- [다음] -->
					<c:if test="${ pi.currentPage >= pi.maxPage }">
						[다음]
					</c:if>
					<c:if test="${ pi.currentPage < pi.maxPage }">
						<c:url var="after" value="blist.do">
							<c:param name="page" value="${ pi.currentPage + 1 }"/>
						</c:url> 
						<a href="${ after }">[다음]</a>
					</c:if>
				</td>
			</tr>
			
			
			
			
			
			
			
			
			
		</table>
	</div>
	
	
	
	
	
	
	
	
	
	
	






</body>
</html>