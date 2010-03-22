
<%@ page import="com.grailsPortal.domain.RegistrationEventOrderRecord" %>
<html>
    <head>
		<meta name="layout" content="main" />
		<gui:resources components="dialog" />
		<g:javascript library="prototype" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton" id="createTheRegistrationEventOrderRecord">
                 <g:remoteLink action="create" update="createRegistrationEventOrderRecord">New</g:remoteLink>
            </span>
        </div>
        <div class="body">
            <h1 style="margin-left:20px;">West Orlando Arts Foundation  - RegistrationEventOrderRecord </h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="showTheRegistrationEventOrderRecord">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <th>Event</th>
                   	    
                   	        <th>Order</th>
                   	    
                   	        <g:sortableColumn property="isActive" title="Is Active" />
                        
                   	        <g:sortableColumn property="comment" title="Comment" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${registrationEventOrderRecordInstanceList}" status="i" var="registrationEventOrderRecordInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>
                              <div id="linkRegistrationEventOrderRecord">
                                   <g:remoteLink action="show" id="${registrationEventOrderRecordInstance.id}" update="showRegistrationEventOrderRecord">${fieldValue(bean:registrationEventOrderRecordInstance, field:'id')}</g:remoteLink>
                              </div>
                           </td>
                            
                        
                            <td>${fieldValue(bean:registrationEventOrderRecordInstance, field:'event')}</td>
                        
                            <td>${fieldValue(bean:registrationEventOrderRecordInstance, field:'order')}</td>
                        
                            <td>${fieldValue(bean:registrationEventOrderRecordInstance, field:'isActive')}</td>
                        
                            <td>${fieldValue(bean:registrationEventOrderRecordInstance, field:'comment')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${registrationEventOrderRecordInstanceTotal}" />
            </div>
        </div>
        <gui:dialog title="RegistrationEventOrderRecord" 
           modal="true" form="false"
           triggers="[show:[id:'showTheRegistrationEventOrderRecord', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="showRegistrationEventOrderRecord" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
        <gui:dialog title="RegistrationEventOrderRecord" 
           modal="true" form="false"
           triggers="[show:[id:'createTheRegistrationEventOrderRecord', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="createRegistrationEventOrderRecord" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
    </body>
</html>
