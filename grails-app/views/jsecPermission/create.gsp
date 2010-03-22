
<%@ page import="com.grailsPortal.domain.JsecPermission" %>
        <div class="body">
            <h1>Create JsecPermission</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${jsecPermissionInstance}">
            <div class="errors">
                <g:renderErrors bean="${jsecPermissionInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="type">Type:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:jsecPermissionInstance,field:'type','errors')}">
                                    <input type="text" id="type" name="type" value="${fieldValue(bean:jsecPermissionInstance,field:'type')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="possibleActions">Possible Actions:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:jsecPermissionInstance,field:'possibleActions','errors')}">
                                    <input type="text" id="possibleActions" name="possibleActions" value="${fieldValue(bean:jsecPermissionInstance,field:'possibleActions')}"/>
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Save" /></span>
                </div>
            </g:form>
