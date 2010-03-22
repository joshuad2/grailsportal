

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>User Roles</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New User Role</g:link></span>
        </div>
        <div class="body">
            <h1>User Roles</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <th>User</th>
                   	    
                   	        <th>Role</th>
                   	    
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${jsecUserRoleRelInstanceList}" status="i" var="jsecUserRoleRelInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${jsecUserRoleRelInstance.id}">${fieldValue(bean:jsecUserRoleRelInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:jsecUserRoleRelInstance, field:'user')}</td>
                        
                            <td>${fieldValue(bean:jsecUserRoleRelInstance, field:'role')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${JsecUserRoleRel}.count()" />
            </div>
        </div>
    </body>
</html>
