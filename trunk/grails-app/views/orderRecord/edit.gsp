
<%@ page import="com.grailsPortal.domain.OrderRecord" %>
        <div class="body">
            <h1>Edit OrderRecord</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${orderRecordInstance}">
            <div class="errors">
                <g:renderErrors bean="${orderRecordInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${orderRecordInstance?.id}" />
                <input type="hidden" name="version" value="${orderRecordInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="orderNumber">Order Number:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:orderRecordInstance,field:'orderNumber','errors')}">
                                    <input type="text" id="orderNumber" name="orderNumber" value="${fieldValue(bean:orderRecordInstance,field:'orderNumber')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="grossAmount">Gross Amount:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:orderRecordInstance,field:'grossAmount','errors')}">
                                    <input type="text" id="grossAmount" name="grossAmount" value="${fieldValue(bean:orderRecordInstance,field:'grossAmount')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="orderRecordType">Order Record Type:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:orderRecordInstance,field:'orderRecordType','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.OrderRecordType.list()}" name="orderRecordType.id" value="${orderRecordInstance?.orderRecordType?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="orderStatus">Order Status:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:orderRecordInstance,field:'orderStatus','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.OrderStatus.list()}" name="orderStatus.id" value="${orderRecordInstance?.orderStatus?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="party">Party:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:orderRecordInstance,field:'party','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.Party.list()}" name="party.id" value="${orderRecordInstance?.party?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="orderComment">Order Comment:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:orderRecordInstance,field:'orderComment','errors')}">
                                    <textarea rows="5" cols="40" name="orderComment">${fieldValue(bean:orderRecordInstance, field:'orderComment')}</textarea>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="orderDate">Order Date:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:orderRecordInstance,field:'orderDate','errors')}">
                                    <g:datePicker name="orderDate" value="${orderRecordInstance?.orderDate}" precision="minute" ></g:datePicker>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="taxAmount">Tax Amount:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:orderRecordInstance,field:'taxAmount','errors')}">
                                    <input type="text" id="taxAmount" name="taxAmount" value="${fieldValue(bean:orderRecordInstance,field:'taxAmount')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="totalAmount">Total Amount:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:orderRecordInstance,field:'totalAmount','errors')}">
                                    <input type="text" id="totalAmount" name="totalAmount" value="${fieldValue(bean:orderRecordInstance,field:'totalAmount')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="subscriptions">Subscriptions:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:orderRecordInstance,field:'subscriptions','errors')}">
                                    
<ul>
<g:each var="s" in="${orderRecordInstance?.subscriptions?}">
    <li><g:link controller="subscription" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="subscription" params="['orderRecord.id':orderRecordInstance?.id]" action="create">Add Subscription</g:link>

                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="registrationEventOrderRecords">Registration Event Order Records:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:orderRecordInstance,field:'registrationEventOrderRecords','errors')}">
                                    
<ul>
<g:each var="r" in="${orderRecordInstance?.registrationEventOrderRecords?}">
    <li><g:link controller="registrationEventOrderRecord" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="registrationEventOrderRecord" params="['orderRecord.id':orderRecordInstance?.id]" action="create">Add RegistrationEventOrderRecord</g:link>

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
