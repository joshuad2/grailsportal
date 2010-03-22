
<%@ page import="com.grailsPortal.domain.InventoryOrder" %>
        <div class="body">
            <h1>Show InventoryOrder</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:inventoryOrderInstance, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Order Number:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:inventoryOrderInstance, field:'orderNumber')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Expected Arrival Date:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:inventoryOrderInstance, field:'expectedArrivalDate')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Order Comment:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:inventoryOrderInstance, field:'orderComment')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Ship Date:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:inventoryOrderInstance, field:'shipDate')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Shipping Comment:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:inventoryOrderInstance, field:'shippingComment')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Shipping Cost:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:inventoryOrderInstance, field:'shippingCost')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Shipping Tracking Num:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:inventoryOrderInstance, field:'shippingTrackingNum')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Shipping Email:</td>
                            
                            <td valign="top" class="value"><div id="theShow"><g:remoteLink controller="contactEmail" action="show" update="show" id="${inventoryOrderInstance?.shippingEmail?.id}">${inventoryOrderInstance?.shippingEmail?.encodeAsHTML()}</g:remoteLink></div></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Inventory Item:</td>
                            
                            <td valign="top" class="value"><div id="theShow"><g:remoteLink controller="inventoryItem" action="show" update="show" id="${inventoryOrderInstance?.inventoryItem?.id}">${inventoryOrderInstance?.inventoryItem?.encodeAsHTML()}</g:remoteLink></div></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Shipping Address:</td>
                            
                            <td valign="top" class="value"><div id="theShow"><g:remoteLink controller="contactAddress" action="show" update="show" id="${inventoryOrderInstance?.shippingAddress?.id}">${inventoryOrderInstance?.shippingAddress?.encodeAsHTML()}</g:remoteLink></div></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Shipping Phone:</td>
                            
                            <td valign="top" class="value"><div id="theShow"><g:remoteLink controller="contactPhone" action="show" update="show" id="${inventoryOrderInstance?.shippingPhone?.id}">${inventoryOrderInstance?.shippingPhone?.encodeAsHTML()}</g:remoteLink></div></td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${inventoryOrderInstance?.id}" />
                    <div id="theEdit" style="float:left;padding:.8em .8em .8em .8em;font-size:14px;">
                             <g:remoteLink action="edit" id="${inventoryOrderInstance.id}" update="edit">Edit</g:remoteLink>
                         </div>
                      </span>
                    <div style="float:left;padding:.8em .8em .8em .8em;font-size:14px;">
                      <g:link class="delete" onclick="return confirm('Are you sure?');" value="Delete" >Delete</g:link>
                    </div>
                </g:form>
            </div>
         <gui:dialog title="InventoryOrder" 
           modal="true" form="false"
           triggers="[show:[id:'theShow', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="show" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
         <gui:dialog title="InventoryOrder" 
           modal="true" form="false"
           triggers="[show:[id:'theEdit', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="edit" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 