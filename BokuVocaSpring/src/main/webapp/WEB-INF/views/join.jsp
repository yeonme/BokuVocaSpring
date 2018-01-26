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
						id="username" name="username" type="text" class="validate" required minlength="2" oninput="checkId();">
					<label for="icon_prefix">사용자 이름</label>
					<p id="username_label"></p>
				</div>
			</div>
			<div class="row center">
				<div class="input-field col s12">
					<i class="material-icons prefix">drafts</i> <input id="email"
						type="email" name="email" class="validate"> <label for="icon_prefix"
						data-error="올바른 이메일 형식이 아닙니다." data-success="이메일 형식을 갖추었습니다.">E-mail</label><br />
					<span id="mailmsg"></span>
				</div>
			</div>
			<div class="row center">
				<div class="input-field col s12">
					<i class="material-icons prefix">enhanced_encryption</i> <input id="password"
						id="password" name="password" type="password" class="validate" required
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
					<input id="photo" name="photo" type="file" accept=".png,.jpg,.jpeg,.gif">
					<div class="file-path-wrapper">
						<input class="file-path validate" type="text" placeholder="프로필 사진">
					</div>
				</div>
			</div>
			<div class="row center">
				<p>암호는 DB에 암호화되어 저장됩니다. 신뢰하지 않으면 가입을 진행하지 마세요.</p>

				<button class="btn-large waves-effect waves-light light-blue" type="submit"
					name="action">
					가입 <i class="material-icons right">edit</i>
				</button>
				&nbsp;<a href="./" id="cancel-button"
					class="btn-large waves-effect waves-light red">취소 <i class="material-icons right">cancel</i></a>
			</div>
			<br> <br>

		</div>
	</div>
</form>
<script>
function checkId(){
	var username = $('#username').val();
	if(username.length == 0){
		return;
	}
	$.ajax({
		url: 'vaildateId',
		type: 'post',
		data: {
			username: username 
		},
		//dataType: 'json',
		success: function(result){
			console.log('성공: '+result);
			if(result["result"]=="ok"){
				document.getElementById('username').setCustomValidity("");
				//$('#username').after().text("사용할 수 있는 아이디입니다.");
				$('#username_label').text("");
			} else {
				//$('#username').get().validity.valid = false;
				$('#username_label').text("입력하신 아이디는 사용중입니다.");
				document.getElementById('username').setCustomValidity("입력하신 아이디는 사용중입니다.");
				//$('#username').after().text("사용할 수 없는 아이디임이 확인됐습니다.");
			}
		},
		error: function(result){
			console.log('에러: '+result);
		}		
	});
}
</script>
<%@ include file="include/footer.jsp"%>