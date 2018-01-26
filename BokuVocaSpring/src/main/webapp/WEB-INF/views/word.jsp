<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="card-content">
	<h3 class="wtitle">${word.word}</h3>
	<h4 class="wyomi">${word.yomi}</h4>
	<h4 class="wstar">${star}</h4>
	<p class="wjlpt">${jlpt}</p>
	<h5 class="wsum">${word.summary }</h5>
	<p class="flow-text">${word.meaning}</p>
</div>
<div class="card-action">
	<a href="#" class="blue-text">단어장 등록</a>
</div>