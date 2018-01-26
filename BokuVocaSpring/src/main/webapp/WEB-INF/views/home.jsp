<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">

<head>
  <title>ボクのボカ - 반가워요</title>
<%@ include file="include/header.jsp"  %>
<div class="section no-pad-bot" id="index-banner">
    <div class="container">
        <br>
        <br>
        <h1 class="header center orange-text">보쿠노보카</h1>
        <div class="row center">
            <h5 class="header col s12 light">나와 모두의 일본어 단어장</h5>
        </div>
        <div class="row center">
            <!-- <p>반갑습니다. ${session.userName }님.</p>-->
            <div id="profile_card">
            </div>
            <!--<a href="http://materializecss.com/getting-started.html" id="login-button" class="btn-large waves-effect waves-light orange"></a>-->
            <div class="input-field col s12 ui-widget">
                <i class="material-icons prefix">search</i>
                <input type="text" id="autocomplete-input" class="autocomplete">
                <label for="autocomplete-input">검색어</label>
            </div>
        </div>
        <ul id="autocomplete-ul" class="collection" style="font-size:90%;">

        </ul>
        <br>
        <br>

    </div>
</div>


<div class="container">
    <div class="section">

        <!--   Icon Section   -->
        <div class="row">
            <div class="col s12 m6">
                <div class="icon-block">
                    <h2 class="center light-blue-text">
                        <i class="material-icons">live_help</i>
                    </h2>
                    <h5 class="center">퀴즈</h5>

                    <p class="light">과연 내가 알고 있는 것이 전부일까요? 사전에는 아직까지 스쳐지나가지 않은 어휘들이 수두룩합니다.</p>
                    <p class="light center">
                        <a class="btn-large waves-effect waves-light blue">입장하기</a>
                    </p>
                </div>
            </div>

            <div class="col s12 m6">
                <div class="icon-block">
                    <h2 class="center light-blue-text">
                        <i class="material-icons">lightbulb_outline</i>
                    </h2>
                    <h5 class="center">단어장</h5>

                    <p class="light">여러분이 기억한 단어, 틀린 단어가 모두 한 곳에 모여 있습니다. 어쩐지 자꾸만 가까이하고 싶어지고, 단어장만 모아서 퀴즈도 풀고, 그러다보면 나도 모르게 기억하게 됩니다.</p>
                    <p class="light center">
                        <a class="btn-large waves-effect waves-light blue">입장하기</a>
                    </p>
                </div>
            </div>
        </div>

    </div>
    <br>
    <br>
</div>
<%@ include file="include/footer.jsp" %>