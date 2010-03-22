
<%@ page import="com.grailsPortal.domain.PortalConfigAttribute" %>
<html>
    <head>
		<meta name="layout" content="main" />
		<gui:resources components="dialog" />
		<g:javascript library="prototype" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton" id="createThePortalConfigAttribute">
                 <g:remoteLink action="create" update="createPortalConfigAttribute">New</g:remoteLink>
            </span>
        </div>
        <div class="body">
            <h1 style="margin-left:20px;">West Orlando Arts Foundation  - PortalConfigAttribute </h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="showThePortalConfigAttribute">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <th>Attribute</th>
                   	    
                   	        <g:sortableColumn property="mandatory" title="Mandatory" />
                        
                   	        <th>Portal Config</th>
                   	    
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${portalConfigAttributeInstanceList}" status="i" var="portalConfigAttributeInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>
                              <div id="linkPortalConfigAttribute">
                                   <g:remoteLink action="show" id="${portalConfigAttributeInstance.id}" update="showPortalConfigAttribute">${fieldValue(bean:portalConfigAttributeInstance, field:'id')}</g:remoteLink>
                              </div>
                           </td>
                            
                        
                            <td>${fieldValue(bean:portalConfigAttributeInstance, field:'attribute')}</td>
                        
                            <td>${fieldValue(bean:portalConfigAttributeInstance, field:'mandatory')}</td>
                        
                            <td>${fieldValue(bean:portalConfigAttributeInstance, field:'portalConfig')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${portalConfigAttributeInstanceTotal}" />
            </div>
        </div>
        <gui:dialog title="PortalConfigAttribute" 
           modal="true" form="false"
           triggers="[show:[id:'showThePortalConfigAttribute', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="showPortalConfigAttribute" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
        <gui:dialog title="PortalConfigAttribute" 
           modal="true" form="false"
           triggers="[show:[id:'createThePortalConfigAttribute', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="createPortalConfigAttribute" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
    </body>
</html>
