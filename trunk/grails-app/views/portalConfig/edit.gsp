
<%@ page import="com.grailsPortal.domain.PortalConfig" %>
        <div class="body">
            <h1>Edit PortalConfig</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${portalConfigInstance}">
            <div class="errors">
                <g:renderErrors bean="${portalConfigInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${portalConfigInstance?.id}" />
                <input type="hidden" name="version" value="${portalConfigInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name">Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:portalConfigInstance,field:'name','errors')}">
                                    <input type="text" maxlength="100" id="name" name="name" value="${fieldValue(bean:portalConfigInstance,field:'name')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="description">Description:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:portalConfigInstance,field:'description','errors')}">
                                    <input type="text" maxlength="200" id="description" name="description" value="${fieldValue(bean:portalConfigInstance,field:'description')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="portalAttributes">Portal Attributes:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:portalConfigInstance,field:'portalAttributes','errors')}">
                                    
<ul>
<g:each var="p" in="${portalConfigInstance?.portalAttributes?}">
    <li><g:link controller="portalConfigAttribute" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="portalConfigAttribute" params="['portalConfig.id':portalConfigInstance?.id]" action="create">Add PortalConfigAttribute</g:link>

                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="portalConfigValues">Portal Config Values:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:portalConfigInstance,field:'portalConfigValues','errors')}">
                                    
<ul>
<g:each var="p" in="${portalConfigInstance?.portalConfigValues?}">
    <li><g:link controller="portalConfigAttrValue" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="portalConfigAttrValue" params="['portalConfig.id':portalConfigInstance?.id]" action="create">Add PortalConfigAttrValue</g:link>

                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="portalConfigBusinessProcesses">Portal Config Business Processes:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:portalConfigInstance,field:'portalConfigBusinessProcesses','errors')}">
                                    
<ul>
<g:each var="p" in="${portalConfigInstance?.portalConfigBusinessProcesses?}">
    <li><g:link controller="portalConfigBusinessProcess" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="portalConfigBusinessProcess" params="['portalConfig.id':portalConfigInstance?.id]" action="create">Add PortalConfigBusinessProcess</g:link>

                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" value="Update" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </div>
            </g:form>
        </div>
