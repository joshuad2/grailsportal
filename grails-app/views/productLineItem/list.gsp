
<%@ page import="com.grailsPortal.domain.ProductLineItem" %>
<html>
    <head>
		<meta name="layout" content="main" />
		<gui:resources components="dialog" />
		<g:javascript library="prototype" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton" id="createTheProductLineItem">
                 <g:remoteLink action="create" update="createProductLineItem">New</g:remoteLink>
            </span>
        </div>
        <div class="body">
            <h1 style="margin-left:20px;">West Orlando Arts Foundation  - ProductLineItem </h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="showTheProductLineItem">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="dsc" title="Dsc" />
                        
                   	        <g:sortableColumn property="grossCost" title="Gross Cost" />
                        
                   	        <g:sortableColumn property="lineItemNumber" title="Line Item Number" />
                        
                   	        <g:sortableColumn property="isTaxable" title="Is Taxable" />
                        
                   	        <g:sortableColumn property="netCost" title="Net Cost" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${productLineItemInstanceList}" status="i" var="productLineItemInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>
                              <div id="linkProductLineItem">
                                   <g:remoteLink action="show" id="${productLineItemInstance.id}" update="showProductLineItem">${fieldValue(bean:productLineItemInstance, field:'id')}</g:remoteLink>
                              </div>
                           </td>
                            
                        
                            <td>${fieldValue(bean:productLineItemInstance, field:'dsc')}</td>
                        
                            <td>${fieldValue(bean:productLineItemInstance, field:'grossCost')}</td>
                        
                            <td>${fieldValue(bean:productLineItemInstance, field:'lineItemNumber')}</td>
                        
                            <td>${fieldValue(bean:productLineItemInstance, field:'isTaxable')}</td>
                        
                            <td>${fieldValue(bean:productLineItemInstance, field:'netCost')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${productLineItemInstanceTotal}" />
            </div>
        </div>
        <gui:dialog title="ProductLineItem" 
           modal="true" form="false"
           triggers="[show:[id:'showTheProductLineItem', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="showProductLineItem" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
        <gui:dialog title="ProductLineItem" 
           modal="true" form="false"
           triggers="[show:[id:'createTheProductLineItem', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="createProductLineItem" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
    </body>
</html>
