<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">

<head>
  <title>ボクのボカ - 회원 가입</title>
<%@ include file="include/header.jsp"  %>
<div class="section no-pad-bot" id="index-banner">
    <div class="container">
        <br>
        <br>
        <h3 class="header center">회원 가입</h3>
        <div class="row center">
            <h5 class="header col s12 light">입력폼은 너무나 단순합니다.</h5>
        </div>
        <div class="row center">
            <div class="input-field col s12">
                <i class="material-icons prefix">account_circle</i>
                <input id="username" type="text" class="validate" required minlength="2">
                <label for="icon_prefix" data-error="너무 짧은 사용자 이름입니다." data-success="길이가 충족됩니다.">Name</label>
            </div>
        </div>
        <div class="row center">
            <div class="input-field col s12">
                <i class="material-icons prefix">drafts</i>
                <input id="mail" type="email" class="validate">
                <label for="icon_prefix" data-error="올바른 이메일 형식이 아닙니다." data-success="이메일 형식을 갖추었습니다.">E-mail</label><br/>
                <span id="mailmsg"></span>
            </div>
        </div>
        <div class="row center">
            <div class="input-field col s12">
                <i class="material-icons prefix">enhanced_encryption</i>
                <input id="password" type="password" class="validate" required minlength="6" maxlength="30">
                <label for="password" data-error="암호는 6자 이상 30자 이하여야 합니다." data-success="사용 가능한 암호">암호</label>
            </div>
        </div>
        <div class="row center">
            <div class="input-field col s12">
                <i class="material-icons prefix">enhanced_encryption</i>
                <input id="password2" type="password" class="validate" required minlength="6" maxlength="30">
                <label for="password" data-error="암호는 6자 이상 30자 이하여야 합니다." data-success="사용 가능한 암호">암호 확인</label>
            </div>
            <p>암호는 DB에 암호화되어 저장됩니다. 신뢰하지 않으면 가입을 진행하지 마세요.</p>
        </div>
        <div class="row center">
            <div class="input-field col s12">
                <i class="material-icons prefix">enhanced_encryption</i>
                <input id="password2" type="password" class="validate" required minlength="6" maxlength="30">
                <label for="password" data-error="암호는 6자 이상 30자 이하여야 합니다." data-success="사용 가능한 암호">암호 확인</label>
            </div>
            <p>암호는 DB에 암호화되어 저장됩니다. 신뢰하지 않으면 가입을 진행하지 마세요.</p>
        </div>
        <div class="row center">
            <a href="#" id="ok-button" class="btn-large waves-effect waves-light blue">가입</a>&nbsp;
            <a href="ajax/login.php" id="cancel-button" class="btn-large waves-effect waves-light gray">취소</a>
        </div>
        <br>
        <br>

    </div>
</div>
<%@ include file="include/footer.jsp" %>