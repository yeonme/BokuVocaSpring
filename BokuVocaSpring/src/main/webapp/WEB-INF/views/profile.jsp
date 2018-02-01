<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <div class="card horizontal">
      <div class="card-image">
        <a href="picture?photoname=${user.photo }"><img src="picture?photoname=${user.photo}" style="max-width:50%;"></a>
      </div>
      <div class="card-stacked">
        <div class="card-content">
          <p>${greeting} ${user.username}님.</p>
          <p>이메일: <a>${user.email}</a></p>
          <p></p>
        </div>
      </div>
    </div>