<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0" />

  <!-- CSS  -->
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/resources/css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection" />
  <link href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css" rel="stylesheet" media="screen,projection" />
  <!--  Scripts-->
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/materialize.js"></script>
</head>

<body>
<nav class="light-blue lighten-1" role="navigation">
	<div class="nav-wrapper container">
		<a id="logo-container" href="./" class="brand-logo">ボクのボカ</a>
		<ul class="right hide-on-med-and-down">
			<li class="logout" style="display: none;"><a href="logout">
					로그아웃</a></li>
			<li>
				<!--<a href="info.html" title="정보">--> <a href="info"> <i
					class="material-icons">info</i>
			</a>
			</li>
		</ul>

		<ul id="nav-mobile" class="side-nav">
			<li class="logout" style="display: none;"><a href="logout">
					로그아웃</a></li>
			<li><a href="info"> <i class="material-icons">info</i>정보
			</a></li>
		</ul>
		<a href="#" data-activates="nav-mobile" class="button-collapse"> <i
			class="material-icons">menu</i>
		</a>
	</div>
</nav>
<div id="outcontainer">