<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<button type="button" class="administratorBtn">${ sessionScope.user.u_name }님 레벨 :${ sessionScope.user.u_level } 관리자 pk:${ sessionScope.user.u_idx }</button>
<br><button class="nomalBtn" username="${ sessionScope.user.u_name }"userlevel="${ sessionScope.user.u_level }" useridx="${ sessionScope.user.u_idx }">관리자모드</button>