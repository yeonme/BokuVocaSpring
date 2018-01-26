<!DOCTYPE html>
<html lang="ko">

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0" />
  <title>ボクのボカ</title>

  <!-- CSS  -->
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection" />
  <link href="css/style.css" type="text/css" rel="stylesheet" media="screen,projection" />
</head>

<body>
  <nav class="light-blue lighten-1" role="navigation">
    <div class="nav-wrapper container">
      <a id="logo-container" href="#" class="brand-logo">ボクのボカ</a>
      <ul class="right hide-on-med-and-down">
        <li class="logout" style="display:none;">
          <a href="lib/query.php?todo=logout">
            로그아웃</a>
        </li>
        <li>
          <!--<a href="info.html" title="정보">-->
          <a href="ajax/main.html">
            <i class="material-icons">info</i>
          </a>
        </li>
      </ul>

      <ul id="nav-mobile" class="side-nav">
        <li class="logout" style="display:none;">
          <a href="lib/query.php?todo=logout">
            로그아웃</a>
        </li>
        <li>
          <a href="info.html">
            <i class="material-icons">info</i>정보</a>
        </li>
      </ul>
      <a href="#" data-activates="nav-mobile" class="button-collapse">
        <i class="material-icons">menu</i>
      </a>
    </div>
  </nav>
  <div id="outcontainer">

  </div>

  <footer class="page-footer orange">
    <div class="container">
      <div class="row">
        <div class="col l6 s12">
          <h5 class="white-text">Company Bio</h5>
          <p class="grey-text text-lighten-4">We are a team of college students working on this project like it's our full time job. Any amount would help support
            and continue development on this project and is greatly appreciated.</p>


        </div>
        <div class="col l3 s12">
          <h5 class="white-text">Settings</h5>
          <ul>
            <li>
              <a class="white-text" href="#!">Link 1</a>
            </li>
            <li>
              <a class="white-text" href="#!">Link 2</a>
            </li>
            <li>
              <a class="white-text" href="#!">Link 3</a>
            </li>
            <li>
              <a class="white-text" href="#!">Link 4</a>
            </li>
          </ul>
        </div>
        <div class="col l3 s12">
          <h5 class="white-text">Connect</h5>
          <ul>
            <li>
              <a class="white-text" href="#!">Link 1</a>
            </li>
            <li>
              <a class="white-text" href="#!">Link 2</a>
            </li>
            <li>
              <a class="white-text" href="#!">Link 3</a>
            </li>
            <li>
              <a class="white-text" href="#!">Link 4</a>
            </li>
          </ul>
        </div>
      </div>
    </div>
    <div class="footer-copyright">
      <div class="container">
        Made by
        <a class="orange-text text-lighten-3" href="http://materializecss.com">Materialize</a>
      </div>
    </div>
  </footer>


  <!--  Scripts-->
  <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
  <script src="js/materialize.js"></script>
  <script src="js/init.js"></script>
  <script>
    var needeach = true;
    var content = $('#outcontainer');
    var ajaxLoadPage = function (url) {

      console.log('Loading ' + url + ' fragment');
      content.load(url + '?fragment=true');

    }
    var eachpage = function () {
      console.log('fired ready method');

      // Handle click event of all links with href not starting with http, https or #
      //$('a').not('[href^=http], [href^=https], [href^=#]').on('click', function (e) {
      $('a[href*=ajax]').not('[href^=http], [href^=https], [href^=#]').on('click', function (e) {
        e.preventDefault();
        var href = $(this).attr('href');
        console.log("href: "+href);
        needeach = false;
        ajaxLoadPage(href);
        //href = /[^/]*$/.exec(href)[0];
        history.pushState({ page: href }, "sample state", "/api/bv3/");
        e.stopPropagation();
        return false;
      });
    };
    var script = function () {

      // Used to detect initial (useless) popstate.
      // If history.state exists, pushState() has created the current entry so we can
      // assume browser isn't going to fire initial popstate
      var popped = ('state' in window.history && window.history.state !== null), initialURL = location.href;

      $('.logout').hide();
      ajaxLoadPage("ajax/login.php");
      

      $(window).bind('popstate', function (event) {

        // Ignore inital popstate that some browsers fire on page load
        var initialPop = !popped && location.href == initialURL;
        popped = true;

        console.log(JSON.stringify(event.originalEvent.state));
        //if (initialPop) { console.log("popstate>initialpop. popped: "+popped+", location.href: "+location.href+", initial: "+initialURL); return; }
        var target = (event.originalEvent.state!=null)?event.originalEvent.state.page:location.pathname;

        console.log('Popstate: ' + target+', state: '+event.state);
        //징배기님 천재!!
        // By the time popstate has fired, location.pathname has been changed

        if (target.endsWith('/')||target.endsWith('login.html')) {
          ajaxLoadPage("ajax/login.html");
        } else {
          var relativeFix = target;
          if(target.indexOf('ajax/')<0){
            relativeFix = target.replace("/api/bv3/","ajax/");
          }
          ajaxLoadPage(relativeFix);
        }
      });
    };
    $(this).ready(script);
    $(document).ajaxComplete(eachpage);
  </script>

</body>

</html>