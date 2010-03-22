
<%@ page import="com.grailsPortal.domain.PortalConfig" %>
<html>
    <head>
		<meta name="layout" content="main" />
		<gui:resources components="dialog" />
		<g:javascript library="prototype" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton" id="createThePortalConfig">
                 <g:remoteLink action="create" update="createPortalConfig">New</g:remoteLink>
            </span>
        </div>
        <div class="body">
            <h1 style="margin-left:20px;">West Orlando Arts Foundation  - PortalConfig </h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="showThePortalConfig">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="name" title="Name" />
                        
                   	        <g:sortableColumn property="description" title="Description" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${portalConfigInstanceList}" status="i" var="portalConfigInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>
                              <div id="linkPortalConfig">
                                   <g:remoteLink action="show" id="${portalConfigInstance.id}" update="showPortalConfig">${fieldValue(bean:portalConfigInstance, field:'id')}</g:remoteLink>
                              </div>
                           </td>
                            
                        
                            <td>${fieldValue(bean:portalConfigInstance, field:'name')}</td>
                        
                            <td>${fieldValue(bean:portalConfigInstance, field:'description')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${portalConfigInstanceTotal}" />
            </div>
        </div>
        <gui:dialog title="PortalConfig" 
           modal="true" form="false"
           triggers="[show:[id:'showThePortalConfig', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="showPortalConfig" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
        <gui:dialog title="PortalConfig" 
           modal="true" form="false"
           triggers="[show:[id:'createThePortalConfig', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="createPortalConfig" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
    </body>
</html>
