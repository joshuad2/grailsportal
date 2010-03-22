
<%@ page import="com.grailsPortal.domain.Subscription" %>
        <div class="body">
            <h1>Edit Subscription</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${subscriptionInstance}">
            <div class="errors">
                <g:renderErrors bean="${subscriptionInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${subscriptionInstance?.id}" />
                <input type="hidden" name="version" value="${subscriptionInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="active">Active:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:subscriptionInstance,field:'active','errors')}">
                                    <textarea rows="5" cols="40" name="active">${fieldValue(bean:subscriptionInstance, field:'active')}</textarea>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="endDate">End Date:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:subscriptionInstance,field:'endDate','errors')}">
                                    <g:datePicker name="endDate" value="${subscriptionInstance?.endDate}" precision="minute" ></g:datePicker>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="paidInFull">Paid In Full:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:subscriptionInstance,field:'paidInFull','errors')}">
                                    <textarea rows="5" cols="40" name="paidInFull">${fieldValue(bean:subscriptionInstance, field:'paidInFull')}</textarea>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="renewDate">Renew Date:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:subscriptionInstance,field:'renewDate','errors')}">
                                    <g:datePicker name="renewDate" value="${subscriptionInstance?.renewDate}" precision="minute" ></g:datePicker>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="startDate">Start Date:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:subscriptionInstance,field:'startDate','errors')}">
                                    <g:datePicker name="startDate" value="${subscriptionInstance?.startDate}" precision="minute" ></g:datePicker>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="totalAmount">Total Amount:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:subscriptionInstance,field:'totalAmount','errors')}">
                                    <input type="text" id="totalAmount" name="totalAmount" value="${fieldValue(bean:subscriptionInstance,field:'totalAmount')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="totalPaid">Total Paid:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:subscriptionInstance,field:'totalPaid','errors')}">
                                    <input type="text" id="totalPaid" name="totalPaid" value="${fieldValue(bean:subscriptionInstance,field:'totalPaid')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="orderRecord">Order Record:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:subscriptionInstance,field:'orderRecord','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.OrderRecord.list()}" name="orderRecord.id" value="${subscriptionInstance?.orderRecord?.id}" ></g:select>
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
