<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<c:if test="${ sessionScope.user.u_level >= 9}">
<button type="button" class="administratorBtn">${ sessionScope.user.u_name }님 레벨 :${ sessionScope.user.u_level } 관리자 pk:${ sessionScope.user.u_idx }</button>
</c:if>