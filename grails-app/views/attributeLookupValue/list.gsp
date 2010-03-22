
<%@ page import="com.grailsPortal.domain.AttributeLookupValue" %>
<html>
    <head>
		<meta name="layout" content="main" />
		<gui:resources components="dialog" />
		<g:javascript library="prototype" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton" id="createTheAttributeLookupValue">
                 <g:remoteLink action="create" update="createAttributeLookupValue">New</g:remoteLink>
            </span>
        </div>
        <div class="body">
            <h1 style="margin-left:20px;">West Orlando Arts Foundation  - AttributeLookupValue </h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="showTheAttributeLookupValue">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="lookupValue" title="Lookup Value" />
                        
                   	        <th>Attribute</th>
                   	    
                   	        <g:sortableColumn property="displayOrder" title="Display Order" />
                        
                   	        <g:sortableColumn property="imagePath" title="Image Path" />
                        
                   	        <g:sortableColumn property="cssClass" title="Css Class" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${attributeLookupValueInstanceList}" status="i" var="attributeLookupValueInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>
                              <div id="linkAttributeLookupValue">
                                   <g:remoteLink action="show" id="${attributeLookupValueInstance.id}" update="showAttributeLookupValue">${fieldValue(bean:attributeLookupValueInstance, field:'id')}</g:remoteLink>
                              </div>
                           </td>
                            
                        
                            <td>${fieldValue(bean:attributeLookupValueInstance, field:'lookupValue')}</td>
                        
                            <td>${fieldValue(bean:attributeLookupValueInstance, field:'attribute')}</td>
                        
                            <td>${fieldValue(bean:attributeLookupValueInstance, field:'displayOrder')}</td>
                        
                            <td>${fieldValue(bean:attributeLookupValueInstance, field:'imagePath')}</td>
                        
                            <td>${fieldValue(bean:attributeLookupValueInstance, field:'cssClass')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${attributeLookupValueInstanceTotal}" />
            </div>
        </div>
        <gui:dialog title="AttributeLookupValue" 
           modal="true" form="false"
           triggers="[show:[id:'showTheAttributeLookupValue', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="showAttributeLookupValue" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
        <gui:dialog title="AttributeLookupValue" 
           modal="true" form="false"
           triggers="[show:[id:'createTheAttributeLookupValue', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="createAttributeLookupValue" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
    </body>
</html>
