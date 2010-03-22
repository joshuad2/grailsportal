
<%@ page import="com.grailsPortal.domain.Party" %>
<html>
    <head>
		<meta name="layout" content="main" />
		<gui:resources components="dialog" />
		<g:javascript library="prototype" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton" id="createTheParty">
                 <g:remoteLink action="create" update="createParty">New</g:remoteLink>
            </span>
        </div>
        <div class="body">
            <h1 style="margin-left:20px;">West Orlando Arts Foundation  - Party </h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="showTheParty">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="prefix" title="Prefix" />
                        
                   	        <g:sortableColumn property="firstName" title="First Name" />
                        
                   	        <g:sortableColumn property="middleName" title="Middle Name" />
                        
                   	        <g:sortableColumn property="lastName" title="Last Name" />
                        
                   	        <g:sortableColumn property="suffix" title="Suffix" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${partyInstanceList}" status="i" var="partyInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>
                              <div id="linkParty">
                                   <g:remoteLink action="show" id="${partyInstance.id}" update="showParty">${fieldValue(bean:partyInstance, field:'id')}</g:remoteLink>
                              </div>
                           </td>
                            
                        
                            <td>${fieldValue(bean:partyInstance, field:'prefix')}</td>
                        
                            <td>${fieldValue(bean:partyInstance, field:'firstName')}</td>
                        
                            <td>${fieldValue(bean:partyInstance, field:'middleName')}</td>
                        
                            <td>${fieldValue(bean:partyInstance, field:'lastName')}</td>
                        
                            <td>${fieldValue(bean:partyInstance, field:'suffix')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${partyInstanceTotal}" />
            </div>
        </div>
        <gui:dialog title="Party" 
           modal="true" form="false"
           triggers="[show:[id:'showTheParty', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="showParty" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
        <gui:dialog title="Party" 
           modal="true" form="false"
           triggers="[show:[id:'createTheParty', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="createParty" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
    </body>
</html>
