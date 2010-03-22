
<%@ page import="com.grailsPortal.domain.PartyContactAddress" %>
<html>
    <head>
		<meta name="layout" content="main" />
		<gui:resources components="dialog" />
		<g:javascript library="prototype" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton" id="createThePartyContactAddress">
                 <g:remoteLink action="create" update="createPartyContactAddress">New</g:remoteLink>
            </span>
        </div>
        <div class="body">
            <h1 style="margin-left:20px;">West Orlando Arts Foundation  - PartyContactAddress </h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="showThePartyContactAddress">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <th>Party</th>
                   	    
                   	        <th>Address</th>
                   	    
                   	        <th>Contact Type</th>
                   	    
                   	        <g:sortableColumn property="active" title="Active" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${partyContactAddressInstanceList}" status="i" var="partyContactAddressInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>
                              <div id="linkPartyContactAddress">
                                   <g:remoteLink action="show" id="${partyContactAddressInstance.id}" update="showPartyContactAddress">${fieldValue(bean:partyContactAddressInstance, field:'id')}</g:remoteLink>
                              </div>
                           </td>
                            
                        
                            <td>${fieldValue(bean:partyContactAddressInstance, field:'party')}</td>
                        
                            <td>${fieldValue(bean:partyContactAddressInstance, field:'address')}</td>
                        
                            <td>${fieldValue(bean:partyContactAddressInstance, field:'contactType')}</td>
                        
                            <td>${fieldValue(bean:partyContactAddressInstance, field:'active')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${partyContactAddressInstanceTotal}" />
            </div>
        </div>
        <gui:dialog title="PartyContactAddress" 
           modal="true" form="false"
           triggers="[show:[id:'showThePartyContactAddress', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="showPartyContactAddress" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
        <gui:dialog title="PartyContactAddress" 
           modal="true" form="false"
           triggers="[show:[id:'createThePartyContactAddress', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="createPartyContactAddress" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
    </body>
</html>
