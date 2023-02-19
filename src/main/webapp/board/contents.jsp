<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<title>글 내용</title>
	<style>
		li {
			list-style:none;
		}
		.administratorBtn, .retrunBtn, .manageBtn, .loginBtn, .logoutBtn{
		border: none;
		background:#ffffff;
		outline: none;
		float: right;
		color: #1b5ac2;
	}
	</style>
</head>
<body>
	<c:choose>	
		<c:when test="${empty sessionScope.user.u_idx }">
			<span><button type="button" class="loginBtn" onclick="location.href='/lcomputerstudy/user-login.do'">로그인</button></span>
		</c:when>
		<c:otherwise>
			<span><button type="button" class="logoutBtn" onclick="location.href='/lcomputerstudy/logout.do'">로그아웃</button></span>
			<button type="button" class="administratorBtn">${ sessionScope.user.u_name }님 레벨 :${ sessionScope.user.u_level } 관리자 pk:${ sessionScope.user.u_idx }</button>
		</c:otherwise>
	</c:choose>
	
	
	<c:if test="${ sessionScope.user.u_level >= 9}">
		<br><button type="button" class="manageBtn" onclick="location.href='/lcomputerstudy/user-list.do'">회원관리</button>
		<br><button type="button" class="retrunBtn" username="${ sessionScope.user.u_name }"userlevel="${ sessionScope.user.u_level }" useridx="${ sessionScope.user.u_idx }">돌아가기</button>
	</c:if>
	<table>
		<tr>
			<th><h3>제목: ${board.title }</h3></th>
		</tr>
		<tr>
			<td>날짜: ${board.date }	조회수: ${board.hits }	</td>
		</tr>
		<tr>
			<td><h1>${board.content}</h1> </td>
		</tr>
	</table>

	<a href="/lcomputerstudy/board-list.do">돌아가기</a>
	<c:if test="${sessionScope.user.u_level >= 9}">
	<a href="/lcomputerstudy/board-edit.do?b_idx=${board.b_idx }">수정</a>
		<a href="/lcomputerstudy/board-delete.do?b_idx=${board.b_idx}">삭제</a>
	</c:if>
	
	<a href="/lcomputerstudy/board-reply.do?b_group=${board.b_group}&b_order=${board.b_order}&b_depth=${board.b_depth}">답글</a>
	<div>
		<div></div>
		<ul id="commentList">
			<c:forEach items="${list}" var="comment">
			<li class="comment_a">
				<div>
					<a>id</a>
					<span>~전</span>
				</div>
				<div class="cont">${comment.content }--group:${comment.groupNum }-- order:${comment.order }</div>
				<div>
					<span></span>
					<c:if test="${ sessionScope.user.u_level >= 9}">
						<button type="button" class="btnUpdateForm">수정</button>
						<button type="button" class="btnDelete" cnodelete="${comment.comment_num}" bnodelete="${comment.b_idx }">삭제</button>
					</c:if>
					<button type="button" class="btnReplyForm">답글</button>
					<br>-----------------
				</div>
			</li>
			<li style="display: none;">
				<div>
					<textarea class="editBox" rows="3" cols="80"></textarea>
					<button type="button" class="btnUpdate" cno="${comment.comment_num}" bno="${comment.b_idx }">수정</button>
					<button type="button" class="btnCancel">취소</button>
				</div>
			</li>
			<li style="display: none; list-style-position:inside;">
				<div>
					<textarea class="replyBox" rows="3" cols="80"></textarea>
					<button type="button" class="btnReply" bidx="${comment.b_idx }" order="${comment.order }" group="${comment.groupNum }" depth="${comment.depth }">등록</button>
					<button type="button" class="btnCancelR">취소</button>
				</div>
			</li>
			</c:forEach>
		</ul>
	</div>
	
	
	<div>
		<label><strong>댓글 쓰기</strong></label>
		<form id="submitUpdate" method="post" action="/lcomputerstudy/comment-regi.do" onsubmit="return confirm('등록하시겠습니까?');">
			
			<div class="simple_wrt">
				<div class="text">
					<!--  <a>댓글을 작성해주시려면 로그인 해주세요. 로그인 하시겠습니까?</a> -->					
					<input type="hidden" name ="b_idx" value="${board.b_idx }">
					<textarea rows="4" cols="50" id="content" name="content" style="background: rgb(255,255,255); overflow: hidden; width:690px; height:63px; resize:none;"></textarea>
				</div>
				<input type="button" value="등록" id="submit">
			</div>
		</form>
	</div>
	
<script src="/lcomputerstudy/query/comment.js"></script>
</body>
</html>