
<%@ page import="com.grailsPortal.domain.ContactAddress" %>
<html>
    <head>
		<meta name="layout" content="main" />
		<gui:resources components="dialog" />
		<g:javascript library="prototype" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton" id="createTheContactAddress">
                 <g:remoteLink action="create" update="createContactAddress">New</g:remoteLink>
            </span>
        </div>
        <div class="body">
            <h1 style="margin-left:20px;">West Orlando Arts Foundation  - ContactAddress </h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="showTheContactAddress">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="address1" title="Address1" />
                        
                   	        <g:sortableColumn property="address2" title="Address2" />
                        
                   	        <g:sortableColumn property="address3" title="Address3" />
                        
                   	        <g:sortableColumn property="city" title="City" />
                        
                   	        <th>State</th>
                   	    
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${contactAddressInstanceList}" status="i" var="contactAddressInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>
                              <div id="linkContactAddress">
                                   <g:remoteLink action="show" id="${contactAddressInstance.id}" update="showContactAddress">${fieldValue(bean:contactAddressInstance, field:'id')}</g:remoteLink>
                              </div>
                           </td>
                            
                        
                            <td>${fieldValue(bean:contactAddressInstance, field:'address1')}</td>
                        
                            <td>${fieldValue(bean:contactAddressInstance, field:'address2')}</td>
                        
                            <td>${fieldValue(bean:contactAddressInstance, field:'address3')}</td>
                        
                            <td>${fieldValue(bean:contactAddressInstance, field:'city')}</td>
                        
                            <td>${fieldValue(bean:contactAddressInstance, field:'state')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${contactAddressInstanceTotal}" />
            </div>
        </div>
        <gui:dialog title="ContactAddress" 
           modal="true" form="false"
           triggers="[show:[id:'showTheContactAddress', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="showContactAddress" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
        <gui:dialog title="ContactAddress" 
           modal="true" form="false"
           triggers="[show:[id:'createTheContactAddress', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="createContactAddress" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
    </body>
</html>
