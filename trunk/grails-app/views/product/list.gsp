
<%@ page import="com.grailsPortal.domain.Product" %>
<html>
    <head>
		<meta name="layout" content="main" />
		<gui:resources components="dialog" />
		<g:javascript library="prototype" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton" id="createTheProduct">
                 <g:remoteLink action="create" update="createProduct">New</g:remoteLink>
            </span>
        </div>
        <div class="body">
            <h1 style="margin-left:20px;">West Orlando Arts Foundation  - Product </h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="showTheProduct">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="cd" title="Cd" />
                        
                   	        <g:sortableColumn property="dsc" title="Dsc" />
                        
                   	        <g:sortableColumn property="ecommerceCode" title="Ecommerce Code" />
                        
                   	        <g:sortableColumn property="name" title="Name" />
                        
                   	        <g:sortableColumn property="netCostAmount" title="Net Cost Amount" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${productInstanceList}" status="i" var="productInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>
                              <div id="linkProduct">
                                   <g:remoteLink action="show" id="${productInstance.id}" update="showProduct">${fieldValue(bean:productInstance, field:'id')}</g:remoteLink>
                              </div>
                           </td>
                            
                        
                            <td>${fieldValue(bean:productInstance, field:'cd')}</td>
                        
                            <td>${fieldValue(bean:productInstance, field:'dsc')}</td>
                        
                            <td>${fieldValue(bean:productInstance, field:'ecommerceCode')}</td>
                        
                            <td>${fieldValue(bean:productInstance, field:'name')}</td>
                        
                            <td>${fieldValue(bean:productInstance, field:'netCostAmount')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${productInstanceTotal}" />
            </div>
        </div>
        <gui:dialog title="Product" 
           modal="true" form="false"
           triggers="[show:[id:'showTheProduct', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="showProduct" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
        <gui:dialog title="Product" 
           modal="true" form="false"
           triggers="[show:[id:'createTheProduct', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="createProduct" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
    </body>
</html>
