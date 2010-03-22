
<%@ page import="com.grailsPortal.domain.JsecUserRoleRel" %>
        <div class="body">
            <h1>Create JsecUserRoleRel</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${jsecUserRoleRelInstance}">
            <div class="errors">
                <g:renderErrors bean="${jsecUserRoleRelInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="user">User:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:jsecUserRoleRelInstance,field:'user','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.JsecUser.list()}" name="user.id" value="${jsecUserRoleRelInstance?.user?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="role">Role:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:jsecUserRoleRelInstance,field:'role','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.JsecRole.list()}" name="role.id" value="${jsecUserRoleRelInstance?.role?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Save" /></span>
                </div>
            </g:form>
