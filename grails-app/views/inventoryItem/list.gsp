
<%@ page import="com.grailsPortal.domain.InventoryItem" %>
<html>
    <head>
		<meta name="layout" content="main" />
		<gui:resources components="dialog" />
		<g:javascript library="prototype" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton" id="createTheInventoryItem">
                 <g:remoteLink action="create" update="createInventoryItem">New</g:remoteLink>
            </span>
        </div>
        <div class="body">
            <h1 style="margin-left:20px;">West Orlando Arts Foundation  - InventoryItem </h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="showTheInventoryItem">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="cd" title="Cd" />
                        
                   	        <g:sortableColumn property="contenturipath" title="Contenturipath" />
                        
                   	        <g:sortableColumn property="dsc" title="Dsc" />
                        
                   	        <g:sortableColumn property="imageuripath" title="Imageuripath" />
                        
                   	        <g:sortableColumn property="name" title="Name" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${inventoryItemInstanceList}" status="i" var="inventoryItemInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>
                              <div id="linkInventoryItem">
                                   <g:remoteLink action="show" id="${inventoryItemInstance.id}" update="showInventoryItem">${fieldValue(bean:inventoryItemInstance, field:'id')}</g:remoteLink>
                              </div>
                           </td>
                            
                        
                            <td>${fieldValue(bean:inventoryItemInstance, field:'cd')}</td>
                        
                            <td>${fieldValue(bean:inventoryItemInstance, field:'contenturipath')}</td>
                        
                            <td>${fieldValue(bean:inventoryItemInstance, field:'dsc')}</td>
                        
                            <td>${fieldValue(bean:inventoryItemInstance, field:'imageuripath')}</td>
                        
                            <td>${fieldValue(bean:inventoryItemInstance, field:'name')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${inventoryItemInstanceTotal}" />
            </div>
        </div>
        <gui:dialog title="InventoryItem" 
           modal="true" form="false"
           triggers="[show:[id:'showTheInventoryItem', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="showInventoryItem" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
        <gui:dialog title="InventoryItem" 
           modal="true" form="false"
           triggers="[show:[id:'createTheInventoryItem', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="createInventoryItem" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
    </body>
</html>
