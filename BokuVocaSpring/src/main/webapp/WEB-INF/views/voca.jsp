<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">

<head>
  <title>ボクのボカ - 단어장</title>
<%@ include file="include/header.jsp"  %>
<div class="section no-pad-bot" id="index-banner">
<div class="container">
<ul class="collapsible" data-collapsible="accordion">
<c:if test="${empty vocalist}"><div>여기가 썰렁합니다. 단어장부터 등록해보세요!</div></c:if>
<c:forEach items="${vocalist}" var="item">
<li><div class="collapsible-header">${item.yomi } (${item.word })</div><div class="collapsible-body card" data-num="${item.num }"></div></li>
</c:forEach>
<script>
$('.collapsible-header').click(function(sender){
	console.log(sender);
	var num = $(sender.target.nextSibling).attr('data-num');
	$.ajax({
		url : "word",
		data : {
			num : num,
			nojump : "1"
		},
		success : function(result) {
			$(sender.target.nextSibling).html(result);
		},
		error : function(a) {
			console.log(a);
		}
	});
});

function delWord(num){
	$.ajax({
		url: "vocadel"
		//, dataType : 'json'
		, data : {
			num: num
		}
		//, processData : true /*querySTring make false*/
		, contentType : "application/json; charset=UTF-8"
		, success : function(data, stat, xhr) {
			location.reload();
			}
		, error : function(xhr, stat, err) {
		   	//alert("error");
			location.reload();
		   }
	});
}
</script>
</ul>

  <ul class="pagination">
    <c:forEach begin="1" end="${tpage}" var="i">
    	<li class="<c:choose><c:when test="${cpage == i}">active</c:when><c:otherwise>waves-effect</c:otherwise></c:choose>"><a href="?page=${i }">${i }</a></li>
    </c:forEach>
  </ul>
  
</div>
</div>
<%@ include file="include/footer.jsp" %>