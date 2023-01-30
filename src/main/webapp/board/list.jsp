<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board-list</title>
<style>
	table {
		border-collapse:collapse;
		margin:40px auto
		
	}
	
	table tr th, table tr td {
		border:1px solid #818181;
		width:200px;
		text-align:center;
	}
	.written {
		float:right;
	}
	table .td_contents {
		test-align:left;
		width:800px;
	    float:left;
  		overflow:hidden; text-overflow:ellipsis; white-space:nowrap;
	}
	
	
</style>
</head>
<body>
	<table>
		<tr>
			<th>제목</th>
			<th>내용</th>
			<th>작성자</th>
			<th>작성일시</th>
			<th>조회수</th>
		</tr>
		<c:forEach items="${list}" var="board">
			<tr>
				<td><a href="/lcomputerstudy/board-view-content.do?b_idx=${board.b_idx}&b_group=${board.b_group}">${board.title }</a></td>
				
				<td class= "td_contents">${board.content }</td>
				<td>${board.writer} </td>
				<td>${board.date }</td>
				<td>${board.hits }</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="5" style="border:none; padding:10px 0px;" >
				<input type="button" class="written" onclick="location='/lcomputerstudy/board-registration.do'" value="글쓰기"  name="write">
    		</td>
		</tr>
	</table>
	
</body>
</html>