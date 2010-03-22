
<%@ page import="com.grailsPortal.domain.AttributeType" %>
<html>
    <head>
		<meta name="layout" content="main" />
		<gui:resources components="dialog" />
		<g:javascript library="prototype" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton" id="createTheAttributeType">
                 <g:remoteLink action="create" update="createAttributeType">New</g:remoteLink>
            </span>
        </div>
        <div class="body">
            <h1 style="margin-left:20px;">West Orlando Arts Foundation  - AttributeType </h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="showTheAttributeType">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="name" title="Name" />
                        
                   	        <g:sortableColumn property="cd" title="Cd" />
                        
                   	        <g:sortableColumn property="dsc" title="Dsc" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${attributeTypeInstanceList}" status="i" var="attributeTypeInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>
                              <div id="linkAttributeType">
                                   <g:remoteLink action="show" id="${attributeTypeInstance.id}" update="showAttributeType">${fieldValue(bean:attributeTypeInstance, field:'id')}</g:remoteLink>
                              </div>
                           </td>
                            
                        
                            <td>${fieldValue(bean:attributeTypeInstance, field:'name')}</td>
                        
                            <td>${fieldValue(bean:attributeTypeInstance, field:'cd')}</td>
                        
                            <td>${fieldValue(bean:attributeTypeInstance, field:'dsc')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${attributeTypeInstanceTotal}" />
            </div>
        </div>
        <gui:dialog title="AttributeType" 
           modal="true" form="false"
           triggers="[show:[id:'showTheAttributeType', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="showAttributeType" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
        <gui:dialog title="AttributeType" 
           modal="true" form="false"
           triggers="[show:[id:'createTheAttributeType', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="createAttributeType" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
    </body>
</html>
