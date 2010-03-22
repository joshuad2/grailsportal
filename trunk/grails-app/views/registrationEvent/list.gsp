
<%@ page import="com.grailsPortal.domain.RegistrationEvent" %>
<html>
    <head>
		<meta name="layout" content="main" />
		<gui:resources components="dialog" />
		<g:javascript library="prototype" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton" id="createTheRegistrationEvent">
                 <g:remoteLink action="create" update="createRegistrationEvent">New</g:remoteLink>
            </span>
        </div>
        <div class="body">
            <h1 style="margin-left:20px;">West Orlando Arts Foundation  - RegistrationEvent </h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="showTheRegistrationEvent">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <th>Registration For</th>
                   	    
                   	        <th>Registration User</th>
                   	    
                   	        <g:sortableColumn property="registrationDate" title="Registration Date" />
                        
                   	        <g:sortableColumn property="registrationGrade" title="Registration Grade" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${registrationEventInstanceList}" status="i" var="registrationEventInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>
                              <div id="linkRegistrationEvent">
                                   <g:remoteLink action="show" id="${registrationEventInstance.id}" update="showRegistrationEvent">${fieldValue(bean:registrationEventInstance, field:'id')}</g:remoteLink>
                              </div>
                           </td>
                            
                        
                            <td>${fieldValue(bean:registrationEventInstance, field:'registrationFor')}</td>
                        
                            <td>${fieldValue(bean:registrationEventInstance, field:'registrationUser')}</td>
                        
                            <td>${fieldValue(bean:registrationEventInstance, field:'registrationDate')}</td>
                        
                            <td>${fieldValue(bean:registrationEventInstance, field:'registrationGrade')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${registrationEventInstanceTotal}" />
            </div>
        </div>
        <gui:dialog title="RegistrationEvent" 
           modal="true" form="false"
           triggers="[show:[id:'showTheRegistrationEvent', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="showRegistrationEvent" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
        <gui:dialog title="RegistrationEvent" 
           modal="true" form="false"
           triggers="[show:[id:'createTheRegistrationEvent', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="createRegistrationEvent" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
    </body>
</html>
