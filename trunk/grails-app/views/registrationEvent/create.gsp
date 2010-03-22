
<%@ page import="com.grailsPortal.domain.RegistrationEvent" %>
        <div class="body">
            <h1>Create RegistrationEvent</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${registrationEventInstance}">
            <div class="errors">
                <g:renderErrors bean="${registrationEventInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="registrationFor">Registration For:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:registrationEventInstance,field:'registrationFor','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.Party.list()}" name="registrationFor.id" value="${registrationEventInstance?.registrationFor?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="registrationUser">Registration User:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:registrationEventInstance,field:'registrationUser','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.JsecUser.list()}" name="registrationUser.id" value="${registrationEventInstance?.registrationUser?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="registrationDate">Registration Date:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:registrationEventInstance,field:'registrationDate','errors')}">
                                    <g:datePicker name="registrationDate" value="${registrationEventInstance?.registrationDate}" precision="minute" noSelection="['':'']"></g:datePicker>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="registrationGrade">Registration Grade:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:registrationEventInstance,field:'registrationGrade','errors')}">
                                    <input type="text" id="registrationGrade" name="registrationGrade" value="${fieldValue(bean:registrationEventInstance,field:'registrationGrade')}"/>
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Save" /></span>
                </div>
            </g:form>
