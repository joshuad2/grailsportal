
<%@ page import="com.grailsPortal.domain.State" %>
<html>
    <head>
		<meta name="layout" content="main" />
		<gui:resources components="dialog" />
		<g:javascript library="prototype" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton" id="createTheState">
                 <g:remoteLink action="create" update="createState">New</g:remoteLink>
            </span>
        </div>
        <div class="body">
            <h1 style="margin-left:20px;">West Orlando Arts Foundation  - State </h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="showTheState">
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
                    <g:each in="${stateInstanceList}" status="i" var="stateInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>
                              <div id="linkState">
                                   <g:remoteLink action="show" id="${stateInstance.id}" update="showState">${fieldValue(bean:stateInstance, field:'id')}</g:remoteLink>
                              </div>
                           </td>
                            
                        
                            <td>${fieldValue(bean:stateInstance, field:'name')}</td>
                        
                            <td>${fieldValue(bean:stateInstance, field:'cd')}</td>
                        
                            <td>${fieldValue(bean:stateInstance, field:'dsc')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${stateInstanceTotal}" />
            </div>
        </div>
        <gui:dialog title="State" 
           modal="true" form="false"
           triggers="[show:[id:'showTheState', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="showState" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
        <gui:dialog title="State" 
           modal="true" form="false"
           triggers="[show:[id:'createTheState', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="createState" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
    </body>
</html>
