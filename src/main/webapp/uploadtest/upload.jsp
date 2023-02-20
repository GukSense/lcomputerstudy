<%@page import="java.util.Enumeration"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		/*
		String id = request.getParameter("id");
		out.print(id); // request.getParameter() 사용불가
		*/
		
		String id = "";
		String subject = "";
		String fileName1 = "";
		String fileName2 = "";
		String orgfileName1 = "";
		String orgfileName2 = "";
		
		String uploadPath = request.getRealPath("upload");	//  upload는 폴더명 /폴더의 경로를 구해옴	//out.print(uploadPath);
		
		try {
			MultipartRequest multi = new MultipartRequest(	//MultipartRequest 인스턴스 생성 (cos.jar의 라이브러리)
				request,
				uploadPath,	//파일을 저장할 디렉토리 지정
				10 * 1024,	//첨부파일 최대 용량 설정(bite) / 10KB / 용량 초과 시 예외발생
				"utf-8",
				new DefaultFileRenamePolicy()
			);
			
			id = multi.getParameter("id");
			subject = multi.getParameter("subject");
			/*
				form의 <input type="file"> name값을 모를 경우 name을 구할때 사용
				Enumeration files = multi.getFileName(); // form의 type="file" name을 구함
				String file1 = (String)files.nextElement(); // 첫번째 type="file" 의 name 저장
				String file2 =(String)files.nextElement(); // 두번째 type="file"의 name 저장
				
			*/
		} catch(Exception e) {
			e.getStackTrace();
		} 
	%>
	
</body>
</html>