<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>     
<c:forEach items="${list}" var="comment">
			<li>
				<div>
					<a>id</a>
					<span>~전</span>
				</div>
				<div>${comment.content }</div>
				<div>
					<span></span>
					<button type="button" class="btnUpdateForm">수정</button>
					<a href="/lcomputerstudy/comment-delete.do?comment_num=${comment.comment_num }">삭제</a>
					<a>답글</a>
					<br>-----------------
				</div>
			</li>
			<li style="display: none;">
				<div>
					<textarea rows="3" cols="80" style="resize:none"></textarea>
					<button type="button" class="btnUpdate" cno="${comment.comment_num}">등록</button>
					<button type="button">취소</button>
				</div>
			</li>
			</c:forEach>