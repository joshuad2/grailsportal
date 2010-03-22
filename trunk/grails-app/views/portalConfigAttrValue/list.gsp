
<%@ page import="com.grailsPortal.domain.PortalConfigAttrValue" %>
<html>
    <head>
		<meta name="layout" content="main" />
		<gui:resources components="dialog" />
		<g:javascript library="prototype" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton" id="createThePortalConfigAttrValue">
                 <g:remoteLink action="create" update="createPortalConfigAttrValue">New</g:remoteLink>
            </span>
        </div>
        <div class="body">
            <h1 style="margin-left:20px;">West Orlando Arts Foundation  - PortalConfigAttrValue </h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="showThePortalConfigAttrValue">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="attrValue" title="Attr Value" />
                        
                   	        <th>Portal Config</th>
                   	    
                   	        <th>Attribute</th>
                   	    
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${portalConfigAttrValueInstanceList}" status="i" var="portalConfigAttrValueInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>
                              <div id="linkPortalConfigAttrValue">
                                   <g:remoteLink action="show" id="${portalConfigAttrValueInstance.id}" update="showPortalConfigAttrValue">${fieldValue(bean:portalConfigAttrValueInstance, field:'id')}</g:remoteLink>
                              </div>
                           </td>
                            
                        
                            <td>${fieldValue(bean:portalConfigAttrValueInstance, field:'attrValue')}</td>
                        
                            <td>${fieldValue(bean:portalConfigAttrValueInstance, field:'portalConfig')}</td>
                        
                            <td>${fieldValue(bean:portalConfigAttrValueInstance, field:'attribute')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${portalConfigAttrValueInstanceTotal}" />
            </div>
        </div>
        <gui:dialog title="PortalConfigAttrValue" 
           modal="true" form="false"
           triggers="[show:[id:'showThePortalConfigAttrValue', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="showPortalConfigAttrValue" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
        <gui:dialog title="PortalConfigAttrValue" 
           modal="true" form="false"
           triggers="[show:[id:'createThePortalConfigAttrValue', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="createPortalConfigAttrValue" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
    </body>
</html>
