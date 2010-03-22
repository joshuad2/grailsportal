
<%@ page import="com.grailsPortal.domain.ContactPhone" %>
<html>
    <head>
		<meta name="layout" content="main" />
		<gui:resources components="dialog" />
		<g:javascript library="prototype" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton" id="createTheContactPhone">
                 <g:remoteLink action="create" update="createContactPhone">New</g:remoteLink>
            </span>
        </div>
        <div class="body">
            <h1 style="margin-left:20px;">West Orlando Arts Foundation  - ContactPhone </h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="showTheContactPhone">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="areaCode" title="Area Code" />
                        
                   	        <g:sortableColumn property="internationalCode" title="International Code" />
                        
                   	        <g:sortableColumn property="phoneNumber" title="Phone Number" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${contactPhoneInstanceList}" status="i" var="contactPhoneInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>
                              <div id="linkContactPhone">
                                   <g:remoteLink action="show" id="${contactPhoneInstance.id}" update="showContactPhone">${fieldValue(bean:contactPhoneInstance, field:'id')}</g:remoteLink>
                              </div>
                           </td>
                            
                        
                            <td>${fieldValue(bean:contactPhoneInstance, field:'areaCode')}</td>
                        
                            <td>${fieldValue(bean:contactPhoneInstance, field:'internationalCode')}</td>
                        
                            <td>${fieldValue(bean:contactPhoneInstance, field:'phoneNumber')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${contactPhoneInstanceTotal}" />
            </div>
        </div>
        <gui:dialog title="ContactPhone" 
           modal="true" form="false"
           triggers="[show:[id:'showTheContactPhone', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="showContactPhone" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
        <gui:dialog title="ContactPhone" 
           modal="true" form="false"
           triggers="[show:[id:'createTheContactPhone', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="createContactPhone" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
    </body>
</html>
