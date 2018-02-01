<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">

<head>
  <title>ボクのボカ - 퀴즈 시작</title>
<%@ include file="include/header.jsp"  %>
<script>
//var questions = ${quiz };
var q = ${qlist}; 
var qnum = 0;
var result = [];
</script>
<div class="section no-pad-bot" id="index-banner">
<div class="container">
<p id="question"></p>
<p ><input class="with-gap bogi" name="bogi" id="bogi1" type="radio"/><label for="bogi1" id="bogi1_label"></label><input class="with-gap bogi" name="bogi" id="bogi2" type="radio"/><label for="bogi2" id="bogi2_label"></label>
<input class="with-gap bogi" name="bogi" id="bogi3" type="radio"/><label for="bogi3" id="bogi3_label"></label><input class="with-gap bogi" name="bogi" id="bogi4" type="radio"/><label for="bogi4" id="bogi4_label"></label>
<input class="with-gap bogi" name="bogi" id="bogi5" type="radio"/><label for="bogi5" id="bogi5_label"></label></p>
<script>
function prepareQuiz(onum){
	$('.bogi').prop("checked",false);
	if(q[onum]["type"]==1){ //뜻 맞추기
		$('#question').text("이 단어는 뭘까요? "+q[onum]["bogi"][q[onum]["answer"]]["summary"]);
		$('#bogi1_label').text(q[onum]["bogi"][0]["yomi"]+" ("+q[onum]["bogi"][0]["word"]+")");
		$('#bogi2_label').text(q[onum]["bogi"][1]["yomi"]+" ("+q[onum]["bogi"][1]["word"]+")");
		$('#bogi3_label').text(q[onum]["bogi"][2]["yomi"]+" ("+q[onum]["bogi"][2]["word"]+")");
		$('#bogi4_label').text(q[onum]["bogi"][3]["yomi"]+" ("+q[onum]["bogi"][3]["word"]+")");
		$('#bogi5_label').text(q[onum]["bogi"][4]["yomi"]+" ("+q[onum]["bogi"][4]["word"]+")");
	} else { //
		$('#question').text("이 단어를 읽으면 어떻게 될까요? "+q[onum]["bogi"][q[onum]["answer"]]["word"]);
		$('#bogi1_label').text(q[onum]["bogi"][0]["yomi"]);
		$('#bogi2_label').text(q[onum]["bogi"][1]["yomi"]);
		$('#bogi3_label').text(q[onum]["bogi"][2]["yomi"]);
		$('#bogi4_label').text(q[onum]["bogi"][3]["yomi"]);
		$('#bogi5_label').text(q[onum]["bogi"][4]["yomi"]);
	}
	qnum = onum;
}
$(function(){
	prepareQuiz(0);
	$('.bogi').on('click',function(sen){
		var myans = 0;
		if(sen.target.id=="bogi1"){
			myans = 0;
		}else if(sen.target.id=="bogi2"){
			myans = 1;
		}else if(sen.target.id=="bogi3"){
			myans = 2;
		}else if(sen.target.id=="bogi4"){
			myans = 3;
		}else if(sen.target.id=="bogi5"){
			myans = 4;
		}
		
		if(q[qnum]["answer"]==myans){
			//정답
			result.push({"word": q[qnum]["bogi"][q[qnum]["answer"]]["num"], "correct": true});
			$('#result').append('<li class="collection-item"><div>'+$('#question').text()+'<a href="#!" class="secondary-content"><i class="material-icons">check</i>'+q[qnum]["bogi"][q[qnum]["answer"]]["yomi"]+'</a></div></li>');
		}else{
			//오답
			result.push({"word": q[qnum]["bogi"][q[qnum]["answer"]]["num"], "correct": false});
			$('#result').append('<li class="collection-item"><div>'+$('#question').text()+'<a href="#!" class="secondary-content wrong"><i class="material-icons">close</i>'+q[qnum]["bogi"][q[qnum]["answer"]]["yomi"]+'</a></div></li>');
		}
		if(qnum >= 9){
			$('.bogi').off('click');
			console.log('end');
			$.ajax({
				url : '<c:url value="/report"/>'
		        , method : "post"
				//, dataType : 'json'
				, data : JSON.stringify(result)
				//, processData : true /*querySTring make false*/
				, contentType : "application/json; charset=UTF-8"
				, success : function(data, stat, xhr) {
					//alert("success");
					alert("퀴즈가 끝났습니다. 오답은 단어장에서 확인하세요.");
					location.href="<c:url value='/quiz'/>";
				}
			   , error : function(xhr, stat, err) {
			   	//alert("error");
			   	console.log(err);
			   	location.href="<c:url value='/quiz'/>";
			   }
			});
			
		} else{
			prepareQuiz(qnum+1);
		}
	});
});
</script>
<div id="results">
<ul class="collection with-header" id="result">
        <li class="collection-header"><h4>정오표</h4></li>
      </ul>
</div>
</div>
</div>
<%@ include file="include/footer.jsp" %>