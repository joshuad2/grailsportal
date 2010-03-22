
<%@ page import="com.grailsPortal.domain.PartyType" %>
<html>
    <head>
		<meta name="layout" content="main" />
		<gui:resources components="dialog" />
		<g:javascript library="prototype" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton" id="createThePartyType">
                 <g:remoteLink action="create" update="createPartyType">New</g:remoteLink>
            </span>
        </div>
        <div class="body">
            <h1 style="margin-left:20px;">West Orlando Arts Foundation  - PartyType </h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="showThePartyType">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="cd" title="Cd" />
                        
                   	        <g:sortableColumn property="dsc" title="Dsc" />
                        
                   	        <g:sortableColumn property="name" title="Name" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${partyTypeInstanceList}" status="i" var="partyTypeInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>
                              <div id="linkPartyType">
                                   <g:remoteLink action="show" id="${partyTypeInstance.id}" update="showPartyType">${fieldValue(bean:partyTypeInstance, field:'id')}</g:remoteLink>
                              </div>
                           </td>
                            
                        
                            <td>${fieldValue(bean:partyTypeInstance, field:'cd')}</td>
                        
                            <td>${fieldValue(bean:partyTypeInstance, field:'dsc')}</td>
                        
                            <td>${fieldValue(bean:partyTypeInstance, field:'name')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${partyTypeInstanceTotal}" />
            </div>
        </div>
        <gui:dialog title="PartyType" 
           modal="true" form="false"
           triggers="[show:[id:'showThePartyType', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="showPartyType" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
        <gui:dialog title="PartyType" 
           modal="true" form="false"
           triggers="[show:[id:'createThePartyType', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="createPartyType" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
    </body>
</html>
