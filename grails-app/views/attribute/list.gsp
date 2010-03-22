
<%@ page import="com.grailsPortal.domain.Attribute" %>
<html>
    <head>
		<meta name="layout" content="main" />
		<gui:resources components="dialog" />
		<g:javascript library="prototype" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton" id="createTheAttribute">
                 <g:remoteLink action="create" update="createAttribute">New</g:remoteLink>
            </span>
        </div>
        <div class="body">
            <h1 style="margin-left:20px;">West Orlando Arts Foundation  - Attribute </h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="showTheAttribute">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="cd" title="Cd" />
                        
                   	        <g:sortableColumn property="dsc" title="Dsc" />
                        
                   	        <g:sortableColumn property="name" title="Name" />
                        
                   	        <th>Attribute Data Type</th>
                   	    
                   	        <th>Attribute Type</th>
                   	    
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${attributeInstanceList}" status="i" var="attributeInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>
                              <div id="linkAttribute">
                                   <g:remoteLink action="show" id="${attributeInstance.id}" update="showAttribute">${fieldValue(bean:attributeInstance, field:'id')}</g:remoteLink>
                              </div>
                           </td>
                            
                        
                            <td>${fieldValue(bean:attributeInstance, field:'cd')}</td>
                        
                            <td>${fieldValue(bean:attributeInstance, field:'dsc')}</td>
                        
                            <td>${fieldValue(bean:attributeInstance, field:'name')}</td>
                        
                            <td>${fieldValue(bean:attributeInstance, field:'attributeDataType')}</td>
                        
                            <td>${fieldValue(bean:attributeInstance, field:'attributeType')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${attributeInstanceTotal}" />
            </div>
        </div>
        <gui:dialog title="Attribute" 
           modal="true" form="false"
           triggers="[show:[id:'showTheAttribute', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="showAttribute" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
        <gui:dialog title="Attribute" 
           modal="true" form="false"
           triggers="[show:[id:'createTheAttribute', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="createAttribute" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
    </body>
</html>
