
<%@ page import="com.grailsPortal.domain.ContactEmail" %>
        <div class="body">
            <h1>Edit ContactEmail</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${contactEmailInstance}">
            <div class="errors">
                <g:renderErrors bean="${contactEmailInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" action="saveContactEmail" >
                <input type="hidden" name="id" value="${contactEmailInstance?.id}" />
                <input type="hidden" name="version" value="${contactEmailInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="emailAddress">Email Address:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:contactEmailInstance,field:'emailAddress','errors')}">
                                    <input type="text" maxlength="200" id="emailAddress" name="emailAddress" value="${fieldValue(bean:contactEmailInstance,field:'emailAddress')}"/>
                                </td>
                            </tr>                         
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" value="Update" action="saveContactEmail"/></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" action="deleteContactEmail" /></span>
                </div>
            </g:form>
        </div>
