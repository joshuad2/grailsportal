

<%@ page import="com.grailsPortal.domain.menu.PortalMenuConfiguration" %>
<html>
<head>
<meta name="layout" content="main" />
<gui:resources components="dialog" />
<g:javascript library="prototype" />
</head>
<body>
<div class="nav"><span class="menuButton"><a class="home"
	href="${resource(dir:'')}">Home</a></span> <span class="menuButton"
	id="createThePortalMenuConfiguration"> <g:remoteLink action="create"
	update="createPortalMenuConfiguration">New</g:remoteLink> </span></div>
<div class="body">
<h1 style="margin-left: 20px;">West Orlando Arts Foundation - PortalMenuConfiguration
</h1>
<g:if test="${flash.message}">
	<div class="message">${flash.message}</div>
</g:if>
<div class="list" id="showThePortalMenuConfiguration">
<table>
	<thead>
		<tr>
			
			<g:sortableColumn property="id" title="Id" />
			
			<g:sortableColumn property="menuName" title="Menu Name" />
			
			<g:sortableColumn property="position" title="Position" />
			
			<g:sortableColumn property="hideDelay" title="Hide Delay" />
			
			<g:sortableColumn property="lazyLoad" title="Lazy Load" />
			
			<g:sortableColumn property="style" title="Style" />
			
		</tr>
	</thead>
	<tbody>
		<g:each in="${portalMenuConfigurationInstanceList}" status="i" var="portalMenuConfigurationInstance">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
				
				<td>
				<div id="linkPortalMenuConfiguration">
				 <g:remoteLink action="show"
					id="${portalMenuConfigurationInstance.id}" update="showPortalMenuConfiguration">
					${fieldValue(bean:portalMenuConfigurationInstance, field:'id')}</g:remoteLink>
				</div>
				</td>

				
				<td>${fieldValue(bean:portalMenuConfigurationInstance, field:'menuName')}</td>
				
				<td>${fieldValue(bean:portalMenuConfigurationInstance, field:'position')}</td>
				
				<td>${fieldValue(bean:portalMenuConfigurationInstance, field:'hideDelay')}</td>
				
				<td>${fieldValue(bean:portalMenuConfigurationInstance, field:'lazyLoad')}</td>
				
				<td>${fieldValue(bean:portalMenuConfigurationInstance, field:'style')}</td>
				
			</tr>
		</g:each>
	</tbody>
</table>
</div>
<div class="paginateButtons"><g:paginate
	total="${portalMenuConfigurationInstanceTotal}" /></div>
</div>
<gui:dialog title="PortalMenuConfiguration" modal="true" form="false"
	triggers="[show:[id:'showThePortalMenuConfiguration', on:'click']]"
	fixedCenter="true">
	<div class="dialog" id="showPortalMenuConfiguration"
		style="width: 600px; height: 400px; overflow: scroll"></div>
</gui:dialog>
<gui:dialog title="PortalMenuConfiguration" modal="true" form="false"
	triggers="[show:[id:'createThePortalMenuConfiguration', on:'click']]"
	fixedCenter="true">
	<div class="dialog" id="createPortalMenuConfiguration"
		style="width: 600px; height: 400px; overflow: scroll"></div>
</gui:dialog>
</body>
</html>
