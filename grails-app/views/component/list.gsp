
<%@ page import="com.grailsPortal.domain.Component" %>
<html>
    <head>
		<meta name="layout" content="main" />
		<gui:resources components="dialog" />
		<g:javascript library="prototype" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton" id="createTheComponent">
                 <g:remoteLink action="create" update="createComponent">New</g:remoteLink>
            </span>
        </div>
        <div class="body">
            <h1 style="margin-left:20px;">West Orlando Arts Foundation  - Component </h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="showTheComponent">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="name" title="Name" />
                        
                   	        <g:sortableColumn property="dsc" title="Dsc" />
                        
                   	        <g:sortableColumn property="cd" title="Cd" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${componentInstanceList}" status="i" var="componentInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>
                              <div id="linkComponent">
                                   <g:remoteLink action="show" id="${componentInstance.id}" update="showComponent">${fieldValue(bean:componentInstance, field:'id')}</g:remoteLink>
                              </div>
                           </td>
                            
                        
                            <td>${fieldValue(bean:componentInstance, field:'name')}</td>
                        
                            <td>${fieldValue(bean:componentInstance, field:'dsc')}</td>
                        
                            <td>${fieldValue(bean:componentInstance, field:'cd')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${componentInstanceTotal}" />
            </div>
        </div>
        <gui:dialog title="Component" 
           modal="true" form="false"
           triggers="[show:[id:'showTheComponent', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="showComponent" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
        <gui:dialog title="Component" 
           modal="true" form="false"
           triggers="[show:[id:'createTheComponent', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="createComponent" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
    </body>
</html>
