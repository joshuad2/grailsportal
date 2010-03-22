
<%@ page import="com.grailsPortal.domain.PaymentMethodType" %>
<html>
    <head>
		<meta name="layout" content="main" />
		<gui:resources components="dialog" />
		<g:javascript library="prototype" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton" id="createThePaymentMethodType">
                 <g:remoteLink action="create" update="createPaymentMethodType">New</g:remoteLink>
            </span>
        </div>
        <div class="body">
            <h1 style="margin-left:20px;">West Orlando Arts Foundation  - PaymentMethodType </h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="showThePaymentMethodType">
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
                    <g:each in="${paymentMethodTypeInstanceList}" status="i" var="paymentMethodTypeInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>
                              <div id="linkPaymentMethodType">
                                   <g:remoteLink action="show" id="${paymentMethodTypeInstance.id}" update="showPaymentMethodType">${fieldValue(bean:paymentMethodTypeInstance, field:'id')}</g:remoteLink>
                              </div>
                           </td>
                            
                        
                            <td>${fieldValue(bean:paymentMethodTypeInstance, field:'cd')}</td>
                        
                            <td>${fieldValue(bean:paymentMethodTypeInstance, field:'dsc')}</td>
                        
                            <td>${fieldValue(bean:paymentMethodTypeInstance, field:'name')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${paymentMethodTypeInstanceTotal}" />
            </div>
        </div>
        <gui:dialog title="PaymentMethodType" 
           modal="true" form="false"
           triggers="[show:[id:'showThePaymentMethodType', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="showPaymentMethodType" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
        <gui:dialog title="PaymentMethodType" 
           modal="true" form="false"
           triggers="[show:[id:'createThePaymentMethodType', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="createPaymentMethodType" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
    </body>
</html>
