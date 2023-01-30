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
	<a href="/lcomputerstudy/board-reply.do?b_group=${board.b_group}">답글</a>
</body>
</html>