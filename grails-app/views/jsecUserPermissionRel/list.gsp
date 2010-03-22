
<%@ page import="com.grailsPortal.domain.JsecUserPermissionRel" %>
<html>
    <head>
		<meta name="layout" content="main" />
		<gui:resources components="dialog" />
		<g:javascript library="prototype" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton" id="createTheJsecUserPermissionRel">
                 <g:remoteLink action="create" update="createJsecUserPermissionRel">New</g:remoteLink>
            </span>
        </div>
        <div class="body">
            <h1 style="margin-left:20px;">West Orlando Arts Foundation  - JsecUserPermissionRel </h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="showTheJsecUserPermissionRel">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="target" title="Target" />
                        
                   	        <g:sortableColumn property="actions" title="Actions" />
                        
                   	        <th>Permission</th>
                   	    
                   	        <th>User</th>
                   	    
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${jsecUserPermissionRelInstanceList}" status="i" var="jsecUserPermissionRelInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>
                              <div id="linkJsecUserPermissionRel">
                                   <g:remoteLink action="show" id="${jsecUserPermissionRelInstance.id}" update="showJsecUserPermissionRel">${fieldValue(bean:jsecUserPermissionRelInstance, field:'id')}</g:remoteLink>
                              </div>
                           </td>
                            
                        
                            <td>${fieldValue(bean:jsecUserPermissionRelInstance, field:'target')}</td>
                        
                            <td>${fieldValue(bean:jsecUserPermissionRelInstance, field:'actions')}</td>
                        
                            <td>${fieldValue(bean:jsecUserPermissionRelInstance, field:'permission')}</td>
                        
                            <td>${fieldValue(bean:jsecUserPermissionRelInstance, field:'user')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${jsecUserPermissionRelInstanceTotal}" />
            </div>
        </div>
        <gui:dialog title="JsecUserPermissionRel" 
           modal="true" form="false"
           triggers="[show:[id:'showTheJsecUserPermissionRel', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="showJsecUserPermissionRel" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
        <gui:dialog title="JsecUserPermissionRel" 
           modal="true" form="false"
           triggers="[show:[id:'createTheJsecUserPermissionRel', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="createJsecUserPermissionRel" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
    </body>
</html>
