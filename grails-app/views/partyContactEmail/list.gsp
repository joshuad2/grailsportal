
<%@ page import="com.grailsPortal.domain.PartyContactEmail" %>
<html>
    <head>
		<meta name="layout" content="main" />
		<gui:resources components="dialog" />
		<g:javascript library="prototype" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton" id="createThePartyContactEmail">
                 <g:remoteLink action="create" update="createPartyContactEmail">New</g:remoteLink>
            </span>
        </div>
        <div class="body">
            <h1 style="margin-left:20px;">West Orlando Arts Foundation  - PartyContactEmail </h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="showThePartyContactEmail">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <th>Party</th>
                   	    
                   	        <th>Email</th>
                   	    
                   	        <th>Contact Type</th>
                   	    
                   	        <g:sortableColumn property="active" title="Active" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${partyContactEmailInstanceList}" status="i" var="partyContactEmailInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>
                              <div id="linkPartyContactEmail">
                                   <g:remoteLink action="show" id="${partyContactEmailInstance.id}" update="showPartyContactEmail">${fieldValue(bean:partyContactEmailInstance, field:'id')}</g:remoteLink>
                              </div>
                           </td>
                            
                        
                            <td>${fieldValue(bean:partyContactEmailInstance, field:'party')}</td>
                        
                            <td>${fieldValue(bean:partyContactEmailInstance, field:'email')}</td>
                        
                            <td>${fieldValue(bean:partyContactEmailInstance, field:'contactType')}</td>
                        
                            <td>${fieldValue(bean:partyContactEmailInstance, field:'active')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${partyContactEmailInstanceTotal}" />
            </div>
        </div>
        <gui:dialog title="PartyContactEmail" 
           modal="true" form="false"
           triggers="[show:[id:'showThePartyContactEmail', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="showPartyContactEmail" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
        <gui:dialog title="PartyContactEmail" 
           modal="true" form="false"
           triggers="[show:[id:'createThePartyContactEmail', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="createPartyContactEmail" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
    </body>
</html>
