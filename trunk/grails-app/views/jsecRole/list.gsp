
<%@ page import="com.grailsPortal.domain.JsecRole" %>
<html>
    <head>
		<meta name="layout" content="main" />
		<gui:resources components="dialog" />
		<g:javascript library="prototype" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton" id="createTheJsecRole">
                 <g:remoteLink action="create" update="createJsecRole">New</g:remoteLink>
            </span>
        </div>
        <div class="body">
            <h1 style="margin-left:20px;">West Orlando Arts Foundation  - JsecRole </h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="showTheJsecRole">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="name" title="Name" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${jsecRoleInstanceList}" status="i" var="jsecRoleInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>
                              <div id="linkJsecRole">
                                   <g:remoteLink action="show" id="${jsecRoleInstance.id}" update="showJsecRole">${fieldValue(bean:jsecRoleInstance, field:'id')}</g:remoteLink>
                              </div>
                           </td>
                            
                        
                            <td>${fieldValue(bean:jsecRoleInstance, field:'name')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${jsecRoleInstanceTotal}" />
            </div>
        </div>
        <gui:dialog title="JsecRole" 
           modal="true" form="false"
           triggers="[show:[id:'showTheJsecRole', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="showJsecRole" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
        <gui:dialog title="JsecRole" 
           modal="true" form="false"
           triggers="[show:[id:'createTheJsecRole', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="createJsecRole" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
    </body>
</html>
