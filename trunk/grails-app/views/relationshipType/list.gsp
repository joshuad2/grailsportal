
<%@ page import="com.grailsPortal.domain.RelationshipType" %>
<html>
    <head>
		<meta name="layout" content="main" />
		<gui:resources components="dialog" />
		<g:javascript library="prototype" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton" id="createTheRelationshipType">
                 <g:remoteLink action="create" update="createRelationshipType">New</g:remoteLink>
            </span>
        </div>
        <div class="body">
            <h1 style="margin-left:20px;">West Orlando Arts Foundation  - RelationshipType </h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="showTheRelationshipType">
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
                    <g:each in="${relationshipTypeInstanceList}" status="i" var="relationshipTypeInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>
                              <div id="linkRelationshipType">
                                   <g:remoteLink action="show" id="${relationshipTypeInstance.id}" update="showRelationshipType">${fieldValue(bean:relationshipTypeInstance, field:'id')}</g:remoteLink>
                              </div>
                           </td>
                            
                        
                            <td>${fieldValue(bean:relationshipTypeInstance, field:'cd')}</td>
                        
                            <td>${fieldValue(bean:relationshipTypeInstance, field:'dsc')}</td>
                        
                            <td>${fieldValue(bean:relationshipTypeInstance, field:'name')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${relationshipTypeInstanceTotal}" />
            </div>
        </div>
        <gui:dialog title="RelationshipType" 
           modal="true" form="false"
           triggers="[show:[id:'showTheRelationshipType', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="showRelationshipType" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
        <gui:dialog title="RelationshipType" 
           modal="true" form="false"
           triggers="[show:[id:'createTheRelationshipType', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="createRelationshipType" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
    </body>
</html>
