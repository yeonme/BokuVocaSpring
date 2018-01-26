<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<title>ボクのボカ - 로그인</title>
<%@ include file="include/header.jsp"%>
<div class="section no-pad-bot" id="index-banner">
	<form method="post">
		<div class="container">
			<br> <br>
			<h3 class="header center">보쿠노보카</h3>
			<div class="row center">
				<h5 class="header col s12 light">나와 모두의 일본어 단어장</h5>
			</div>
			<div class="row center">
				<div class="input-field col s12">
					<i class="material-icons prefix">account_circle</i> <input
						type="text" id="username" name="username"> <label
						for="icon_prefix">사용자 이름</label>
				</div>
			</div>
			<div class="row center">
				<div class="input-field col s12">
					<i class="material-icons prefix">enhanced_encryption</i> <input
						type="password" id="password" name="password"> <label for="password">암호</label>
				</div>
			</div>
			<div class="row center">
				<button class="btn-large waves-effect waves-light blue"
					type="submit" name="action">
					로그인 <i class="material-icons right">done</i>
				</button>
				&nbsp; <a href="join" id="join-button"
					class="btn-large waves-effect waves-light orange"
					style="font-weight: bold;">매우 빠른 회원 가입 <i
					class="material-icons right">face</i></a>
			</div>
			<br> <br>

		</div>
	</form>
</div>


<div class="container">
	<div class="section">

		<div class="row s12 m6 center">
			<p class="flow-text">
				Created by Yeon 2017 <br />Database from 민중서림
			</p>
		</div>
	</div>
	<br> <br>
</div>
<%@ include file="include/footer.jsp"%>