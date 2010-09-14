
<% import org.codehaus.groovy.grails.orm.hibernate.support.ClosureEventTriggeringInterceptor as Events %>
<%=packageName%>
<html>
<head>
<meta name="layout" content="main" />
<gui:resources components="dialog" />
<g:javascript library="prototype" />
</head>
<body>
<div class="nav"><span class="menuButton"><a class="home"
	href="\${resource(dir:'')}">Home</a></span> <span class="menuButton"
	id="createThe${className}"> <g:link action="create" >New</g:link> </span></div>
<div class="body">
<h1 style="margin-left: 20px;">West Orlando Arts Foundation - ${className}
</h1>
<g:if test="\${flash.message}">
	<div class="message">\${flash.message}</div>
</g:if>
<div class="list" id="showThe${className}">
<table>
	<thead>
		<tr>
			<%
         excludedProps = ['version',
             Events.ONLOAD_EVENT,
             Events.BEFORE_DELETE_EVENT,
             Events.BEFORE_INSERT_EVENT,
             Events.BEFORE_UPDATE_EVENT]           
           props = domainClass.properties.findAll { 
			   !excludedProps.contains(it.name) && it.type != Set.class }
              Collections.sort(props, comparator.constructors[0].newInstance(
			                  [domainClass] as Object[]))
           props.eachWithIndex { p,i ->
                   if(i < 6) {
                if(p.isAssociation()) { %>
			<th>
			${p.naturalName}
			</th>
			<%          } else { %>
			<g:sortableColumn property="${p.name}" title="${p.naturalName}" />
			<%  }   }   } %>
		</tr>
	</thead>
	<tbody>
		<g:each in="\${${propertyName}List}" status="i" var="${propertyName}">
			<tr class="\${(i % 2) == 0 ? 'odd' : 'even'}">
				<%  props.eachWithIndex { p,i ->
                                if(i == 0) { %>
				<td>
				<div id="link${className}">
				 <g:link action="show"
					id="\${${propertyName}.id}">
					\${fieldValue(bean:${propertyName}, field:'id')}</g:link>
				</div>
				</td>

				<%      } else if(i < 6) { %>
				<td>\${fieldValue(bean:${propertyName}, field:'${p.name}')}</td>
				<%  }   } %>
			</tr>
		</g:each>
	</tbody>
</table>
</div>
<div class="paginateButtons"><g:paginate
	total="\${${propertyName}Total}" /></div>
</div>
</body>
</html>
