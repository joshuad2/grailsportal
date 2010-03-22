

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit West Orlando Arts Foundation User Profile</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">User Profile List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New User Profile</g:link></span>
        </div>
        <div class="body">
            <h1>Edit User Profile</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${jsecUserInstance}">
            <div class="errors">
                <g:renderErrors bean="${jsecUserInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${jsecUserInstance?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="username">Username:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:jsecUserInstance,field:'username','errors')}">
                                    <input type="text" id="username" name="username" value="${fieldValue(bean:jsecUserInstance,field:'username')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="party">Party:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:jsecUserInstance,field:'party','errors')}">
                                    <g:select optionKey="id" from="${Party.list()}" name="party.id" value="${jsecUserInstance?.party?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="passwordHash">Password:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:jsecUserInstance,field:'passwordHash','errors')}">
                                    <input type="password" id="passwordHash" name="passwordHash" value="${fieldValue(bean:jsecUserInstance,field:'passwordHash')}"/>
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                <div class="dialog">
                <table>
                    <thead>
                        <tr>
                        <td colspan="3">User Role(s)</td>
                        </tr>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <th>User</th>
                   	    
                   	        <th>Role</th>
                   	    
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${jsecUserRoleRelInstanceList}" status="i" var="jsecUserRoleRelInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" controller="userRole" id="${jsecUserRoleRelInstance.id}">${fieldValue(bean:jsecUserRoleRelInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:jsecUserRoleRelInstance, field:'user')}</td>
                        
                            <td>${fieldValue(bean:jsecUserRoleRelInstance, field:'role')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" value="Update" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
