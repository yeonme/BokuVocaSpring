<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Alert</title>
</head>
<body>
<c:choose>
<c:when test="${url != null}">
<script>
alert('${message}');
location.href='${url}';
</script>
</c:when>
<c:otherwise>
<script>
alert('${message}');
history.go(-1);
</script>
</c:otherwise>
</c:choose>
</body>
</html>