<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <div class="card horizontal">
      <div class="card-image">
        <!--<img src="https://lorempixel.com/100/190/nature/6">-->
      </div>
      <div class="card-stacked">
        <div class="card-content">
          <p>${greeting} ${user.username}님.</p>
          <p>이메일: <a>${user.email}</a></p>
          <p><button class="btn waves-effect waves-light blue">사진 변경</button></p>
          <p></p>
        </div>
        <div class="card-action">
          <a href="#">사진 변경</a>
          <a href="#">프로필 수정</a>
        </div>
      </div>
    </div>