
<%@ page import="com.grailsPortal.domain.PartyAttrValue" %>
<html>
    <head>
		<meta name="layout" content="main" />
		<gui:resources components="dialog" />
		<g:javascript library="prototype" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton" id="createThePartyAttrValue">
                 <g:remoteLink action="create" update="createPartyAttrValue">New</g:remoteLink>
            </span>
        </div>
        <div class="body">
            <h1 style="margin-left:20px;">West Orlando Arts Foundation  - PartyAttrValue </h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="showThePartyAttrValue">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="attrValue" title="Attr Value" />
                        
                   	        <th>Party</th>
                   	    
                   	        <th>Attribute</th>
                   	    
                   	        <g:sortableColumn property="displayOrder" title="Display Order" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${partyAttrValueInstanceList}" status="i" var="partyAttrValueInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>
                              <div id="linkPartyAttrValue">
                                   <g:remoteLink action="show" id="${partyAttrValueInstance.id}" update="showPartyAttrValue">${fieldValue(bean:partyAttrValueInstance, field:'id')}</g:remoteLink>
                              </div>
                           </td>
                            
                        
                            <td>${fieldValue(bean:partyAttrValueInstance, field:'attrValue')}</td>
                        
                            <td>${fieldValue(bean:partyAttrValueInstance, field:'party')}</td>
                        
                            <td>${fieldValue(bean:partyAttrValueInstance, field:'attribute')}</td>
                        
                            <td>${fieldValue(bean:partyAttrValueInstance, field:'displayOrder')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${partyAttrValueInstanceTotal}" />
            </div>
        </div>
        <gui:dialog title="PartyAttrValue" 
           modal="true" form="false"
           triggers="[show:[id:'showThePartyAttrValue', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="showPartyAttrValue" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
        <gui:dialog title="PartyAttrValue" 
           modal="true" form="false"
           triggers="[show:[id:'createThePartyAttrValue', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="createPartyAttrValue" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
    </body>
</html>
