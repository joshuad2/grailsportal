
<%@ page import="com.grailsPortal.domain.PortalConfigBusinessProcess" %>
        <div class="body">
            <h1>Edit PortalConfigBusinessProcess</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${portalConfigBusinessProcessInstance}">
            <div class="errors">
                <g:renderErrors bean="${portalConfigBusinessProcessInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${portalConfigBusinessProcessInstance?.id}" />
                <input type="hidden" name="version" value="${portalConfigBusinessProcessInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="portalConfig">Portal Config:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:portalConfigBusinessProcessInstance,field:'portalConfig','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.PortalConfig.list()}" name="portalConfig.id" value="${portalConfigBusinessProcessInstance?.portalConfig?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name">Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:portalConfigBusinessProcessInstance,field:'name','errors')}">
                                    <input type="text" maxlength="100" id="name" name="name" value="${fieldValue(bean:portalConfigBusinessProcessInstance,field:'name')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="purpose">Purpose:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:portalConfigBusinessProcessInstance,field:'purpose','errors')}">
                                    <input type="text" maxlength="200" id="purpose" name="purpose" value="${fieldValue(bean:portalConfigBusinessProcessInstance,field:'purpose')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="views">Views:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:portalConfigBusinessProcessInstance,field:'views','errors')}">
                                    
<ul>
<g:each var="v" in="${portalConfigBusinessProcessInstance?.views?}">
    <li><g:link controller="view" action="show" id="${v.id}">${v?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="view" params="['portalConfigBusinessProcess.id':portalConfigBusinessProcessInstance?.id]" action="create">Add View</g:link>

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
