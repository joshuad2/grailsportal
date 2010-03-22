
<%@ page import="com.grailsPortal.domain.JsecUser" %>
<html>
    <head>
		<meta name="layout" content="main" />
		<gui:resources components="dialog" />
		<g:javascript library="prototype" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton" id="createTheJsecUser">
                 <g:remoteLink action="create" update="createJsecUser">New</g:remoteLink>
            </span>
        </div>
        <div class="body">
            <h1 style="margin-left:20px;">West Orlando Arts Foundation  - JsecUser </h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="showTheJsecUser">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="username" title="Username" />
                        
                   	        <g:sortableColumn property="passwordHash" title="Password Hash" />
                        
                   	        <th>Party</th>
                   	    
                   	        <g:sortableColumn property="active" title="Active" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${jsecUserInstanceList}" status="i" var="jsecUserInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>
                              <div id="linkJsecUser">
                                   <g:remoteLink action="show" id="${jsecUserInstance.id}" update="showJsecUser">${fieldValue(bean:jsecUserInstance, field:'id')}</g:remoteLink>
                              </div>
                           </td>
                            
                        
                            <td>${fieldValue(bean:jsecUserInstance, field:'username')}</td>
                        
                            <td>${fieldValue(bean:jsecUserInstance, field:'passwordHash')}</td>
                        
                            <td>${fieldValue(bean:jsecUserInstance, field:'party')}</td>
                        
                            <td>${fieldValue(bean:jsecUserInstance, field:'active')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${jsecUserInstanceTotal}" />
            </div>
        </div>
        <gui:dialog title="JsecUser" 
           modal="true" form="false"
           triggers="[show:[id:'showTheJsecUser', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="showJsecUser" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
        <gui:dialog title="JsecUser" 
           modal="true" form="false"
           triggers="[show:[id:'createTheJsecUser', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="createJsecUser" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
    </body>
</html>
