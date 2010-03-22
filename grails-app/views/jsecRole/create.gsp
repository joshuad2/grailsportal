
<%@ page import="com.grailsPortal.domain.JsecRole" %>
        <div class="body">
            <h1>Create JsecRole</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${jsecRoleInstance}">
            <div class="errors">
                <g:renderErrors bean="${jsecRoleInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
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
                    <span class="button"><input class="save" type="submit" value="Save" /></span>
                </div>
            </g:form>
