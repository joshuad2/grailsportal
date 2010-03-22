
<%@ page import="com.grailsPortal.domain.PortalConfigBusinessProcess" %>
<html>
    <head>
		<meta name="layout" content="main" />
		<gui:resources components="dialog" />
		<g:javascript library="prototype" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton" id="createThePortalConfigBusinessProcess">
                 <g:remoteLink action="create" update="createPortalConfigBusinessProcess">New</g:remoteLink>
            </span>
        </div>
        <div class="body">
            <h1 style="margin-left:20px;">West Orlando Arts Foundation  - PortalConfigBusinessProcess </h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="showThePortalConfigBusinessProcess">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <th>Portal Config</th>
                   	    
                   	        <g:sortableColumn property="name" title="Name" />
                        
                   	        <g:sortableColumn property="purpose" title="Purpose" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${portalConfigBusinessProcessInstanceList}" status="i" var="portalConfigBusinessProcessInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>
                              <div id="linkPortalConfigBusinessProcess">
                                   <g:remoteLink action="show" id="${portalConfigBusinessProcessInstance.id}" update="showPortalConfigBusinessProcess">${fieldValue(bean:portalConfigBusinessProcessInstance, field:'id')}</g:remoteLink>
                              </div>
                           </td>
                            
                        
                            <td>${fieldValue(bean:portalConfigBusinessProcessInstance, field:'portalConfig')}</td>
                        
                            <td>${fieldValue(bean:portalConfigBusinessProcessInstance, field:'name')}</td>
                        
                            <td>${fieldValue(bean:portalConfigBusinessProcessInstance, field:'purpose')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${portalConfigBusinessProcessInstanceTotal}" />
            </div>
        </div>
        <gui:dialog title="PortalConfigBusinessProcess" 
           modal="true" form="false"
           triggers="[show:[id:'showThePortalConfigBusinessProcess', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="showPortalConfigBusinessProcess" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
        <gui:dialog title="PortalConfigBusinessProcess" 
           modal="true" form="false"
           triggers="[show:[id:'createThePortalConfigBusinessProcess', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="createPortalConfigBusinessProcess" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
    </body>
</html>
