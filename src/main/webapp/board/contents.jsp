<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 내용</title>
</head>
<body>
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
	<a href="/lcomputerstudy/board-edit.do?b_idx=${board.b_idx }">수정</a>
	<a href="/lcomputerstudy/board-delete.do?b_idx=${board.b_idx}">삭제</a>
	<a href="/lcomputerstudy/board-reply.do?b_group=${board.b_group}&b_order=${board.b_order}&b_depth=${board.b_depth}">답글</a>
	<div>
		<label><strong>댓글 쓰기</strong></label>
		<form method="post" action="/lcomputerstudy/comment-regi.do" onsubmit="return confirm('등록하시겠습니까?');">
			
			<div class="simple_wrt">
				<div class="text">
					<!--  <a>댓글을 작성해주시려면 로그인 해주세요. 로그인 하시겠습니까?</a> -->
					<!-- hidden -->
					<!-- hidden -->
					<textarea rows="4" cols="50" style="background: rgb(255,255,255); overflow: hidden; width:690px; height:63px; resize:none;"></textarea>
				</div>
				<input type="button" value="등록">
			</div>
		</form>
	</div>
	
</body>
</html>