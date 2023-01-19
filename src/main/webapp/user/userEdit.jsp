<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		<p> 이름 : <input type="text" maxlength="4" size="4" name="edit_name" value=""></p>
		<p> 연락처: <input type="text" maxlength="4" size="4" name="edit_tel1" value="">-
				  <input type="text" maxlength="4" size="4" name="edit_tel2">-
				  <input type="text" maxlength="4" size="4" name="edit_tel3">	
		</p>
		<p> 나이: <input type="text" name="age"></p>
		<p> <input type="submit"value="수정완료"></p>
	</form>
</body>
</html>