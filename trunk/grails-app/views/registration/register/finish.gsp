<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Finished Registration</title>         
    </head>
    <body>
        <div class="body">
                <div class="nav">

            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" controller="registration" action="newRegistration">Register Another Child</g:link></span>
            <span class="menuButton"><g:link class="list" controller="registration" action="register">Edit this Registration</g:link></span>
         </div>
            <h1>Finished Registration</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${registration}">
            <div class="errors">
                <g:renderErrors bean="${registration}" as="list" />
            </div>
            </g:hasErrors>
        </div>
    </body>
</html>
 