<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>게시글수정</title>
</head>
<body>
	<form method="post" action="/lcomputerstudy/board-edit-result.do" onsubmit="return confirm('등록하시겠습니까?');" name="registration">
				<input type="hidden" name="b_idx" value="${board.b_idx }">
    			<table style= "text-align: center; boarder: 1px solid #dddddd">
	    	    	<thead>
		    	    	<tr>
		    	    		<th colspan= "2" style= "background-color: #eeeeee; text-align: center;">게시판 글쓰기 양식</th>
		    	    	</tr>
	    	    	</thead>
			    	<tbody>
			    		<tr>
			    			<td><input type="text"  placeholder="글 제목"  name="edit-title" maxlength="50" ></td>
			    		</tr>
			    		<tr>
			    			<td><textarea  placeholder="글 내용"  name="edit-content" maxlength="2048" style= "height:350px" ></textarea></td>
			    		</tr>
			    	</tbody>
    	    	</table>
    	    	<input type="submit"  value="수정하기" >
    </form>
</body>
</html>