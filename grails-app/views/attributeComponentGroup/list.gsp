
<%@ page import="com.grailsPortal.domain.AttributeComponentGroup" %>
<html>
    <head>
		<meta name="layout" content="main" />
		<gui:resources components="dialog" />
		<g:javascript library="prototype" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton" id="createTheAttributeComponentGroup">
                 <g:remoteLink action="create" update="createAttributeComponentGroup">New</g:remoteLink>
            </span>
        </div>
        <div class="body">
            <h1 style="margin-left:20px;">West Orlando Arts Foundation  - AttributeComponentGroup </h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="showTheAttributeComponentGroup">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="name" title="Name" />
                        
                   	        <g:sortableColumn property="dsc" title="Dsc" />
                        
                   	        <g:sortableColumn property="cd" title="Cd" />
                        
                   	        <g:sortableColumn property="active" title="Active" />
                        
                   	        <g:sortableColumn property="mandatory" title="Mandatory" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${attributeComponentGroupInstanceList}" status="i" var="attributeComponentGroupInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>
                              <div id="linkAttributeComponentGroup">
                                   <g:remoteLink action="show" id="${attributeComponentGroupInstance.id}" update="showAttributeComponentGroup">${fieldValue(bean:attributeComponentGroupInstance, field:'id')}</g:remoteLink>
                              </div>
                           </td>
                            
                        
                            <td>${fieldValue(bean:attributeComponentGroupInstance, field:'name')}</td>
                        
                            <td>${fieldValue(bean:attributeComponentGroupInstance, field:'dsc')}</td>
                        
                            <td>${fieldValue(bean:attributeComponentGroupInstance, field:'cd')}</td>
                        
                            <td>${fieldValue(bean:attributeComponentGroupInstance, field:'active')}</td>
                        
                            <td>${fieldValue(bean:attributeComponentGroupInstance, field:'mandatory')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${attributeComponentGroupInstanceTotal}" />
            </div>
        </div>
        <gui:dialog title="AttributeComponentGroup" 
           modal="true" form="false"
           triggers="[show:[id:'showTheAttributeComponentGroup', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="showAttributeComponentGroup" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
        <gui:dialog title="AttributeComponentGroup" 
           modal="true" form="false"
           triggers="[show:[id:'createTheAttributeComponentGroup', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="createAttributeComponentGroup" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
    </body>
</html>
