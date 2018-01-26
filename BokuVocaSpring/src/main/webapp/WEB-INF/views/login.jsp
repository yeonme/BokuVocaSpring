<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">

<head>
  <title>ボクのボカ - 로그인</title>
<%@ include file="include/header.jsp"  %>
<div class="section no-pad-bot" id="index-banner">
    <div class="container">
        <br>
        <br>
        <h3 class="header center">보쿠노보카</h3>
        <div class="row center">
            <h5 class="header col s12 light">나와 모두의 일본어 단어장</h5>
        </div>
        <div class="row center">
            <div class="input-field col s12">
                <i class="material-icons prefix">account_circle</i>
                <input type="text" class="validate" id="inputEmail">
                <label for="icon_prefix">E-mail</label>
            </div>
        </div>
        <div class="row center">
            <div class="input-field col s12">
                <i class="material-icons prefix">enhanced_encryption</i>
                <input type="password" class="validate" id="inputPass">
                <label for="password">암호</label>
            </div>
        </div>
        <div class="row center">
            <a href="#" id="login-button" class="btn-large waves-effect waves-light blue">로그인</a>&nbsp;
            <a href="join" id="join-button" class="btn-large waves-effect waves-light orange" style="font-weight:bold;">매우 빠른 회원 가입</a>
        </div>
        <br>
        <br>

    </div>
</div>


<div class="container">
    <div class="section">

        <div class="row s12 m6 center">
            <p class="flow-text">Created by Yeon 2017
                <br/>Database from 민중서림</p>
        </div>
    </div>
    <br>
    <br>
</div>
<script>
    $(function() {
        $('#login-button').click(function(e) {
            e.preventDefault();
            e.stopPropagation();

            if($('#inputEmail').val() == undefined||$('#inputPass').val() == undefined) {
                return false;
            }
            
            $.post("lib/query.php", { todo: "login", email: $('#inputEmail').val(), pass: $('#inputPass').val() },
                function (data) {
                    console.log(data);
                    var result = JSON.parse(data);
                    if (result["result"] != "ok") {
                        //alert(data);
                        return false;
                    } else {
                        //alert(data);
                        
                        var href = "ajax/main.php";
                        console.log("href: " + href);
                        needeach = false;
                        ajaxLoadPage(href);
                        //href = /[^/]*$/.exec(href)[0];
                        history.pushState({ page: href }, "sample state", "/api/bv3/");
                    }

                }).fail(function () {
                    alert('연결 문제로 로그인 요청에 실패했습니다.');
                    return false;
                });
        });
    });
</script>
<%@ include file="include/footer.jsp" %>
