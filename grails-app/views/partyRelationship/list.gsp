
<%@ page import="com.grailsPortal.domain.PartyRelationship" %>
<html>
    <head>
		<meta name="layout" content="main" />
		<gui:resources components="dialog" />
		<g:javascript library="prototype" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton" id="createThePartyRelationship">
                 <g:remoteLink action="create" update="createPartyRelationship">New</g:remoteLink>
            </span>
        </div>
        <div class="body">
            <h1 style="margin-left:20px;">West Orlando Arts Foundation  - PartyRelationship </h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="showThePartyRelationship">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <th>Child</th>
                   	    
                   	        <th>Relationship Type</th>
                   	    
                   	        <th>Parent</th>
                   	    
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${partyRelationshipInstanceList}" status="i" var="partyRelationshipInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>
                              <div id="linkPartyRelationship">
                                   <g:remoteLink action="show" id="${partyRelationshipInstance.id}" update="showPartyRelationship">${fieldValue(bean:partyRelationshipInstance, field:'id')}</g:remoteLink>
                              </div>
                           </td>
                            
                        
                            <td>${fieldValue(bean:partyRelationshipInstance, field:'child')}</td>
                        
                            <td>${fieldValue(bean:partyRelationshipInstance, field:'relationshipType')}</td>
                        
                            <td>${fieldValue(bean:partyRelationshipInstance, field:'parent')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${partyRelationshipInstanceTotal}" />
            </div>
        </div>
        <gui:dialog title="PartyRelationship" 
           modal="true" form="false"
           triggers="[show:[id:'showThePartyRelationship', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="showPartyRelationship" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
        <gui:dialog title="PartyRelationship" 
           modal="true" form="false"
           triggers="[show:[id:'createThePartyRelationship', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="createPartyRelationship" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
    </body>
</html>
