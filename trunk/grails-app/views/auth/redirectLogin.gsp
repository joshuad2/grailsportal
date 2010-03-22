
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="layout" content="main" />
<gui:resources components="['accordion','dialog']" />
<g:javascript library="prototype" />
<title>User Login</title>
</head>
<body>
<g:if test="${flash.message}">
	<div class="message">
	${flash.message}
	</div>
</g:if>
<p>Please use the link in the header of this page to Login to the Portal</p>
</body>