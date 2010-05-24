
<%@ page import="com.grailsPortal.domain.AttributeCategory" %>
<html>
    <head>
		<meta name="layout" content="main" />
		<gui:resources components="dialog" />
		<g:javascript library="prototype" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton" id="createTheAttributeCategory">
                 <g:remoteLink action="create" update="createAttributeCategory">New</g:remoteLink>
            </span>
        </div>
        <div class="body">
            <h1 style="margin-left:20px;">West Orlando Arts Foundation  - AttributeCategory </h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="showTheAttributeCategory">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="cd" title="Cd" />
                        
                   	        <g:sortableColumn property="dsc" title="Dsc" />
                        
                   	        <g:sortableColumn property="name" title="Name" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${attributeCategoryInstanceList}" status="i" var="attributeCategoryInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>
                              <div id="linkAttributeCategory">
                                   <g:remoteLink action="show" id="${attributeCategoryInstance.id}" update="showAttributeCategory">${fieldValue(bean:attributeCategoryInstance, field:'id')}</g:remoteLink>
                              </div>
                           </td>
                            
                        
                            <td>${fieldValue(bean:attributeCategoryInstance, field:'cd')}</td>
                        
                            <td>${fieldValue(bean:attributeCategoryInstance, field:'dsc')}</td>
                        
                            <td>${fieldValue(bean:attributeCategoryInstance, field:'name')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${attributeCategoryInstanceTotal}" />
            </div>
        </div>
        <gui:dialog title="AttributeCategory" 
           modal="true" form="false"
           triggers="[show:[id:'showTheAttributeCategory', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="showAttributeCategory" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
        <gui:dialog title="AttributeCategory" 
           modal="true" form="false"
           triggers="[show:[id:'createTheAttributeCategory', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="createAttributeCategory" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
    </body>
</html>