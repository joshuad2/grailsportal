

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Registration List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" controller="registration" action="newRegistration">Register Another Child</g:link></span>
         </div>
        <div class="body" style="float:left">
            <h1>Registration List</h1>
            <div id="listText" class="dialog" style="width:800px;font-size:12;border: 1px;font-family: Verdana;padding-top: 20;padding-bottom: 20;padding-left: 10;padding-right: 20;border-style: solid">Instructions:</br>Please select the Registrations you would like to manage by selecting the "id" column on the left side.&nbsp;&nbsp;A completed registration has a status of <b>Ready</b>.&nbsp;&nbsp;If it has been cancelled or had a problem it will show as <b>Incomplete</b>.&nbsp;&nbsp;When it has been processed it will show <b>Paid in Full</div>
            </p>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                   	        <g:sortableColumn property="id" title="Id" />
                   	        <g:sortableColumn property="camperName" title="Camper Name" />
                   	        <g:sortableColumn property="camperAge" title="Camper Age" />
                   	        <g:sortableColumn property="campergrade" title="Camper Grade" />
                   	        <g:sortableColumn property="orderStatus" title="Status"/>
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${registrationEventInstanceList}" status="i" var="registrationEventInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                          <g:if test="${registrationEventInstance.orderRecord!=null && registrationEventInstance.orderRecord.orderStatus!=null && (registrationEventInstance.orderRecord.orderStatus=='Incomplete' || registrationEventInstance.orderRecord.orderStatus=='Ready')}">
                            <td><g:link controller="registration" action="edit" params="[regEventId:registrationEventInstance.id]">${fieldValue(bean:registrationEventInstance, field:'id')}</g:link></td>
                          </g:if>
                          <g:else>
                            <td>${fieldValue(bean:registrationEventInstance, field:'id')}</td>
                          </g:else>                          
                            <td>${fieldValue(bean:registrationEventInstance, field:'registrationFor.firstName')}&nbsp;${fieldValue(bean:registrationEventInstance, field:'registrationFor.lastName')}</td>
                            <td>${fieldValue(bean:registrationEventInstance, field:'camperAge')}</td>
                            <td>${fieldValue(bean:registrationEventInstance, field:'campergrade')}</td>
                            <td>${fieldValue(bean:registrationEventInstance, field:'orderRecord.orderStatus')}</td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${registrationEventInstanceList}.count()" />
            </div>
        </div>
    </body>
</html>
