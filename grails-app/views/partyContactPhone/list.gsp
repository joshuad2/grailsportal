
<%@ page import="com.grailsPortal.domain.PartyContactPhone" %>
<html>
    <head>
		<meta name="layout" content="main" />
		<gui:resources components="dialog" />
		<g:javascript library="prototype" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton" id="createThePartyContactPhone">
                 <g:remoteLink action="create" update="createPartyContactPhone">New</g:remoteLink>
            </span>
        </div>
        <div class="body">
            <h1 style="margin-left:20px;">West Orlando Arts Foundation  - PartyContactPhone </h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="showThePartyContactPhone">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <th>Party</th>
                   	    
                   	        <th>Phone</th>
                   	    
                   	        <th>Contact Type</th>
                   	    
                   	        <g:sortableColumn property="active" title="Active" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${partyContactPhoneInstanceList}" status="i" var="partyContactPhoneInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>
                              <div id="linkPartyContactPhone">
                                   <g:remoteLink action="show" id="${partyContactPhoneInstance.id}" update="showPartyContactPhone">${fieldValue(bean:partyContactPhoneInstance, field:'id')}</g:remoteLink>
                              </div>
                           </td>
                            
                        
                            <td>${fieldValue(bean:partyContactPhoneInstance, field:'party')}</td>
                        
                            <td>${fieldValue(bean:partyContactPhoneInstance, field:'phone')}</td>
                        
                            <td>${fieldValue(bean:partyContactPhoneInstance, field:'contactType')}</td>
                        
                            <td>${fieldValue(bean:partyContactPhoneInstance, field:'active')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${partyContactPhoneInstanceTotal}" />
            </div>
        </div>
        <gui:dialog title="PartyContactPhone" 
           modal="true" form="false"
           triggers="[show:[id:'showThePartyContactPhone', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="showPartyContactPhone" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
        <gui:dialog title="PartyContactPhone" 
           modal="true" form="false"
           triggers="[show:[id:'createThePartyContactPhone', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="createPartyContactPhone" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
    </body>
</html>
