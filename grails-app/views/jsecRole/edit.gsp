
<%@ page import="com.grailsPortal.domain.JsecRole" %>
        <div class="body">
            <h1>Edit JsecRole</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${jsecRoleInstance}">
            <div class="errors">
                <g:renderErrors bean="${jsecRoleInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${jsecRoleInstance?.id}" />
                <input type="hidden" name="version" value="${jsecRoleInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name">Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:jsecRoleInstance,field:'name','errors')}">
                                    <input type="text" id="name" name="name" value="${fieldValue(bean:jsecRoleInstance,field:'name')}"/>
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
