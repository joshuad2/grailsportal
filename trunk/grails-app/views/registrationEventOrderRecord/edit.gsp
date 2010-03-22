
<%@ page import="com.grailsPortal.domain.RegistrationEventOrderRecord" %>
        <div class="body">
            <h1>Edit RegistrationEventOrderRecord</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${registrationEventOrderRecordInstance}">
            <div class="errors">
                <g:renderErrors bean="${registrationEventOrderRecordInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${registrationEventOrderRecordInstance?.id}" />
                <input type="hidden" name="version" value="${registrationEventOrderRecordInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="event">Event:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:registrationEventOrderRecordInstance,field:'event','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.RegistrationEvent.list()}" name="event.id" value="${registrationEventOrderRecordInstance?.event?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="order">Order:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:registrationEventOrderRecordInstance,field:'order','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.OrderRecord.list()}" name="order.id" value="${registrationEventOrderRecordInstance?.order?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="isActive">Is Active:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:registrationEventOrderRecordInstance,field:'isActive','errors')}">
                                    <g:checkBox name="isActive" value="${registrationEventOrderRecordInstance?.isActive}" ></g:checkBox>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="comment">Comment:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:registrationEventOrderRecordInstance,field:'comment','errors')}">
                                    <textarea rows="5" cols="40" name="comment">${fieldValue(bean:registrationEventOrderRecordInstance, field:'comment')}</textarea>
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
