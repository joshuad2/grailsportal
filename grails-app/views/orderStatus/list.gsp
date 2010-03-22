
<%@ page import="com.grailsPortal.domain.OrderStatus" %>
<html>
    <head>
		<meta name="layout" content="main" />
		<gui:resources components="dialog" />
		<g:javascript library="prototype" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton" id="createTheOrderStatus">
                 <g:remoteLink action="create" update="createOrderStatus">New</g:remoteLink>
            </span>
        </div>
        <div class="body">
            <h1 style="margin-left:20px;">West Orlando Arts Foundation  - OrderStatus </h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="showTheOrderStatus">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="name" title="Name" />
                        
                   	        <g:sortableColumn property="cd" title="Cd" />
                        
                   	        <g:sortableColumn property="dsc" title="Dsc" />
                        
                   	        <g:sortableColumn property="active" title="Active" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${orderStatusInstanceList}" status="i" var="orderStatusInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>
                              <div id="linkOrderStatus">
                                   <g:remoteLink action="show" id="${orderStatusInstance.id}" update="showOrderStatus">${fieldValue(bean:orderStatusInstance, field:'id')}</g:remoteLink>
                              </div>
                           </td>
                            
                        
                            <td>${fieldValue(bean:orderStatusInstance, field:'name')}</td>
                        
                            <td>${fieldValue(bean:orderStatusInstance, field:'cd')}</td>
                        
                            <td>${fieldValue(bean:orderStatusInstance, field:'dsc')}</td>
                        
                            <td>${fieldValue(bean:orderStatusInstance, field:'active')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${orderStatusInstanceTotal}" />
            </div>
        </div>
        <gui:dialog title="OrderStatus" 
           modal="true" form="false"
           triggers="[show:[id:'showTheOrderStatus', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="showOrderStatus" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
        <gui:dialog title="OrderStatus" 
           modal="true" form="false"
           triggers="[show:[id:'createTheOrderStatus', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="createOrderStatus" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
    </body>
</html>
