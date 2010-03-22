
<%@ page import="com.grailsPortal.domain.RegistrationEventAttrValue" %>
        <div class="body">
            <h1>Create RegistrationEventAttrValue</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${registrationEventAttrValueInstance}">
            <div class="errors">
                <g:renderErrors bean="${registrationEventAttrValueInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="value">Value:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:registrationEventAttrValueInstance,field:'value','errors')}">
                                    <input type="text" maxlength="100" id="value" name="value" value="${fieldValue(bean:registrationEventAttrValueInstance,field:'value')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="registrationEvent">Registration Event:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:registrationEventAttrValueInstance,field:'registrationEvent','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.RegistrationEvent.list()}" name="registrationEvent.id" value="${registrationEventAttrValueInstance?.registrationEvent?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="attribute">Attribute:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:registrationEventAttrValueInstance,field:'attribute','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.Attribute.list()}" name="attribute.id" value="${registrationEventAttrValueInstance?.attribute?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Save" /></span>
                </div>
            </g:form>
