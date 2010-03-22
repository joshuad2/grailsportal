
<%@ page import="com.grailsPortal.domain.JsecUserAttributeValue" %>
<html>
    <head>
		<meta name="layout" content="main" />
		<gui:resources components="dialog" />
		<g:javascript library="prototype" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton" id="createTheJsecUserAttributeValue">
                 <g:remoteLink action="create" update="createJsecUserAttributeValue">New</g:remoteLink>
            </span>
        </div>
        <div class="body">
            <h1 style="margin-left:20px;">West Orlando Arts Foundation  - JsecUserAttributeValue </h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="showTheJsecUserAttributeValue">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <th>User</th>
                   	    
                   	        <th>Attribute</th>
                   	    
                   	        <g:sortableColumn property="value" title="Value" />
                        
                   	        <g:sortableColumn property="active" title="Active" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${jsecUserAttributeValueInstanceList}" status="i" var="jsecUserAttributeValueInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>
                              <div id="linkJsecUserAttributeValue">
                                   <g:remoteLink action="show" id="${jsecUserAttributeValueInstance.id}" update="showJsecUserAttributeValue">${fieldValue(bean:jsecUserAttributeValueInstance, field:'id')}</g:remoteLink>
                              </div>
                           </td>
                            
                        
                            <td>${fieldValue(bean:jsecUserAttributeValueInstance, field:'user')}</td>
                        
                            <td>${fieldValue(bean:jsecUserAttributeValueInstance, field:'attribute')}</td>
                        
                            <td>${fieldValue(bean:jsecUserAttributeValueInstance, field:'value')}</td>
                        
                            <td>${fieldValue(bean:jsecUserAttributeValueInstance, field:'active')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${jsecUserAttributeValueInstanceTotal}" />
            </div>
        </div>
        <gui:dialog title="JsecUserAttributeValue" 
           modal="true" form="false"
           triggers="[show:[id:'showTheJsecUserAttributeValue', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="showJsecUserAttributeValue" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
        <gui:dialog title="JsecUserAttributeValue" 
           modal="true" form="false"
           triggers="[show:[id:'createTheJsecUserAttributeValue', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="createJsecUserAttributeValue" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
    </body>
</html>
