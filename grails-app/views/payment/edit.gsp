
<%@ page import="com.grailsPortal.domain.Payment" %>
        <div class="body">
            <h1>Edit Payment</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${paymentInstance}">
            <div class="errors">
                <g:renderErrors bean="${paymentInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${paymentInstance?.id}" />
                <input type="hidden" name="version" value="${paymentInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="displayOrder">Display Order:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:paymentInstance,field:'displayOrder','errors')}">
                                    <input type="text" id="displayOrder" name="displayOrder" value="${fieldValue(bean:paymentInstance,field:'displayOrder')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="paymentAmount">Payment Amount:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:paymentInstance,field:'paymentAmount','errors')}">
                                    <input type="text" id="paymentAmount" name="paymentAmount" value="${fieldValue(bean:paymentInstance,field:'paymentAmount')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="paymentDate">Payment Date:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:paymentInstance,field:'paymentDate','errors')}">
                                    <g:datePicker name="paymentDate" value="${paymentInstance?.paymentDate}" precision="minute" ></g:datePicker>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="subscription">Subscription:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:paymentInstance,field:'subscription','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.Subscription.list()}" name="subscription.id" value="${paymentInstance?.subscription?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="party">Party:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:paymentInstance,field:'party','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.Party.list()}" name="party.id" value="${paymentInstance?.party?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="paymentMethod">Payment Method:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:paymentInstance,field:'paymentMethod','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.PaymentMethodType.list()}" name="paymentMethod.id" value="${paymentInstance?.paymentMethod?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="paymentType">Payment Type:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:paymentInstance,field:'paymentType','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.PaymentType.list()}" name="paymentType.id" value="${paymentInstance?.paymentType?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="inventoryOrder">Inventory Order:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:paymentInstance,field:'inventoryOrder','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.InventoryOrder.list()}" name="inventoryOrder.id" value="${paymentInstance?.inventoryOrder?.id}" ></g:select>
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
