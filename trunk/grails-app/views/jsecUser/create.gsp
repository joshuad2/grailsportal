
<%@ page import="com.grailsPortal.domain.JsecUser" %>
        <div class="body">
            <h1>Create JsecUser</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${jsecUserInstance}">
            <div class="errors">
                <g:renderErrors bean="${jsecUserInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="username">Username:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:jsecUserInstance,field:'username','errors')}">
                                    <input type="text" id="username" name="username" value="${fieldValue(bean:jsecUserInstance,field:'username')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="passwordHash">Password Hash:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:jsecUserInstance,field:'passwordHash','errors')}">
                                    <input type="text" id="passwordHash" name="passwordHash" value="${fieldValue(bean:jsecUserInstance,field:'passwordHash')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="party">Party:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:jsecUserInstance,field:'party','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.Party.list()}" name="party.id" value="${jsecUserInstance?.party?.id}" noSelection="['null':'']"></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="active">Active:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:jsecUserInstance,field:'active','errors')}">
                                    <g:checkBox name="active" value="${jsecUserInstance?.active}" ></g:checkBox>
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Save" /></span>
                </div>
            </g:form>
