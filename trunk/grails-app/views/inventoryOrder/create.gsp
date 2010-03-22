
<%@ page import="com.grailsPortal.domain.InventoryOrder" %>
        <div class="body">
            <h1>Create InventoryOrder</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${inventoryOrderInstance}">
            <div class="errors">
                <g:renderErrors bean="${inventoryOrderInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="orderNumber">Order Number:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:inventoryOrderInstance,field:'orderNumber','errors')}">
                                    <input type="text" id="orderNumber" name="orderNumber" value="${fieldValue(bean:inventoryOrderInstance,field:'orderNumber')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="expectedArrivalDate">Expected Arrival Date:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:inventoryOrderInstance,field:'expectedArrivalDate','errors')}">
                                    <g:datePicker name="expectedArrivalDate" value="${inventoryOrderInstance?.expectedArrivalDate}" precision="minute" ></g:datePicker>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="orderComment">Order Comment:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:inventoryOrderInstance,field:'orderComment','errors')}">
                                    <textarea rows="5" cols="40" name="orderComment">${fieldValue(bean:inventoryOrderInstance, field:'orderComment')}</textarea>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="shipDate">Ship Date:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:inventoryOrderInstance,field:'shipDate','errors')}">
                                    <g:datePicker name="shipDate" value="${inventoryOrderInstance?.shipDate}" precision="minute" ></g:datePicker>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="shippingComment">Shipping Comment:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:inventoryOrderInstance,field:'shippingComment','errors')}">
                                    <textarea rows="5" cols="40" name="shippingComment">${fieldValue(bean:inventoryOrderInstance, field:'shippingComment')}</textarea>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="shippingCost">Shipping Cost:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:inventoryOrderInstance,field:'shippingCost','errors')}">
                                    <input type="text" id="shippingCost" name="shippingCost" value="${fieldValue(bean:inventoryOrderInstance,field:'shippingCost')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="shippingTrackingNum">Shipping Tracking Num:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:inventoryOrderInstance,field:'shippingTrackingNum','errors')}">
                                    <textarea rows="5" cols="40" name="shippingTrackingNum">${fieldValue(bean:inventoryOrderInstance, field:'shippingTrackingNum')}</textarea>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="shippingEmail">Shipping Email:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:inventoryOrderInstance,field:'shippingEmail','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.ContactEmail.list()}" name="shippingEmail.id" value="${inventoryOrderInstance?.shippingEmail?.id}" noSelection="['null':'']"></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="inventoryItem">Inventory Item:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:inventoryOrderInstance,field:'inventoryItem','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.InventoryItem.list()}" name="inventoryItem.id" value="${inventoryOrderInstance?.inventoryItem?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="shippingAddress">Shipping Address:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:inventoryOrderInstance,field:'shippingAddress','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.ContactAddress.list()}" name="shippingAddress.id" value="${inventoryOrderInstance?.shippingAddress?.id}" noSelection="['null':'']"></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="shippingPhone">Shipping Phone:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:inventoryOrderInstance,field:'shippingPhone','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.ContactPhone.list()}" name="shippingPhone.id" value="${inventoryOrderInstance?.shippingPhone?.id}" noSelection="['null':'']"></g:select>
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Save" /></span>
                </div>
            </g:form>
