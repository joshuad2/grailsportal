
<%@ page import="com.grailsPortal.domain.JsecRolePermissionRel" %>
        <div class="body">
            <h1>Edit JsecRolePermissionRel</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${jsecRolePermissionRelInstance}">
            <div class="errors">
                <g:renderErrors bean="${jsecRolePermissionRelInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${jsecRolePermissionRelInstance?.id}" />
                <input type="hidden" name="version" value="${jsecRolePermissionRelInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="actions">Actions:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:jsecRolePermissionRelInstance,field:'actions','errors')}">
                                    <input type="text" id="actions" name="actions" value="${fieldValue(bean:jsecRolePermissionRelInstance,field:'actions')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="target">Target:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:jsecRolePermissionRelInstance,field:'target','errors')}">
                                    <input type="text" id="target" name="target" value="${fieldValue(bean:jsecRolePermissionRelInstance,field:'target')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="permission">Permission:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:jsecRolePermissionRelInstance,field:'permission','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.JsecPermission.list()}" name="permission.id" value="${jsecRolePermissionRelInstance?.permission?.id}" noSelection="['null':'']"></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="role">Role:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:jsecRolePermissionRelInstance,field:'role','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.JsecRole.list()}" name="role.id" value="${jsecRolePermissionRelInstance?.role?.id}" noSelection="['null':'']"></g:select>
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
