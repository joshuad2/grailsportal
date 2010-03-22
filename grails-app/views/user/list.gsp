

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>West Orlando Arts Foundation User Profiles</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New User Profile</g:link></span>
        </div>
        <div class="body">
            <h1>West Orlando Arts Foundation User Profiles</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="username" title="User Name" />
                        
                   	        <th>First Name</th>
                   	        <th>Last Name</th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${jsecUserInstanceList}" status="i" var="jsecUserInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${jsecUserInstance.id}">${fieldValue(bean:jsecUserInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:jsecUserInstance, field:'username')}</td>
                        
                            <td>${fieldValue(bean:jsecUserInstance, field:'party.firstName')}</td>
                            <td>${fieldValue(bean:jsecUserInstance, field:'party.lastName')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${JsecUser}.count()" />
            </div>
        </div>
    </body>
</html>
