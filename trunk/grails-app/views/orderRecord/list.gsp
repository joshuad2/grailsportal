
<%@ page import="com.grailsPortal.domain.OrderRecord" %>
<html>
    <head>
		<meta name="layout" content="main" />
		<gui:resources components="dialog" />
		<g:javascript library="prototype" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton" id="createTheOrderRecord">
                 <g:remoteLink action="create" update="createOrderRecord">New</g:remoteLink>
            </span>
        </div>
        <div class="body">
            <h1 style="margin-left:20px;">West Orlando Arts Foundation  - OrderRecord </h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="showTheOrderRecord">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="orderNumber" title="Order Number" />
                        
                   	        <g:sortableColumn property="grossAmount" title="Gross Amount" />
                        
                   	        <th>Order Record Type</th>
                   	    
                   	        <th>Order Status</th>
                   	    
                   	        <th>Party</th>
                   	    
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${orderRecordInstanceList}" status="i" var="orderRecordInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>
                              <div id="linkOrderRecord">
                                   <g:remoteLink action="show" id="${orderRecordInstance.id}" update="showOrderRecord">${fieldValue(bean:orderRecordInstance, field:'id')}</g:remoteLink>
                              </div>
                           </td>
                            
                        
                            <td>${fieldValue(bean:orderRecordInstance, field:'orderNumber')}</td>
                        
                            <td>${fieldValue(bean:orderRecordInstance, field:'grossAmount')}</td>
                        
                            <td>${fieldValue(bean:orderRecordInstance, field:'orderRecordType')}</td>
                        
                            <td>${fieldValue(bean:orderRecordInstance, field:'orderStatus')}</td>
                        
                            <td>${fieldValue(bean:orderRecordInstance, field:'party')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${orderRecordInstanceTotal}" />
            </div>
        </div>
        <gui:dialog title="OrderRecord" 
           modal="true" form="false"
           triggers="[show:[id:'showTheOrderRecord', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="showOrderRecord" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
        <gui:dialog title="OrderRecord" 
           modal="true" form="false"
           triggers="[show:[id:'createTheOrderRecord', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="createOrderRecord" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
    </body>
</html>
