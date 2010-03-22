
<%@ page import="com.grailsPortal.domain.RegistrationEventParty" %>
<html>
    <head>
		<meta name="layout" content="main" />
		<gui:resources components="dialog" />
		<g:javascript library="prototype" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton" id="createTheRegistrationEventParty">
                 <g:remoteLink action="create" update="createRegistrationEventParty">New</g:remoteLink>
            </span>
        </div>
        <div class="body">
            <h1 style="margin-left:20px;">West Orlando Arts Foundation  - RegistrationEventParty </h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="showTheRegistrationEventParty">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <th>Party</th>
                   	    
                   	        <th>Registration Event</th>
                   	    
                   	        <th>Role</th>
                   	    
                   	        <g:sortableColumn property="active" title="Active" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${registrationEventPartyInstanceList}" status="i" var="registrationEventPartyInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>
                              <div id="linkRegistrationEventParty">
                                   <g:remoteLink action="show" id="${registrationEventPartyInstance.id}" update="showRegistrationEventParty">${fieldValue(bean:registrationEventPartyInstance, field:'id')}</g:remoteLink>
                              </div>
                           </td>
                            
                        
                            <td>${fieldValue(bean:registrationEventPartyInstance, field:'party')}</td>
                        
                            <td>${fieldValue(bean:registrationEventPartyInstance, field:'registrationEvent')}</td>
                        
                            <td>${fieldValue(bean:registrationEventPartyInstance, field:'role')}</td>
                        
                            <td>${fieldValue(bean:registrationEventPartyInstance, field:'active')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${registrationEventPartyInstanceTotal}" />
            </div>
        </div>
        <gui:dialog title="RegistrationEventParty" 
           modal="true" form="false"
           triggers="[show:[id:'showTheRegistrationEventParty', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="showRegistrationEventParty" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
        <gui:dialog title="RegistrationEventParty" 
           modal="true" form="false"
           triggers="[show:[id:'createTheRegistrationEventParty', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="createRegistrationEventParty" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
    </body>
</html>
