
<%@ page import="com.grailsPortal.domain.JsecRolePermissionRel" %>
<html>
    <head>
		<meta name="layout" content="main" />
		<gui:resources components="dialog" />
		<g:javascript library="prototype" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton" id="createTheJsecRolePermissionRel">
                 <g:remoteLink action="create" update="createJsecRolePermissionRel">New</g:remoteLink>
            </span>
        </div>
        <div class="body">
            <h1 style="margin-left:20px;">West Orlando Arts Foundation  - JsecRolePermissionRel </h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="showTheJsecRolePermissionRel">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="actions" title="Actions" />
                        
                   	        <g:sortableColumn property="target" title="Target" />
                        
                   	        <th>Permission</th>
                   	    
                   	        <th>Role</th>
                   	    
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${jsecRolePermissionRelInstanceList}" status="i" var="jsecRolePermissionRelInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>
                              <div id="linkJsecRolePermissionRel">
                                   <g:remoteLink action="show" id="${jsecRolePermissionRelInstance.id}" update="showJsecRolePermissionRel">${fieldValue(bean:jsecRolePermissionRelInstance, field:'id')}</g:remoteLink>
                              </div>
                           </td>
                            
                        
                            <td>${fieldValue(bean:jsecRolePermissionRelInstance, field:'actions')}</td>
                        
                            <td>${fieldValue(bean:jsecRolePermissionRelInstance, field:'target')}</td>
                        
                            <td>${fieldValue(bean:jsecRolePermissionRelInstance, field:'permission')}</td>
                        
                            <td>${fieldValue(bean:jsecRolePermissionRelInstance, field:'role')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${jsecRolePermissionRelInstanceTotal}" />
            </div>
        </div>
        <gui:dialog title="JsecRolePermissionRel" 
           modal="true" form="false"
           triggers="[show:[id:'showTheJsecRolePermissionRel', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="showJsecRolePermissionRel" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
        <gui:dialog title="JsecRolePermissionRel" 
           modal="true" form="false"
           triggers="[show:[id:'createTheJsecRolePermissionRel', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="createJsecRolePermissionRel" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
    </body>
</html>
