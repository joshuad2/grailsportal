
<%@ page import="com.grailsPortal.domain.OrderRecordLineItem" %>
        <div class="body">
            <h1>Edit OrderRecordLineItem</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${orderRecordLineItemInstance}">
            <div class="errors">
                <g:renderErrors bean="${orderRecordLineItemInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${orderRecordLineItemInstance?.id}" />
                <input type="hidden" name="version" value="${orderRecordLineItemInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="amountPurchased">Amount Purchased:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:orderRecordLineItemInstance,field:'amountPurchased','errors')}">
                                    <g:select from="${1..100}" id="amountPurchased" name="amountPurchased" value="${orderRecordLineItemInstance?.amountPurchased}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="lineItemAmount">Line Item Amount:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:orderRecordLineItemInstance,field:'lineItemAmount','errors')}">
                                    <input type="text" id="lineItemAmount" name="lineItemAmount" value="${fieldValue(bean:orderRecordLineItemInstance,field:'lineItemAmount')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="lineItemNumber">Line Item Number:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:orderRecordLineItemInstance,field:'lineItemNumber','errors')}">
                                    <g:select from="${1..100}" id="lineItemNumber" name="lineItemNumber" value="${orderRecordLineItemInstance?.lineItemNumber}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="product">Product:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:orderRecordLineItemInstance,field:'product','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.Product.list()}" name="product.id" value="${orderRecordLineItemInstance?.product?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="orderRecord">Order Record:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:orderRecordLineItemInstance,field:'orderRecord','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.OrderRecord.list()}" name="orderRecord.id" value="${orderRecordLineItemInstance?.orderRecord?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="lineItemDate">Line Item Date:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:orderRecordLineItemInstance,field:'lineItemDate','errors')}">
                                    <g:datePicker name="lineItemDate" value="${orderRecordLineItemInstance?.lineItemDate}" precision="minute" ></g:datePicker>
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
