
<%@ page import="com.grailsPortal.domain.PortalConfigAttribute" %>
        <div class="body">
            <h1>Edit PortalConfigAttribute</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${portalConfigAttributeInstance}">
            <div class="errors">
                <g:renderErrors bean="${portalConfigAttributeInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${portalConfigAttributeInstance?.id}" />
                <input type="hidden" name="version" value="${portalConfigAttributeInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="attribute">Attribute:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:portalConfigAttributeInstance,field:'attribute','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.Attribute.list()}" name="attribute.id" value="${portalConfigAttributeInstance?.attribute?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="mandatory">Mandatory:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:portalConfigAttributeInstance,field:'mandatory','errors')}">
                                    <g:checkBox name="mandatory" value="${portalConfigAttributeInstance?.mandatory}" ></g:checkBox>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="portalConfig">Portal Config:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:portalConfigAttributeInstance,field:'portalConfig','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.PortalConfig.list()}" name="portalConfig.id" value="${portalConfigAttributeInstance?.portalConfig?.id}" ></g:select>
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
