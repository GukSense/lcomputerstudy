<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>답글작성</title>
</head>
<body>
	<form method="post" action="/lcomputerstudy/board-reply-process.do" onsubmit="return confirm('등록하시겠습니까?');" name="registration">
				<input type="hidden" name="b_group" value="${board.b_group }">
				<input type="hidden" name="b_order" value="${board.b_order }">
    			<table style= "text-align: center; boarder: 1px solid #dddddd">
	    	    	<thead>
		    	    	<tr>
		    	    		<th colspan= "2" style= "background-color: #eeeeee; text-align: center;">게시판 글쓰기 양식</th>
		    	    	</tr>
	    	    	</thead>
			    	<tbody>
			    		<tr>
			    			<td><input type="text"  placeholder="글 제목"  name="reply-title" maxlength="50" ></td>
			    		</tr>
			    		<tr>
			    			<td><textarea  placeholder="글 내용"  name="reply-content" maxlength="2048" style= "height:350px" ></textarea></td>
			    		</tr>
			    	</tbody>
    	    	</table>
    	    	<input type="submit"  value="답글작성" >
    </form>
</body>
</html>