

<%@ page import="com.grailsPortal.domain.menu.PortalSubMenu" %>
<html>
<head>
<meta name="layout" content="main" />
<gui:resources components="dialog" />
<g:javascript library="prototype" />
</head>
<body>
<div class="nav"><span class="menuButton"><a class="home"
	href="${resource(dir:'')}">Home</a></span> <span class="menuButton"
	id="createThePortalSubMenu"> <g:link action="create" >New</g:link> </span></div>
<div class="body">
<h1 style="margin-left: 20px;">West Orlando Arts Foundation - PortalSubMenu
</h1>
<g:if test="${flash.message}">
	<div class="message">${flash.message}</div>
</g:if>
<div class="list" id="showThePortalSubMenu">
<table>
	<thead>
		<tr>
			
			<g:sortableColumn property="id" title="Id" />
			
			<g:sortableColumn property="theController" title="The Controller" />
			
			<g:sortableColumn property="theAction" title="The Action" />
			
			<g:sortableColumn property="text" title="Text" />
			
			<g:sortableColumn property="isAjax" title="Is Ajax" />
			
			<g:sortableColumn property="showSpinner" title="Show Spinner" />
			
		</tr>
	</thead>
	<tbody>
		<g:each in="${portalSubMenuInstanceList}" status="i" var="portalSubMenuInstance">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
				
				<td>
				<div id="linkPortalSubMenu">
				 <g:link action="show"
					id="${portalSubMenuInstance.id}">
					${fieldValue(bean:portalSubMenuInstance, field:'id')}</g:link>
				</div>
				</td>

				
				<td>${fieldValue(bean:portalSubMenuInstance, field:'theController')}</td>
				
				<td>${fieldValue(bean:portalSubMenuInstance, field:'theAction')}</td>
				
				<td>${fieldValue(bean:portalSubMenuInstance, field:'text')}</td>
				
				<td>${fieldValue(bean:portalSubMenuInstance, field:'isAjax')}</td>
				
				<td>${fieldValue(bean:portalSubMenuInstance, field:'showSpinner')}</td>
				
			</tr>
		</g:each>
	</tbody>
</table>
</div>
<div class="paginateButtons"><g:paginate
	total="${portalSubMenuInstanceTotal}" /></div>
</div>
</body>
</html>
