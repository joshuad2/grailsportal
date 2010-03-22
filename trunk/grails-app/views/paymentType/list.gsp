
<%@ page import="com.grailsPortal.domain.PaymentType" %>
<html>
    <head>
		<meta name="layout" content="main" />
		<gui:resources components="dialog" />
		<g:javascript library="prototype" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton" id="createThePaymentType">
                 <g:remoteLink action="create" update="createPaymentType">New</g:remoteLink>
            </span>
        </div>
        <div class="body">
            <h1 style="margin-left:20px;">West Orlando Arts Foundation  - PaymentType </h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="showThePaymentType">
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
                    <g:each in="${paymentTypeInstanceList}" status="i" var="paymentTypeInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>
                              <div id="linkPaymentType">
                                   <g:remoteLink action="show" id="${paymentTypeInstance.id}" update="showPaymentType">${fieldValue(bean:paymentTypeInstance, field:'id')}</g:remoteLink>
                              </div>
                           </td>
                            
                        
                            <td>${fieldValue(bean:paymentTypeInstance, field:'cd')}</td>
                        
                            <td>${fieldValue(bean:paymentTypeInstance, field:'dsc')}</td>
                        
                            <td>${fieldValue(bean:paymentTypeInstance, field:'name')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${paymentTypeInstanceTotal}" />
            </div>
        </div>
        <gui:dialog title="PaymentType" 
           modal="true" form="false"
           triggers="[show:[id:'showThePaymentType', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="showPaymentType" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
        <gui:dialog title="PaymentType" 
           modal="true" form="false"
           triggers="[show:[id:'createThePaymentType', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="createPaymentType" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
    </body>
</html>
