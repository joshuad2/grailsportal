
<%@ page import="com.grailsPortal.domain.ContactType" %>
<html>
    <head>
		<meta name="layout" content="main" />
		<gui:resources components="dialog" />
		<g:javascript library="prototype" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton" id="createTheContactType">
                 <g:remoteLink action="create" update="createContactType">New</g:remoteLink>
            </span>
        </div>
        <div class="body">
            <h1 style="margin-left:20px;">West Orlando Arts Foundation  - ContactType </h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="showTheContactType">
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
                    <g:each in="${contactTypeInstanceList}" status="i" var="contactTypeInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>
                              <div id="linkContactType">
                                   <g:remoteLink action="show" id="${contactTypeInstance.id}" update="showContactType">${fieldValue(bean:contactTypeInstance, field:'id')}</g:remoteLink>
                              </div>
                           </td>
                            
                        
                            <td>${fieldValue(bean:contactTypeInstance, field:'cd')}</td>
                        
                            <td>${fieldValue(bean:contactTypeInstance, field:'dsc')}</td>
                        
                            <td>${fieldValue(bean:contactTypeInstance, field:'name')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${contactTypeInstanceTotal}" />
            </div>
        </div>
        <gui:dialog title="ContactType" 
           modal="true" form="false"
           triggers="[show:[id:'showTheContactType', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="showContactType" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
        <gui:dialog title="ContactType" 
           modal="true" form="false"
           triggers="[show:[id:'createTheContactType', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="createContactType" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
    </body>
</html>
