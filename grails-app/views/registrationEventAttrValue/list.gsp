
<%@ page import="com.grailsPortal.domain.RegistrationEventAttrValue" %>
<html>
    <head>
		<meta name="layout" content="main" />
		<gui:resources components="dialog" />
		<g:javascript library="prototype" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton" id="createTheRegistrationEventAttrValue">
                 <g:remoteLink action="create" update="createRegistrationEventAttrValue">New</g:remoteLink>
            </span>
        </div>
        <div class="body">
            <h1 style="margin-left:20px;">West Orlando Arts Foundation  - RegistrationEventAttrValue </h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="showTheRegistrationEventAttrValue">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="value" title="Value" />
                        
                   	        <th>Registration Event</th>
                   	    
                   	        <th>Attribute</th>
                   	    
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${registrationEventAttrValueInstanceList}" status="i" var="registrationEventAttrValueInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>
                              <div id="linkRegistrationEventAttrValue">
                                   <g:remoteLink action="show" id="${registrationEventAttrValueInstance.id}" update="showRegistrationEventAttrValue">${fieldValue(bean:registrationEventAttrValueInstance, field:'id')}</g:remoteLink>
                              </div>
                           </td>
                            
                        
                            <td>${fieldValue(bean:registrationEventAttrValueInstance, field:'value')}</td>
                        
                            <td>${fieldValue(bean:registrationEventAttrValueInstance, field:'registrationEvent')}</td>
                        
                            <td>${fieldValue(bean:registrationEventAttrValueInstance, field:'attribute')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${registrationEventAttrValueInstanceTotal}" />
            </div>
        </div>
        <gui:dialog title="RegistrationEventAttrValue" 
           modal="true" form="false"
           triggers="[show:[id:'showTheRegistrationEventAttrValue', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="showRegistrationEventAttrValue" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
        <gui:dialog title="RegistrationEventAttrValue" 
           modal="true" form="false"
           triggers="[show:[id:'createTheRegistrationEventAttrValue', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="createRegistrationEventAttrValue" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
    </body>
</html>
