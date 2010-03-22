
<%@ page import="com.grailsPortal.domain.PartyRole" %>
<html>
    <head>
		<meta name="layout" content="main" />
		<gui:resources components="dialog" />
		<g:javascript library="prototype" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton" id="createThePartyRole">
                 <g:remoteLink action="create" update="createPartyRole">New</g:remoteLink>
            </span>
        </div>
        <div class="body">
            <h1 style="margin-left:20px;">West Orlando Arts Foundation  - PartyRole </h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="showThePartyRole">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="name" title="Name" />
                        
                   	        <g:sortableColumn property="cd" title="Cd" />
                        
                   	        <g:sortableColumn property="dsc" title="Dsc" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${partyRoleInstanceList}" status="i" var="partyRoleInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>
                              <div id="linkPartyRole">
                                   <g:remoteLink action="show" id="${partyRoleInstance.id}" update="showPartyRole">${fieldValue(bean:partyRoleInstance, field:'id')}</g:remoteLink>
                              </div>
                           </td>
                            
                        
                            <td>${fieldValue(bean:partyRoleInstance, field:'name')}</td>
                        
                            <td>${fieldValue(bean:partyRoleInstance, field:'cd')}</td>
                        
                            <td>${fieldValue(bean:partyRoleInstance, field:'dsc')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${partyRoleInstanceTotal}" />
            </div>
        </div>
        <gui:dialog title="PartyRole" 
           modal="true" form="false"
           triggers="[show:[id:'showThePartyRole', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="showPartyRole" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
        <gui:dialog title="PartyRole" 
           modal="true" form="false"
           triggers="[show:[id:'createThePartyRole', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="createPartyRole" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
    </body>
</html>
