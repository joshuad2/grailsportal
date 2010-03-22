
<%@ page import="com.grailsPortal.domain.AttributeComponent" %>
<html>
    <head>
		<meta name="layout" content="main" />
		<gui:resources components="dialog" />
		<g:javascript library="prototype" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton" id="createTheAttributeComponent">
                 <g:remoteLink action="create" update="createAttributeComponent">New</g:remoteLink>
            </span>
        </div>
        <div class="body">
            <h1 style="margin-left:20px;">West Orlando Arts Foundation  - AttributeComponent </h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="showTheAttributeComponent">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="name" title="Name" />
                        
                   	        <th>Attribute</th>
                   	    
                   	        <th>Component</th>
                   	    
                   	        <g:sortableColumn property="labelText" title="Label Text" />
                        
                   	        <g:sortableColumn property="labelCssStyle" title="Label Css Style" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${attributeComponentInstanceList}" status="i" var="attributeComponentInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>
                              <div id="linkAttributeComponent">
                                   <g:remoteLink action="show" id="${attributeComponentInstance.id}" update="showAttributeComponent">${fieldValue(bean:attributeComponentInstance, field:'id')}</g:remoteLink>
                              </div>
                           </td>
                            
                        
                            <td>${fieldValue(bean:attributeComponentInstance, field:'name')}</td>
                        
                            <td>${fieldValue(bean:attributeComponentInstance, field:'attribute')}</td>
                        
                            <td>${fieldValue(bean:attributeComponentInstance, field:'component')}</td>
                        
                            <td>${fieldValue(bean:attributeComponentInstance, field:'labelText')}</td>
                        
                            <td>${fieldValue(bean:attributeComponentInstance, field:'labelCssStyle')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${attributeComponentInstanceTotal}" />
            </div>
        </div>
        <gui:dialog title="AttributeComponent" 
           modal="true" form="false"
           triggers="[show:[id:'showTheAttributeComponent', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="showAttributeComponent" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
        <gui:dialog title="AttributeComponent" 
           modal="true" form="false"
           triggers="[show:[id:'createTheAttributeComponent', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="createAttributeComponent" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
    </body>
</html>
