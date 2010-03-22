
<%@ page import="com.grailsPortal.domain.ContactEmail" %>
        <div class="body">
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${contactEmailInstance}">
            <div class="errors">
                <g:renderErrors bean="${contactEmailInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="saveContactEmail" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="emailAddress">Email Address:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:contactEmailInstance,field:'emailAddress','errors')}">
                                    <g:hiddenField name="partyId" value="${partyId}"/>
                                    <g:hiddenField name="contactType" value="${contactType}"/>
                                    <input type="text" maxlength="200" id="emailAddress" name="emailAddress" value="${fieldValue(bean:contactEmailInstance,field:'emailAddress')}"/>
                                </td>
                            </tr>                         
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Save" /></span>
                </div>
            </g:form>
