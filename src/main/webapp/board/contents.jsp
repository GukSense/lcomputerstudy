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
	</style>
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
		<div></div>
		<ul id="commentList">
			<c:forEach items="${list}" var="comment">
			<li id="comment_a">
				<div>
					<a>id</a>
					<span>~전</span>
				</div>
				<div>${comment.content }</div>
				<div>
					<span></span>
					<button type="button" class="btnUpdateForm">수정</button>
					<!--<a href="/lcomputerstudy/comment-delete.do?comment_num=${comment.comment_num }">삭제</a>-->
					<button type="button" class="btnDelete" cnodelete="${comment.comment_num}" bnodelete="${comment.b_idx }">삭제</button>
					<a>답글</a>
					<!--  <button type="button" class="btnReply">답글</button>-->
					<br>-----------------
				</div>
			</li>
			<li style="display: none;">
				<div>
					<textarea rows="3" cols="80" style="resize:none"></textarea>
					<button type="button" class="btnUpdate" cno="${comment.comment_num}" bno="${comment.b_idx }">수정</button>
					<button type="button" class="btnCancel">취소</button>
				</div>
			</li>
			</c:forEach>
		</ul>
	</div>
	
	
	<div>
		<label><strong>댓글 쓰기</strong></label>
		<form method="post" action="/lcomputerstudy/comment-regi.do" onsubmit="return confirm('등록하시겠습니까?');">
			
			<div class="simple_wrt">
				<div class="text">
					<!--  <a>댓글을 작성해주시려면 로그인 해주세요. 로그인 하시겠습니까?</a> -->
					<input type="hidden" name ="b_idx" value="${board.b_idx }">
					<!-- hidden -->
					<textarea rows="4" cols="50" name="content" style="background: rgb(255,255,255); overflow: hidden; width:690px; height:63px; resize:none;"></textarea>
				</div>
				<input type="submit" value="등록">
			</div>
		</form>
	</div>
	
<script>
$(document).on('click', '.btnUpdateForm', function () {	//수정 폼 버튼 
	console.log('클릭');
	$(this).parent().parent().next().css('display', '');
});
$(document).on('click', '.btnCancel', function() {	// 수정 폼 버튼 닫기
	console.log('클릭');
	$(this).parent().parent().css('display','none');
});

$(document).on('click', '.btnUpdate', function () {	// 수정 내용, 넘버   to Controller
	let contents = $(this).prev().val();
	let cno = $(this).attr('cno');
	let bno = $(this).attr('bno');
	$.ajax({
		  method: "POST",
		  url: "comment-edit-update.do",
		  data: { comment: contents, comment_num: cno, b_idx: bno}
		})
	  .done(function( data ) {
	    alert( "Data Saved: " + data );  
	    $('#commentList').html(data);
	  });
});

$(document).on('click','.btnDelete', function() {	// 삭제 클릭 -> controller info pass 
	console.log('click');
	let cno = $(this).attr('cnodelete');
	let bno = $(this).attr('bnodelete');
	$.ajax({
		method:"POST",
		url: "comment-delete.do",
		data: {comment_num: cno,
			   b_idx: bno	   
		}
	})
	.done(function(data){	// 완료 후 html 수정된 html data 받아오기
		alert("삭제완료", + data);
		$('#commentList').html(data);
	});			
});

</script>
</body>
</html>