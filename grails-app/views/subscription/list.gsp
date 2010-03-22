
<%@ page import="com.grailsPortal.domain.Subscription" %>
<html>
    <head>
		<meta name="layout" content="main" />
		<gui:resources components="dialog" />
		<g:javascript library="prototype" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton" id="createTheSubscription">
                 <g:remoteLink action="create" update="createSubscription">New</g:remoteLink>
            </span>
        </div>
        <div class="body">
            <h1 style="margin-left:20px;">West Orlando Arts Foundation  - Subscription </h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="showTheSubscription">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="active" title="Active" />
                        
                   	        <g:sortableColumn property="endDate" title="End Date" />
                        
                   	        <g:sortableColumn property="paidInFull" title="Paid In Full" />
                        
                   	        <g:sortableColumn property="renewDate" title="Renew Date" />
                        
                   	        <g:sortableColumn property="startDate" title="Start Date" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${subscriptionInstanceList}" status="i" var="subscriptionInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>
                              <div id="linkSubscription">
                                   <g:remoteLink action="show" id="${subscriptionInstance.id}" update="showSubscription">${fieldValue(bean:subscriptionInstance, field:'id')}</g:remoteLink>
                              </div>
                           </td>
                            
                        
                            <td>${fieldValue(bean:subscriptionInstance, field:'active')}</td>
                        
                            <td>${fieldValue(bean:subscriptionInstance, field:'endDate')}</td>
                        
                            <td>${fieldValue(bean:subscriptionInstance, field:'paidInFull')}</td>
                        
                            <td>${fieldValue(bean:subscriptionInstance, field:'renewDate')}</td>
                        
                            <td>${fieldValue(bean:subscriptionInstance, field:'startDate')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${subscriptionInstanceTotal}" />
            </div>
        </div>
        <gui:dialog title="Subscription" 
           modal="true" form="false"
           triggers="[show:[id:'showTheSubscription', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="showSubscription" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
        <gui:dialog title="Subscription" 
           modal="true" form="false"
           triggers="[show:[id:'createTheSubscription', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="createSubscription" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
    </body>
</html>
