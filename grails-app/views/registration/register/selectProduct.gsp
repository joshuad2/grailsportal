<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="layout" content="main" />
<title>Select weeks to register for</title>
</head>
<body>
<div class="body">
<h1>Select the week(s)</h1>
<g:if test="${message}">
	<div class="message">
	${message}
	</div>
</g:if> 
<g:hasErrors bean="${registration}">
	<div class="errors"><g:renderErrors bean="${registration}"
		as="list" /></div>
</g:hasErrors>
<g:if test="${session.regEventId!=null}">
	<g:set var="regEvent"
		value="${RegistrationEvent.get(session.regEventId)}" />
	<g:set var="clis"
		value="${OrderRecordLineItem.findAllByOrderRecord(regEvent.orderRecord)}" />
</g:if>
<g:else>
	<g:set var="regEvent" value="${new com.grailsPortal.domain.RegistrationEvent()}" />
</g:else>
<g:form action="register" method="post">
<div id="mainParent" class="dialog">
	<table>
		<th>
		<tr>
			<td colspan="2">Select The Product you are interested in</td>
			<td>Cost</td>
		</tr>
		</th>
		<tbody>
			<g:if test="${regEvent.id == null}">
				<g:each in="${session.prods}">
					<tr class="prop">
						<td valign="top" class="name"><label for="${it.name}"></label></td>
						<td valign="top">
						  <g:checkBox name="${it.cd}" value="${false}" />
						<td valign="top" align="left" class="name">
						  <g:formatNumber number="${it.netCostAmount}" format="\$###,##0.00" />
						</td>
					</tr>
				</g:each>
			</g:if>
			<g:else>
				<g:each in="${session.prods}">
					<tr class="prop">
						<td valign="top" class="name"><label for="${it.name}"></label></td>
						<td valign="top">
						  <g:set var="cli" value="${OrderRecordLineItem.findByOrderRecordAndProduct(regEvent.orderRecord,it)}" />
						  <g:if test="${cli==null}">
							<g:checkBox name="${it.cd}" value="${false}" />
						  </g:if> 
						  <g:else>
							 <g:checkBox name="${it.cd}" value="${true}" />
						  </g:else>
						</td>
						<td valign="top" align="left" class="name">
						    <g:formatNumber number="${it.netCostAmount}" format="\$###,##0.00" />
						</td>
					</tr>
				</g:each>
			</g:else>
		</tbody>
	</table>
	</div>
	<div class="buttons"><g:submitButton name="gotoRegistrantInfo"
		value="Next"></g:submitButton> <g:submitButton name="gotoCancel"
		value="Cancel"></g:submitButton></div>
</g:form></div>
</body>
</html>
