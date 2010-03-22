
<%@ page import="com.grailsPortal.domain.Responsibility" %>
<html>
    <head>
		<meta name="layout" content="main" />
		<gui:resources components="dialog" />
		<g:javascript library="prototype" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton" id="createTheResponsibility">
                 <g:remoteLink action="create" update="createResponsibility">New</g:remoteLink>
            </span>
        </div>
        <div class="body">
            <h1 style="margin-left:20px;">West Orlando Arts Foundation  - Responsibility </h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="showTheResponsibility">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="cd" title="Cd" />
                        
                   	        <g:sortableColumn property="dsc" title="Dsc" />
                        
                   	        <g:sortableColumn property="name" title="Name" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${responsibilityInstanceList}" status="i" var="responsibilityInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>
                              <div id="linkResponsibility">
                                   <g:remoteLink action="show" id="${responsibilityInstance.id}" update="showResponsibility">${fieldValue(bean:responsibilityInstance, field:'id')}</g:remoteLink>
                              </div>
                           </td>
                            
                        
                            <td>${fieldValue(bean:responsibilityInstance, field:'cd')}</td>
                        
                            <td>${fieldValue(bean:responsibilityInstance, field:'dsc')}</td>
                        
                            <td>${fieldValue(bean:responsibilityInstance, field:'name')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${responsibilityInstanceTotal}" />
            </div>
        </div>
        <gui:dialog title="Responsibility" 
           modal="true" form="false"
           triggers="[show:[id:'showTheResponsibility', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="showResponsibility" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
        <gui:dialog title="Responsibility" 
           modal="true" form="false"
           triggers="[show:[id:'createTheResponsibility', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="createResponsibility" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
    </body>
</html>
