<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>     
			<c:forEach items="${list}" var="comment">
			<li class="comment_a">
				<div>
					<a>id</a>
					<span>~전</span>
				</div>
				<div class="cont">${comment.content }</div>
				<div>
					<span></span>
					<button type="button" class="btnUpdateForm">수정</button>
					<!--<a href="/lcomputerstudy/comment-delete.do?comment_num=${comment.comment_num }">삭제</a>-->
					<button type="button" class="btnDelete" cnodelete="${comment.comment_num}" bnodelete="${comment.b_idx }">삭제</button>
					<!--<a>답글</a>-->
					<button type="button" class="btnReply">답글</button>
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
			</c:forEach>