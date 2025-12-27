<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>all</title>
</head>
<body>
	<h1>모든 사람(member, admin, nonmember)</h1>
	<div>
		<!-- 주의 : 시큐리티 태그에는 마지막에 세미콜론이 들어가면 안된다.  -->
		<sec:authorize access="isAnonymous()">
			회원가입
		</sec:authorize>
	
		<sec:authorize access="isAnonymous()">
			로그인
		</sec:authorize>
	
		<sec:authorize access="isAuthenticated()">
			로그아웃
		</sec:authorize>
	</div>
</body>
</html>