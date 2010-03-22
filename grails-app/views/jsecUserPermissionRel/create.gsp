
<%@ page import="com.grailsPortal.domain.JsecUserPermissionRel" %>
        <div class="body">
            <h1>Create JsecUserPermissionRel</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${jsecUserPermissionRelInstance}">
            <div class="errors">
                <g:renderErrors bean="${jsecUserPermissionRelInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="target">Target:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:jsecUserPermissionRelInstance,field:'target','errors')}">
                                    <input type="text" id="target" name="target" value="${fieldValue(bean:jsecUserPermissionRelInstance,field:'target')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="actions">Actions:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:jsecUserPermissionRelInstance,field:'actions','errors')}">
                                    <input type="text" id="actions" name="actions" value="${fieldValue(bean:jsecUserPermissionRelInstance,field:'actions')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="permission">Permission:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:jsecUserPermissionRelInstance,field:'permission','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.JsecPermission.list()}" name="permission.id" value="${jsecUserPermissionRelInstance?.permission?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="user">User:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:jsecUserPermissionRelInstance,field:'user','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.JsecUser.list()}" name="user.id" value="${jsecUserPermissionRelInstance?.user?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Save" /></span>
                </div>
            </g:form>
