
<%@ page import="com.grailsPortal.domain.Payment" %>
<html>
    <head>
		<meta name="layout" content="main" />
		<gui:resources components="dialog" />
		<g:javascript library="prototype" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton" id="createThePayment">
                 <g:remoteLink action="create" update="createPayment">New</g:remoteLink>
            </span>
        </div>
        <div class="body">
            <h1 style="margin-left:20px;">West Orlando Arts Foundation  - Payment </h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="showThePayment">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="displayOrder" title="Display Order" />
                        
                   	        <g:sortableColumn property="paymentAmount" title="Payment Amount" />
                        
                   	        <g:sortableColumn property="paymentDate" title="Payment Date" />
                        
                   	        <th>Subscription</th>
                   	    
                   	        <th>Party</th>
                   	    
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${paymentInstanceList}" status="i" var="paymentInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>
                              <div id="linkPayment">
                                   <g:remoteLink action="show" id="${paymentInstance.id}" update="showPayment">${fieldValue(bean:paymentInstance, field:'id')}</g:remoteLink>
                              </div>
                           </td>
                            
                        
                            <td>${fieldValue(bean:paymentInstance, field:'displayOrder')}</td>
                        
                            <td>${fieldValue(bean:paymentInstance, field:'paymentAmount')}</td>
                        
                            <td>${fieldValue(bean:paymentInstance, field:'paymentDate')}</td>
                        
                            <td>${fieldValue(bean:paymentInstance, field:'subscription')}</td>
                        
                            <td>${fieldValue(bean:paymentInstance, field:'party')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${paymentInstanceTotal}" />
            </div>
        </div>
        <gui:dialog title="Payment" 
           modal="true" form="false"
           triggers="[show:[id:'showThePayment', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="showPayment" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
        <gui:dialog title="Payment" 
           modal="true" form="false"
           triggers="[show:[id:'createThePayment', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="createPayment" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
    </body>
</html>
