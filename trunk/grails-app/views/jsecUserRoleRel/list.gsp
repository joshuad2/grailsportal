
<%@ page import="com.grailsPortal.domain.JsecUserRoleRel" %>
<html>
    <head>
		<meta name="layout" content="main" />
		<gui:resources components="dialog" />
		<g:javascript library="prototype" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton" id="createTheJsecUserRoleRel">
                 <g:remoteLink action="create" update="createJsecUserRoleRel">New</g:remoteLink>
            </span>
        </div>
        <div class="body">
            <h1 style="margin-left:20px;">West Orlando Arts Foundation  - JsecUserRoleRel </h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="showTheJsecUserRoleRel">
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
                        
                            <td>
                              <div id="linkJsecUserRoleRel">
                                   <g:remoteLink action="show" id="${jsecUserRoleRelInstance.id}" update="showJsecUserRoleRel">${fieldValue(bean:jsecUserRoleRelInstance, field:'id')}</g:remoteLink>
                              </div>
                           </td>
                            
                        
                            <td>${fieldValue(bean:jsecUserRoleRelInstance, field:'user')}</td>
                        
                            <td>${fieldValue(bean:jsecUserRoleRelInstance, field:'role')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${jsecUserRoleRelInstanceTotal}" />
            </div>
        </div>
        <gui:dialog title="JsecUserRoleRel" 
           modal="true" form="false"
           triggers="[show:[id:'showTheJsecUserRoleRel', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="showJsecUserRoleRel" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
        <gui:dialog title="JsecUserRoleRel" 
           modal="true" form="false"
           triggers="[show:[id:'createTheJsecUserRoleRel', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="createJsecUserRoleRel" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
    </body>
</html>
