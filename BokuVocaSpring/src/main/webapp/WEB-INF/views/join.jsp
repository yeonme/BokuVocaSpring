<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<title>ボクのボカ - 회원 가입</title>
<%@ include file="include/header.jsp"%>
<form method="post">
<%--
username
email
password
 --%>
	<div class="section no-pad-bot" id="index-banner">
		<div class="container">
			<br> <br>
			<h3 class="header center">회원 가입</h3>
			<div class="row center">
				<h5 class="header col s12 light">입력폼은 너무나 단순합니다.</h5>
			</div>
			<div class="row center">
				<div class="input-field col s12">
					<i class="material-icons prefix">account_circle</i> <input
						id="username" type="text" class="validate" required minlength="2">
					<label for="icon_prefix" data-error="너무 짧은 사용자 이름입니다."
						data-success="길이가 충족됩니다.">Name</label>
				</div>
			</div>
			<div class="row center">
				<div class="input-field col s12">
					<i class="material-icons prefix">drafts</i> <input id="mail"
						type="email" class="validate"> <label for="icon_prefix"
						data-error="올바른 이메일 형식이 아닙니다." data-success="이메일 형식을 갖추었습니다.">E-mail</label><br />
					<span id="mailmsg"></span>
				</div>
			</div>
			<div class="row center">
				<div class="input-field col s12">
					<i class="material-icons prefix">enhanced_encryption</i> <input
						id="password" type="password" class="validate" required
						minlength="6" maxlength="30"> <label for="password"
						data-error="암호는 6자 이상 30자 이하여야 합니다." data-success="사용 가능한 암호">암호</label>
				</div>
			</div>
			<div class="row center">
				<div class="input-field col s12">
					<i class="material-icons prefix">enhanced_encryption</i> <input
						id="password2" type="password" class="validate" required
						minlength="6" maxlength="30"> <label for="password"
						data-error="암호는 6자 이상 30자 이하여야 합니다." data-success="사용 가능한 암호">암호
						확인</label>
				</div>
			</div>
			<div class="row center">
				<div class="file-field input-field col s12">
					<div class="btn blue darken-1">
						<span><i class="material-icons prefix">add_a_photo</i></span>
					</div>
					<input id="photo" type="file" accept=".png,.jpg,.jpeg,.gif">
					<div class="file-path-wrapper">
						<input class="file-path validate" type="text" placeholder="프로필 사진">
					</div>
				</div>
			</div>
			<div class="row center">
				<p>암호는 DB에 암호화되어 저장됩니다. 신뢰하지 않으면 가입을 진행하지 마세요.</p>

				<button class="btn-large waves-effect waves-light light-blue" type="submit"
					name="action">
					가입 <i class="material-icons right">send</i>
				</button>
				&nbsp;<a href="./" id="cancel-button"
					class="btn-large waves-effect waves-light red">취소</a>
			</div>
			<br> <br>

		</div>
	</div>
</form>
<%@ include file="include/footer.jsp"%>