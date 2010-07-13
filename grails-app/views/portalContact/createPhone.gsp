
<%@ page import="com.grailsPortal.domain.ContactPhone" %>
        <div class="body">
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${contactPhoneInstance}">
            <div class="errors">
                <g:renderErrors bean="${contactPhoneInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="saveContactPhone" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <g:hiddenField name="partyId" value="${partyId}"/>
                                    <g:hiddenField name="contactType" value="${contactType}"/>
                                    <label for="areaCode">Area Code:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:contactPhoneInstance,field:'areaCode','errors')}">
                                    <input type="text" maxlength="4" id="areaCode" name="areaCode" value="${fieldValue(bean:contactPhoneInstance,field:'areaCode')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="phoneNumber">Phone Number:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:contactPhoneInstance,field:'phoneNumber','errors')}">
                                    <input type="text" maxlength="20" id="phoneNumber" name="phoneNumber" value="${fieldValue(bean:contactPhoneInstance,field:'phoneNumber')}"/>
                                </td>
                            </tr> 
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Save" /></span>
                </div>
            </g:form>