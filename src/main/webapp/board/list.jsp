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
	table .td_contents {
		test-align:left;
		width:800px;
	    float:left;
  		overflow:hidden; text-overflow:ellipsis; white-space:nowrap;
	}
	.fl_search{
		height: 40px;
		width: 400px;
		border: 1px solid #1b5ac2;
		bachground: #ffffff;
	}
	.fr {
		height: 40px;
		border: 1px solid #1b5ac2;
		background: #ffffff;
	}
	.i_search {
		font-size: 16px;
		width: 325px;
		padding: 10px;
		border: 0px;
		outline:none;
		float:left;
	}
	.s_button, .written{
		width:50px;
		height:100%;
		border:0px;
		background:#1b5ac2;
		outline: none;
		float: right;
		color: #ffffff;
	}
	
	.fr {
		float:right;
		
	}
	.btm {
		display:flex;
		justify-content: space-between;
		clear:both;
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
				<td><a href="/lcomputerstudy/board-view-content.do?b_idx=${board.b_idx}&b_group=${board.b_group}&b_order=${board.b_order}&b_depth=${board.b_depth}">${board.title }</a></td>
				
				<td class= "td_contents">${board.content }</td>
				<td>${board.writer} </td>
				<td>${board.date }</td>
				<td>${board.hits }</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="5" style="border:none; padding:10px 0px;" >
		<div class="btm">
			<div class="fl_search">
					<input type="text" class="i_search" placeholder="검색어 입력">	<button class="s_button">검색</button>
			</div>
			<div class= "fr">
					<input type="button" class="written" onclick="location='/lcomputerstudy/board-registration.do'" value="글쓰기"  name="write">
			</div>
		</div>
		</tr>
	</table>
	
	
	
</body>
</html>