
<%@ page import="com.grailsPortal.domain.OrderRecordLineItem" %>
<html>
    <head>
		<meta name="layout" content="main" />
		<gui:resources components="dialog" />
		<g:javascript library="prototype" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton" id="createTheOrderRecordLineItem">
                 <g:remoteLink action="create" update="createOrderRecordLineItem">New</g:remoteLink>
            </span>
        </div>
        <div class="body">
            <h1 style="margin-left:20px;">West Orlando Arts Foundation  - OrderRecordLineItem </h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="showTheOrderRecordLineItem">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="amountPurchased" title="Amount Purchased" />
                        
                   	        <g:sortableColumn property="lineItemAmount" title="Line Item Amount" />
                        
                   	        <g:sortableColumn property="lineItemNumber" title="Line Item Number" />
                        
                   	        <th>Product</th>
                   	    
                   	        <th>Order Record</th>
                   	    
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${orderRecordLineItemInstanceList}" status="i" var="orderRecordLineItemInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>
                              <div id="linkOrderRecordLineItem">
                                   <g:remoteLink action="show" id="${orderRecordLineItemInstance.id}" update="showOrderRecordLineItem">${fieldValue(bean:orderRecordLineItemInstance, field:'id')}</g:remoteLink>
                              </div>
                           </td>
                            
                        
                            <td>${fieldValue(bean:orderRecordLineItemInstance, field:'amountPurchased')}</td>
                        
                            <td>${fieldValue(bean:orderRecordLineItemInstance, field:'lineItemAmount')}</td>
                        
                            <td>${fieldValue(bean:orderRecordLineItemInstance, field:'lineItemNumber')}</td>
                        
                            <td>${fieldValue(bean:orderRecordLineItemInstance, field:'product')}</td>
                        
                            <td>${fieldValue(bean:orderRecordLineItemInstance, field:'orderRecord')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${orderRecordLineItemInstanceTotal}" />
            </div>
        </div>
        <gui:dialog title="OrderRecordLineItem" 
           modal="true" form="false"
           triggers="[show:[id:'showTheOrderRecordLineItem', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="showOrderRecordLineItem" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
        <gui:dialog title="OrderRecordLineItem" 
           modal="true" form="false"
           triggers="[show:[id:'createTheOrderRecordLineItem', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="createOrderRecordLineItem" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
    </body>
</html>
