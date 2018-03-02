<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<title>ボクのボカ - 퀴즈</title>
<%@ include file="include/header.jsp"%>
<div class="section no-pad-bot" id="index-banner">
	<div class="container">
		<ul class="collapsible" data-collapsible="accordion">
			<li>
				<div class="collapsible-header">
					<i class="material-icons">filter_drama</i>모두 랜덤<a href="<c:url value='/quiz/rand'/>" class="rightfloat waves-effect waves-light btn">입장하기</a>
				</div>
				<div class="collapsible-body">
					<span>진정한 고수는 범위를 한정하지 않고 사전 범위 모두를 맞출 수 있을 겁니다.</span>
				</div>
			</li>
			<li>
				<div class="collapsible-header">
					<i class="material-icons">place</i>중요도별 랜덤<a href="<c:url value='/quiz/star2'/>" class="rightfloat waves-effect waves-light btn">매우 중요</a><a href="<c:url value='/quiz/star1'/>" class="rightfloat waves-effect waves-light btn">중요</a><a href="<c:url value='/quiz/star0'/>" class="rightfloat waves-effect waves-light btn">표준</a>
				</div>
				<div class="collapsible-body">
					<span>중요도가 높은 별표 단어로 표시된 표제어만 출제됩니다. 사실 자주 쓰는 표현을 알아야 일본어 실력이 크게 늘겠죠.</span>
				</div>
			</li>
			<li>
				<div class="collapsible-header">
					<i class="material-icons">error</i>JLPT N 급수별<a href="<c:url value='/quiz/jlpt5'/>" class="rightfloat waves-effect waves-light btn">N5</a><a href="<c:url value='/quiz/jlpt4'/>" class="rightfloat waves-effect waves-light btn">N4</a><a href="<c:url value='/quiz/jlpt3'/>" class="rightfloat waves-effect waves-light btn">N3</a><a href="<c:url value='/quiz/jlpt2'/>" class="rightfloat waves-effect waves-light btn">N2</a><a href="<c:url value='/quiz/jlpt1'/>" class="rightfloat waves-effect waves-light btn">N1</a>
				</div>
				<div class="collapsible-body">
					<span>JLPT N 급수를 한정하여 JLPT 특별 대비를 해봅시다.</span>
				</div>
			</li>
			<li>
				<div class="collapsible-header">
					<i class="material-icons">edit</i>내 단어장에서 출제<a href="<c:url value='/quiz/voca'/>" class="rightfloat waves-effect waves-light btn">입장하기</a>
				</div>
				<div class="collapsible-body">
					<span>내 단어장에 저장된 단어에서만 출제하면 단어장의 암기 현황을 확인할 수 있습니다.</span>
				</div>
			</li>
		</ul>
	</div>
</div>
<%@ include file="include/footer.jsp"%>