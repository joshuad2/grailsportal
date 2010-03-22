
<%@ page import="com.grailsPortal.domain.OrderRecordType" %>
<html>
    <head>
		<meta name="layout" content="main" />
		<gui:resources components="dialog" />
		<g:javascript library="prototype" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton" id="createTheOrderRecordType">
                 <g:remoteLink action="create" update="createOrderRecordType">New</g:remoteLink>
            </span>
        </div>
        <div class="body">
            <h1 style="margin-left:20px;">West Orlando Arts Foundation  - OrderRecordType </h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="showTheOrderRecordType">
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
                    <g:each in="${orderRecordTypeInstanceList}" status="i" var="orderRecordTypeInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>
                              <div id="linkOrderRecordType">
                                   <g:remoteLink action="show" id="${orderRecordTypeInstance.id}" update="showOrderRecordType">${fieldValue(bean:orderRecordTypeInstance, field:'id')}</g:remoteLink>
                              </div>
                           </td>
                            
                        
                            <td>${fieldValue(bean:orderRecordTypeInstance, field:'cd')}</td>
                        
                            <td>${fieldValue(bean:orderRecordTypeInstance, field:'dsc')}</td>
                        
                            <td>${fieldValue(bean:orderRecordTypeInstance, field:'name')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${orderRecordTypeInstanceTotal}" />
            </div>
        </div>
        <gui:dialog title="OrderRecordType" 
           modal="true" form="false"
           triggers="[show:[id:'showTheOrderRecordType', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="showOrderRecordType" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
        <gui:dialog title="OrderRecordType" 
           modal="true" form="false"
           triggers="[show:[id:'createTheOrderRecordType', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="createOrderRecordType" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
    </body>
</html>
