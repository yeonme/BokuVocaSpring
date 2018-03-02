<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="card-content">
	<h3 class="wyomi">${word.yomi}</h3>
	<h4 class="wtitle">${word.word}</h4>
	<h4 class="wstar">${star}</h4>
	<p class="wjlpt">${jlpt}</p>
	<h5 class="wsum">${word.summary }</h5>
	<p class="flow-text">${word.meaning}</p>
</div>
<div class="card-action">
<c:choose>
<c:when test="${isWorded}">
	<a href="javascript:delWord(${word.num })" class="blue-text">단어장에서 삭제</a>
</c:when><c:otherwise>
	<a href="javascript:addWord(${word.num })" class="blue-text">단어장에 등록</a>
</c:otherwise>
</c:choose>
<c:if test="${param.nojump != '1'}">
	<a href="javascript:refreshWord();" class="blue-text">아무 단어</a>
</c:if>
</div>
<script>
var thisnum = ${word.num};
</script>