
<%@ page import="com.grailsPortal.domain.SalesChannelType" %>
<html>
    <head>
		<meta name="layout" content="main" />
		<gui:resources components="dialog" />
		<g:javascript library="prototype" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton" id="createTheSalesChannelType">
                 <g:remoteLink action="create" update="createSalesChannelType">New</g:remoteLink>
            </span>
        </div>
        <div class="body">
            <h1 style="margin-left:20px;">West Orlando Arts Foundation  - SalesChannelType </h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="showTheSalesChannelType">
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
                    <g:each in="${salesChannelTypeInstanceList}" status="i" var="salesChannelTypeInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>
                              <div id="linkSalesChannelType">
                                   <g:remoteLink action="show" id="${salesChannelTypeInstance.id}" update="showSalesChannelType">${fieldValue(bean:salesChannelTypeInstance, field:'id')}</g:remoteLink>
                              </div>
                           </td>
                            
                        
                            <td>${fieldValue(bean:salesChannelTypeInstance, field:'cd')}</td>
                        
                            <td>${fieldValue(bean:salesChannelTypeInstance, field:'dsc')}</td>
                        
                            <td>${fieldValue(bean:salesChannelTypeInstance, field:'name')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${salesChannelTypeInstanceTotal}" />
            </div>
        </div>
        <gui:dialog title="SalesChannelType" 
           modal="true" form="false"
           triggers="[show:[id:'showTheSalesChannelType', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="showSalesChannelType" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
        <gui:dialog title="SalesChannelType" 
           modal="true" form="false"
           triggers="[show:[id:'createTheSalesChannelType', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="createSalesChannelType" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
    </body>
</html>
