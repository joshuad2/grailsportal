
<%@ page import="com.grailsPortal.domain.ContactEmail" %>
<html>
    <head>
		<meta name="layout" content="main" />
		<gui:resources components="dialog" />
		<g:javascript library="prototype" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton" id="createTheContactEmail">
                 <g:remoteLink action="create" update="createContactEmail">New</g:remoteLink>
            </span>
        </div>
        <div class="body">
            <h1 style="margin-left:20px;">West Orlando Arts Foundation  - ContactEmail </h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="showTheContactEmail">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="emailAddress" title="Email Address" />
                        
                   	        <g:sortableColumn property="verified" title="Verified" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${contactEmailInstanceList}" status="i" var="contactEmailInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>
                              <div id="linkContactEmail">
                                   <g:remoteLink action="show" id="${contactEmailInstance.id}" update="showContactEmail">${fieldValue(bean:contactEmailInstance, field:'id')}</g:remoteLink>
                              </div>
                           </td>
                            
                        
                            <td>${fieldValue(bean:contactEmailInstance, field:'emailAddress')}</td>
                        
                            <td>${fieldValue(bean:contactEmailInstance, field:'verified')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${contactEmailInstanceTotal}" />
            </div>
        </div>
        <gui:dialog title="ContactEmail" 
           modal="true" form="false"
           triggers="[show:[id:'showTheContactEmail', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="showContactEmail" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
        <gui:dialog title="ContactEmail" 
           modal="true" form="false"
           triggers="[show:[id:'createTheContactEmail', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="createContactEmail" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
    </body>
</html>
