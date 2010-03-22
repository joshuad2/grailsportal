
<%@ page import="com.grailsPortal.domain.ViewAttribute" %>
<html>
    <head>
		<meta name="layout" content="main" />
		<gui:resources components="dialog" />
		<g:javascript library="prototype" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton" id="createTheViewAttribute">
                 <g:remoteLink action="create" update="createViewAttribute">New</g:remoteLink>
            </span>
        </div>
        <div class="body">
            <h1 style="margin-left:20px;">West Orlando Arts Foundation  - ViewAttribute </h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="showTheViewAttribute">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <th>View</th>
                   	    
                   	        <th>Attribute Component</th>
                   	    
                   	        <g:sortableColumn property="displayOrder" title="Display Order" />
                        
                   	        <g:sortableColumn property="mandatory" title="Mandatory" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${viewAttributeInstanceList}" status="i" var="viewAttributeInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>
                              <div id="linkViewAttribute">
                                   <g:remoteLink action="show" id="${viewAttributeInstance.id}" update="showViewAttribute">${fieldValue(bean:viewAttributeInstance, field:'id')}</g:remoteLink>
                              </div>
                           </td>
                            
                        
                            <td>${fieldValue(bean:viewAttributeInstance, field:'view')}</td>
                        
                            <td>${fieldValue(bean:viewAttributeInstance, field:'attributeComponent')}</td>
                        
                            <td>${fieldValue(bean:viewAttributeInstance, field:'displayOrder')}</td>
                        
                            <td>${fieldValue(bean:viewAttributeInstance, field:'mandatory')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${viewAttributeInstanceTotal}" />
            </div>
        </div>
        <gui:dialog title="ViewAttribute" 
           modal="true" form="false"
           triggers="[show:[id:'showTheViewAttribute', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="showViewAttribute" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
        <gui:dialog title="ViewAttribute" 
           modal="true" form="false"
           triggers="[show:[id:'createTheViewAttribute', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="createViewAttribute" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
    </body>
</html>
