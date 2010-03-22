
<%@ page import="com.grailsPortal.domain.AttributeDataType" %>
<html>
    <head>
		<meta name="layout" content="main" />
		<gui:resources components="dialog" />
		<g:javascript library="prototype" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton" id="createTheAttributeDataType">
                 <g:remoteLink action="create" update="createAttributeDataType">New</g:remoteLink>
            </span>
        </div>
        <div class="body">
            <h1 style="margin-left:20px;">West Orlando Arts Foundation  - AttributeDataType </h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="showTheAttributeDataType">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="name" title="Name" />
                        
                   	        <g:sortableColumn property="dsc" title="Dsc" />
                        
                   	        <g:sortableColumn property="groovyType" title="Groovy Type" />
                        
                   	        <g:sortableColumn property="size" title="Size" />
                        
                   	        <g:sortableColumn property="htmlStartTag" title="Html Start Tag" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${attributeDataTypeInstanceList}" status="i" var="attributeDataTypeInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>
                              <div id="linkAttributeDataType">
                                   <g:remoteLink action="show" id="${attributeDataTypeInstance.id}" update="showAttributeDataType">${fieldValue(bean:attributeDataTypeInstance, field:'id')}</g:remoteLink>
                              </div>
                           </td>
                            
                        
                            <td>${fieldValue(bean:attributeDataTypeInstance, field:'name')}</td>
                        
                            <td>${fieldValue(bean:attributeDataTypeInstance, field:'dsc')}</td>
                        
                            <td>${fieldValue(bean:attributeDataTypeInstance, field:'groovyType')}</td>
                        
                            <td>${fieldValue(bean:attributeDataTypeInstance, field:'size')}</td>
                        
                            <td>${fieldValue(bean:attributeDataTypeInstance, field:'htmlStartTag')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${attributeDataTypeInstanceTotal}" />
            </div>
        </div>
        <gui:dialog title="AttributeDataType" 
           modal="true" form="false"
           triggers="[show:[id:'showTheAttributeDataType', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="showAttributeDataType" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
        <gui:dialog title="AttributeDataType" 
           modal="true" form="false"
           triggers="[show:[id:'createTheAttributeDataType', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="createAttributeDataType" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
    </body>
</html>
