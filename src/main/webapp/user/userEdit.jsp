<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정</title>
</head>
<body>
	<form action="user-edit-process.do" name="userEdit" method="post">
	<h2>회원 정보 수정</h2>
		<p><input type="hidden" name="edit_u_idx" value="${user.u_idx}"></p>
		<p> 아이디: 	<input type="text" name="edit_id" value="${user.u_id}"></p>
		<p> 비밀번호: <input type="password" name="edit_password" value="${user.u_pw}"></p>
		<p> 이름 : <input type="text" maxlength="4" size="4" name="edit_name" value="${user.u_name }"></p>
		<p> 연락처: <input type="text" maxlength="4" size="4" name="edit_tel1" value="">-
				  <input type="text" maxlength="4" size="4" name="edit_tel2">-
				  <input type="text" maxlength="4" size="4" name="edit_tel3">	
		</p>
		<p> 나이: <input type="text" name="age" value="${user.u_age }"></p>
		<c:choose>
			<c:when test="${User.authority  }">
				<p>	권한: <input type="checkbox" name="edit_u_auth" value="${User.authority }" checked >관리자
						<input type="checkbox" name="edit_u_auth" value="${user.getNomal() }" >일반</p>
			</c:when>
			<c:otherwise>
				<p>	권한: <input type="checkbox" name="edit_u_auth" value="${user.getAuthority() }" >관리자
						<input type="checkbox" name="edit_u_auth" value="${user.getNomal() }" checked >일반</p>
			</c:otherwise>
		</c:choose>
		<p> <input type="submit"value="수정완료"></p>
	</form>
</body>
</html>