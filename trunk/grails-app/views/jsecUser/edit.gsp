
<%@ page import="com.grailsPortal.domain.JsecUser" %>
        <div class="body">
            <h1>Edit JsecUser</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${jsecUserInstance}">
            <div class="errors">
                <g:renderErrors bean="${jsecUserInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${jsecUserInstance?.id}" />
                <input type="hidden" name="version" value="${jsecUserInstance?.version}" />
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
                                    <label for="attributeValues">Attribute Values:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:jsecUserInstance,field:'attributeValues','errors')}">
                                    
<ul>
<g:each var="a" in="${jsecUserInstance?.attributeValues?}">
    <li><g:link controller="jsecUserAttributeValue" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="jsecUserAttributeValue" params="['jsecUser.id':jsecUserInstance?.id]" action="create">Add JsecUserAttributeValue</g:link>

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
                    <span class="button"><g:actionSubmit class="save" value="Update" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </div>
            </g:form>
        </div>
