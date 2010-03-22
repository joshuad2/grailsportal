
<%@ page import="com.grailsPortal.domain.View" %>
<html>
    <head>
		<meta name="layout" content="main" />
		<gui:resources components="dialog" />
		<g:javascript library="prototype" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton" id="createTheView">
                 <g:remoteLink action="create" update="createView">New</g:remoteLink>
            </span>
        </div>
        <div class="body">
            <h1 style="margin-left:20px;">West Orlando Arts Foundation  - View </h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="showTheView">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="name" title="Name" />
                        
                   	        <g:sortableColumn property="viewName" title="View Name" />
                        
                   	        <g:sortableColumn property="actionName" title="Action Name" />
                        
                   	        <g:sortableColumn property="title" title="Title" />
                        
                   	        <th>Portal Config Business Process</th>
                   	    
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${viewInstanceList}" status="i" var="viewInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>
                              <div id="linkView">
                                   <g:remoteLink action="show" id="${viewInstance.id}" update="showView">${fieldValue(bean:viewInstance, field:'id')}</g:remoteLink>
                              </div>
                           </td>
                            
                        
                            <td>${fieldValue(bean:viewInstance, field:'name')}</td>
                        
                            <td>${fieldValue(bean:viewInstance, field:'viewName')}</td>
                        
                            <td>${fieldValue(bean:viewInstance, field:'actionName')}</td>
                        
                            <td>${fieldValue(bean:viewInstance, field:'title')}</td>
                        
                            <td>${fieldValue(bean:viewInstance, field:'portalConfigBusinessProcess')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${viewInstanceTotal}" />
            </div>
        </div>
        <gui:dialog title="View" 
           modal="true" form="false"
           triggers="[show:[id:'showTheView', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="showView" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
        <gui:dialog title="View" 
           modal="true" form="false"
           triggers="[show:[id:'createTheView', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="createView" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
    </body>
</html>
