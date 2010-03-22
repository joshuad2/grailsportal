

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Register User</title>         
    </head>
    <body>
  <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
   </div>
        <div class="body">
            <jsec:isNotLoggedIn>
              <h1>Register for WebSite</h1>
            </jsec:isNotLoggedIn>
            <jsec:isLoggedIn>
              <h1>Update User Profile</h1>
            </jsec:isLoggedIn>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${partyInstance}">
            <div class="errors">
                <g:renderErrors bean="${registerInstance}" as="list" />
            </div>
            </g:hasErrors>
        <!--
          <jsec:isNotLoggedIn>
            <div>You have successfully registered, please <g:link href="login">login</g:link> to the website.</div>
          </jsec:isNotLoggedIn>
        -->
        </div>
        <script type="text/javascript">
var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
</script>
<script type="text/javascript">
try {
var pageTracker = _gat._getTracker("UA-5367093-2");
pageTracker._trackPageview();
} catch(err) {}</script>
    </body>
</html>
