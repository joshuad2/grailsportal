
<%@ page import="com.grailsPortal.domain.ViewAttributeComponentGroup" %>
<html>
    <head>
		<meta name="layout" content="main" />
		<gui:resources components="dialog" />
		<g:javascript library="prototype" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton" id="createTheViewAttributeComponentGroup">
                 <g:remoteLink action="create" update="createViewAttributeComponentGroup">New</g:remoteLink>
            </span>
        </div>
        <div class="body">
            <h1 style="margin-left:20px;">West Orlando Arts Foundation  - ViewAttributeComponentGroup </h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="showTheViewAttributeComponentGroup">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="name" title="Name" />
                        
                   	        <g:sortableColumn property="displayOrder" title="Display Order" />
                        
                   	        <th>View</th>
                   	    
                   	        <g:sortableColumn property="active" title="Active" />
                        
                   	        <g:sortableColumn property="mandatory" title="Mandatory" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${viewAttributeComponentGroupInstanceList}" status="i" var="viewAttributeComponentGroupInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>
                              <div id="linkViewAttributeComponentGroup">
                                   <g:remoteLink action="show" id="${viewAttributeComponentGroupInstance.id}" update="showViewAttributeComponentGroup">${fieldValue(bean:viewAttributeComponentGroupInstance, field:'id')}</g:remoteLink>
                              </div>
                           </td>
                            
                        
                            <td>${fieldValue(bean:viewAttributeComponentGroupInstance, field:'name')}</td>
                        
                            <td>${fieldValue(bean:viewAttributeComponentGroupInstance, field:'displayOrder')}</td>
                        
                            <td>${fieldValue(bean:viewAttributeComponentGroupInstance, field:'view')}</td>
                        
                            <td>${fieldValue(bean:viewAttributeComponentGroupInstance, field:'active')}</td>
                        
                            <td>${fieldValue(bean:viewAttributeComponentGroupInstance, field:'mandatory')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${viewAttributeComponentGroupInstanceTotal}" />
            </div>
        </div>
        <gui:dialog title="ViewAttributeComponentGroup" 
           modal="true" form="false"
           triggers="[show:[id:'showTheViewAttributeComponentGroup', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="showViewAttributeComponentGroup" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
        <gui:dialog title="ViewAttributeComponentGroup" 
           modal="true" form="false"
           triggers="[show:[id:'createTheViewAttributeComponentGroup', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="createViewAttributeComponentGroup" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
    </body>
</html>
