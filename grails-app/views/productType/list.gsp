
<%@ page import="com.grailsPortal.domain.ProductType" %>
<html>
    <head>
		<meta name="layout" content="main" />
		<gui:resources components="dialog" />
		<g:javascript library="prototype" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton" id="createTheProductType">
                 <g:remoteLink action="create" update="createProductType">New</g:remoteLink>
            </span>
        </div>
        <div class="body">
            <h1 style="margin-left:20px;">West Orlando Arts Foundation  - ProductType </h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="showTheProductType">
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
                    <g:each in="${productTypeInstanceList}" status="i" var="productTypeInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>
                              <div id="linkProductType">
                                   <g:remoteLink action="show" id="${productTypeInstance.id}" update="showProductType">${fieldValue(bean:productTypeInstance, field:'id')}</g:remoteLink>
                              </div>
                           </td>
                            
                        
                            <td>${fieldValue(bean:productTypeInstance, field:'cd')}</td>
                        
                            <td>${fieldValue(bean:productTypeInstance, field:'dsc')}</td>
                        
                            <td>${fieldValue(bean:productTypeInstance, field:'name')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${productTypeInstanceTotal}" />
            </div>
        </div>
        <gui:dialog title="ProductType" 
           modal="true" form="false"
           triggers="[show:[id:'showTheProductType', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="showProductType" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
        <gui:dialog title="ProductType" 
           modal="true" form="false"
           triggers="[show:[id:'createTheProductType', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="createProductType" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
    </body>
</html>
