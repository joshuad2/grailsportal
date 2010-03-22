
<%@ page import="com.grailsPortal.domain.BusinessProcessStep" %>
<html>
    <head>
		<meta name="layout" content="main" />
		<gui:resources components="dialog" />
		<g:javascript library="prototype" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton" id="createTheBusinessProcessStep">
                 <g:remoteLink action="create" update="createBusinessProcessStep">New</g:remoteLink>
            </span>
        </div>
        <div class="body">
            <h1 style="margin-left:20px;">West Orlando Arts Foundation  - BusinessProcessStep </h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="showTheBusinessProcessStep">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="cd" title="Cd" />
                        
                   	        <g:sortableColumn property="name" title="Name" />
                        
                   	        <g:sortableColumn property="dsc" title="Dsc" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${businessProcessStepInstanceList}" status="i" var="businessProcessStepInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>
                              <div id="linkBusinessProcessStep">
                                   <g:remoteLink action="show" id="${businessProcessStepInstance.id}" update="showBusinessProcessStep">${fieldValue(bean:businessProcessStepInstance, field:'id')}</g:remoteLink>
                              </div>
                           </td>
                            
                        
                            <td>${fieldValue(bean:businessProcessStepInstance, field:'cd')}</td>
                        
                            <td>${fieldValue(bean:businessProcessStepInstance, field:'name')}</td>
                        
                            <td>${fieldValue(bean:businessProcessStepInstance, field:'dsc')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${businessProcessStepInstanceTotal}" />
            </div>
        </div>
        <gui:dialog title="BusinessProcessStep" 
           modal="true" form="false"
           triggers="[show:[id:'showTheBusinessProcessStep', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="showBusinessProcessStep" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
        <gui:dialog title="BusinessProcessStep" 
           modal="true" form="false"
           triggers="[show:[id:'createTheBusinessProcessStep', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="createBusinessProcessStep" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
    </body>
</html>
