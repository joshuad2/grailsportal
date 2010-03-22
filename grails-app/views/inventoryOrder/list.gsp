
<%@ page import="com.grailsPortal.domain.InventoryOrder" %>
<html>
    <head>
		<meta name="layout" content="main" />
		<gui:resources components="dialog" />
		<g:javascript library="prototype" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton" id="createTheInventoryOrder">
                 <g:remoteLink action="create" update="createInventoryOrder">New</g:remoteLink>
            </span>
        </div>
        <div class="body">
            <h1 style="margin-left:20px;">West Orlando Arts Foundation  - InventoryOrder </h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="showTheInventoryOrder">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="orderNumber" title="Order Number" />
                        
                   	        <g:sortableColumn property="expectedArrivalDate" title="Expected Arrival Date" />
                        
                   	        <g:sortableColumn property="orderComment" title="Order Comment" />
                        
                   	        <g:sortableColumn property="shipDate" title="Ship Date" />
                        
                   	        <g:sortableColumn property="shippingComment" title="Shipping Comment" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${inventoryOrderInstanceList}" status="i" var="inventoryOrderInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>
                              <div id="linkInventoryOrder">
                                   <g:remoteLink action="show" id="${inventoryOrderInstance.id}" update="showInventoryOrder">${fieldValue(bean:inventoryOrderInstance, field:'id')}</g:remoteLink>
                              </div>
                           </td>
                            
                        
                            <td>${fieldValue(bean:inventoryOrderInstance, field:'orderNumber')}</td>
                        
                            <td>${fieldValue(bean:inventoryOrderInstance, field:'expectedArrivalDate')}</td>
                        
                            <td>${fieldValue(bean:inventoryOrderInstance, field:'orderComment')}</td>
                        
                            <td>${fieldValue(bean:inventoryOrderInstance, field:'shipDate')}</td>
                        
                            <td>${fieldValue(bean:inventoryOrderInstance, field:'shippingComment')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${inventoryOrderInstanceTotal}" />
            </div>
        </div>
        <gui:dialog title="InventoryOrder" 
           modal="true" form="false"
           triggers="[show:[id:'showTheInventoryOrder', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="showInventoryOrder" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
        <gui:dialog title="InventoryOrder" 
           modal="true" form="false"
           triggers="[show:[id:'createTheInventoryOrder', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="createInventoryOrder" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
    </body>
</html>
