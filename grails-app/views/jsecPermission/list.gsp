
<%@ page import="com.grailsPortal.domain.JsecPermission" %>
<html>
    <head>
		<meta name="layout" content="main" />
		<gui:resources components="dialog" />
		<g:javascript library="prototype" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton" id="createTheJsecPermission">
                 <g:remoteLink action="create" update="createJsecPermission">New</g:remoteLink>
            </span>
        </div>
        <div class="body">
            <h1 style="margin-left:20px;">West Orlando Arts Foundation  - JsecPermission </h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="showTheJsecPermission">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="type" title="Type" />
                        
                   	        <g:sortableColumn property="possibleActions" title="Possible Actions" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${jsecPermissionInstanceList}" status="i" var="jsecPermissionInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>
                              <div id="linkJsecPermission">
                                   <g:remoteLink action="show" id="${jsecPermissionInstance.id}" update="showJsecPermission">${fieldValue(bean:jsecPermissionInstance, field:'id')}</g:remoteLink>
                              </div>
                           </td>
                            
                        
                            <td>${fieldValue(bean:jsecPermissionInstance, field:'type')}</td>
                        
                            <td>${fieldValue(bean:jsecPermissionInstance, field:'possibleActions')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${jsecPermissionInstanceTotal}" />
            </div>
        </div>
        <gui:dialog title="JsecPermission" 
           modal="true" form="false"
           triggers="[show:[id:'showTheJsecPermission', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="showJsecPermission" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
        <gui:dialog title="JsecPermission" 
           modal="true" form="false"
           triggers="[show:[id:'createTheJsecPermission', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="createJsecPermission" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
    </body>
</html>
