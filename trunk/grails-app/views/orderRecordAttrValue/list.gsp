
<%@ page import="com.grailsPortal.domain.OrderRecordAttrValue" %>
<html>
    <head>
		<meta name="layout" content="main" />
		<gui:resources components="dialog" />
		<g:javascript library="prototype" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton" id="createTheOrderRecordAttrValue">
                 <g:remoteLink action="create" update="createOrderRecordAttrValue">New</g:remoteLink>
            </span>
        </div>
        <div class="body">
            <h1 style="margin-left:20px;">West Orlando Arts Foundation  - OrderRecordAttrValue </h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="showTheOrderRecordAttrValue">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="attrValue" title="Attr Value" />
                        
                   	        <th>Order Record</th>
                   	    
                   	        <th>Attribute</th>
                   	    
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${orderRecordAttrValueInstanceList}" status="i" var="orderRecordAttrValueInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>
                              <div id="linkOrderRecordAttrValue">
                                   <g:remoteLink action="show" id="${orderRecordAttrValueInstance.id}" update="showOrderRecordAttrValue">${fieldValue(bean:orderRecordAttrValueInstance, field:'id')}</g:remoteLink>
                              </div>
                           </td>
                            
                        
                            <td>${fieldValue(bean:orderRecordAttrValueInstance, field:'attrValue')}</td>
                        
                            <td>${fieldValue(bean:orderRecordAttrValueInstance, field:'orderRecord')}</td>
                        
                            <td>${fieldValue(bean:orderRecordAttrValueInstance, field:'attribute')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${orderRecordAttrValueInstanceTotal}" />
            </div>
        </div>
        <gui:dialog title="OrderRecordAttrValue" 
           modal="true" form="false"
           triggers="[show:[id:'showTheOrderRecordAttrValue', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="showOrderRecordAttrValue" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
        <gui:dialog title="OrderRecordAttrValue" 
           modal="true" form="false"
           triggers="[show:[id:'createTheOrderRecordAttrValue', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="createOrderRecordAttrValue" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
    </body>
</html>
