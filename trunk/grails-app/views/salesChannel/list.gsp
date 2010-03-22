
<%@ page import="com.grailsPortal.domain.SalesChannel" %>
<html>
    <head>
		<meta name="layout" content="main" />
		<gui:resources components="dialog" />
		<g:javascript library="prototype" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton" id="createTheSalesChannel">
                 <g:remoteLink action="create" update="createSalesChannel">New</g:remoteLink>
            </span>
        </div>
        <div class="body">
            <h1 style="margin-left:20px;">West Orlando Arts Foundation  - SalesChannel </h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="showTheSalesChannel">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="cd" title="Cd" />
                        
                   	        <g:sortableColumn property="dsc" title="Dsc" />
                        
                   	        <g:sortableColumn property="name" title="Name" />
                        
                   	        <th>Sales Channel Type</th>
                   	    
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${salesChannelInstanceList}" status="i" var="salesChannelInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>
                              <div id="linkSalesChannel">
                                   <g:remoteLink action="show" id="${salesChannelInstance.id}" update="showSalesChannel">${fieldValue(bean:salesChannelInstance, field:'id')}</g:remoteLink>
                              </div>
                           </td>
                            
                        
                            <td>${fieldValue(bean:salesChannelInstance, field:'cd')}</td>
                        
                            <td>${fieldValue(bean:salesChannelInstance, field:'dsc')}</td>
                        
                            <td>${fieldValue(bean:salesChannelInstance, field:'name')}</td>
                        
                            <td>${fieldValue(bean:salesChannelInstance, field:'salesChannelType')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${salesChannelInstanceTotal}" />
            </div>
        </div>
        <gui:dialog title="SalesChannel" 
           modal="true" form="false"
           triggers="[show:[id:'showTheSalesChannel', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="showSalesChannel" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
        <gui:dialog title="SalesChannel" 
           modal="true" form="false"
           triggers="[show:[id:'createTheSalesChannel', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="createSalesChannel" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
    </body>
</html>
