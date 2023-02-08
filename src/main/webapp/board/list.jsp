<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board-list</title>
<style>
	a {
		text-decoration: none; /* 링크의 밑줄 제거 */
  		color: #222222; /* 링크의 색상 제거 */
  		font-weight:13px;
	}
	table {
		border-collapse:collapse;
		margin:40px auto
	}
	
	table tr th, table tr td {
		border:1px solid #818181;
		width:200px;
		text-align:center;
	}
	.td_title {
		width:800px;
	    text-align:left;
	    text-indent:10px;
  		overflow:hidden; text-overflow:ellipsis; white-space:nowrap;
	}
	.fl_search{
		height: 40px;
		width: 460px;
		bachground: #ffffff;
		display:flex;
		
	}
	.fr {
		height: 40px;
		border: 1px solid #1b5ac2;
		background: #ffffff;
	}
	.select_t {
		color:#ffffff;
		background:#1b5ac2;
		border: 1px solid #1b5ac2;
		outline:none;
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
	.select {
		height: 40px;
		width: 462px;
		border: 1px solid #1b5ac2;
		background:#1b5ac2;
		display:flex;
	}
	.fr {
		float:right;
		
	}
	.btm {
		display:flex;
		justify-content: space-between;
		clear:both;
	}
	ul {	<!-- 페이지네이션 css-->
		width:450px;
		height:50px;
		margin:10px auto;
		display: table;
  		margin-left: auto;
 		margin-right: auto;
					
	}
	li {	<!-- 페이지네이션 css-->
		list-style:none;
		width:50px;
		line-height:50px;
		border:1px solid #ededed;
		float:left;
		margin:0 5px;
		border-radius:5px;
		display:inline;
		background:#1b5ac2;
		color:#ffffff;
		text-align:center;
	}
	
	.tab_menu{
		display:flex;
		justify-content:row;
		height:45px;
		background:#1b5ac2;
	}
	.tab_menu li {
		padding-right:15px;
		padding-left:15px;
		border:none;
		line-height:45px;	
	}
	
</style>
</head>
<body>

	<div class="logo">
		<a href="/lcomputerstudy/board-list.do"><img src="C:\Users\강국\Documents\workspace-spring-tool-suite-4-4.7.0.RELEASE\workspace\lcomputerstudy\src\main\webapp\img\logo_title.jpg" alt=""></a>
		<a href="/lcomputerstudy/board-list.do"><img src="C:\Users\강국\Documents\workspace-spring-tool-suite-4-4.7.0.RELEASE\workspace\lcomputerstudy\src\main\webapp\img\logo.jpg" alt=""></a>
	</div>
	<table>
	<tr>
		<td colspan="5" style="border:none; padding:10px 0px;">
			<ul class ="tab_menu">
				<li><a href="/lcomputerstudy/board-list.do?category=lcomputer" style="color:#ffffff;">lcomputer</a></li>
				<li><a href="/lcomputerstudy/board-list.do?category=개발" style="color:#ffffff;">개발</a></li>
				<li><a href="/lcomputerstudy/board-list.do?category=일상" style="color:#ffffff;">일상</a></li>
				<li><a href="/lcomputerstudy/board-list.do?category=질문" style="color:#ffffff;">질문</a></li>
			</ul>
		</td>
	</tr>
		<tr style="background:#1b5ac2; color=#ffffff;">
			<th>탭</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일시</th>
			<th>조회수</th>
		</tr>
		<c:forEach items="${list}" var="board">
			<tr>
				<td>${board.b_category}</td>				
				<td class= "td_title"><a href="/lcomputerstudy/board-view-content.do?b_idx=${board.b_idx}&b_group=${board.b_group}&b_order=${board.b_order}&b_depth=${board.b_depth}">${board.title }</a></td>
				<td>${board.writer} </td>
				<td>${board.date }</td>
				<td>${board.hits }</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="5" style="border:none; padding:10px 0px;" >
		<div class="btm">
			<div class="fl_search">
				<form method="get" action="/lcomputerstudy/board-list.do" name="search">
					<input type="hidden" name="category" value="${pagination.search.search_category }">
					<span class="select">
						<select class ="select_t" name="search_target" >
							<option value="title">제목</option>
							<option value="title_content">내용</option>
							<option value="nick_name">작성자</option>
						</select>
					
						<input type="text" class="i_search" placeholder="검색어 입력" name="search_keyword">	
						<button class="s_button">검색</button>
					</span>
					
				</form>
			</div>
			<div class= "fr">
					<input type="button" class="written" onclick="location='/lcomputerstudy/board-registration.do'" value="글쓰기"  name="write">
			</div>
		</div>
		</tr>
	</table>
<!--페이지네이션-->
	<div>
		<ul>
			<c:choose>
				<c:when test="${pagination.startPage > 5}">
					<li>
						<a href="board-list.do?page=${pagination.prevPage }">
							◀
						</a>
					</li>
				</c:when>
			</c:choose>
			<c:forEach var="i" begin="${pagination.startPage}" end="${pagination.endPage}" step="1">
				<c:choose>
					<c:when test="${ pagination.page == i}">
						
						<li style="background-color:#ededed; color:black;">
							<span>${i}</span>
						</li>
					</c:when>
					<c:when test="${pagination.page != i }">
						<li>
							<a href="board-list.do?page=${i}">${i}</a>
						</li>
					</c:when>				
				</c:choose>
			</c:forEach>
			<c:choose>
				<c:when test="${ pagination.nextPage < pagination.lastPage }">
					<li style="display:inlien;">
						<a href ="board-list.do?page=${pagination.nextPage }">▶</a>
					</li>
				</c:when>
			</c:choose>
		</ul>
	</div>
	
</body>
</html>