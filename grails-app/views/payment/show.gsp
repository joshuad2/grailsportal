
<%@ page import="com.grailsPortal.domain.Payment" %>
        <div class="body">
            <h1>Show Payment</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:paymentInstance, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Display Order:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:paymentInstance, field:'displayOrder')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Payment Amount:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:paymentInstance, field:'paymentAmount')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Payment Date:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:paymentInstance, field:'paymentDate')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Subscription:</td>
                            
                            <td valign="top" class="value"><div id="theShow"><g:remoteLink controller="subscription" action="show" update="show" id="${paymentInstance?.subscription?.id}">${paymentInstance?.subscription?.encodeAsHTML()}</g:remoteLink></div></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Party:</td>
                            
                            <td valign="top" class="value"><div id="theShow"><g:remoteLink controller="party" action="show" update="show" id="${paymentInstance?.party?.id}">${paymentInstance?.party?.encodeAsHTML()}</g:remoteLink></div></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Payment Method:</td>
                            
                            <td valign="top" class="value"><div id="theShow"><g:remoteLink controller="paymentMethodType" action="show" update="show" id="${paymentInstance?.paymentMethod?.id}">${paymentInstance?.paymentMethod?.encodeAsHTML()}</g:remoteLink></div></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Payment Type:</td>
                            
                            <td valign="top" class="value"><div id="theShow"><g:remoteLink controller="paymentType" action="show" update="show" id="${paymentInstance?.paymentType?.id}">${paymentInstance?.paymentType?.encodeAsHTML()}</g:remoteLink></div></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Inventory Order:</td>
                            
                            <td valign="top" class="value"><div id="theShow"><g:remoteLink controller="inventoryOrder" action="show" update="show" id="${paymentInstance?.inventoryOrder?.id}">${paymentInstance?.inventoryOrder?.encodeAsHTML()}</g:remoteLink></div></td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${paymentInstance?.id}" />
                    <div id="theEdit" style="float:left;padding:.8em .8em .8em .8em;font-size:14px;">
                             <g:remoteLink action="edit" id="${paymentInstance.id}" update="edit">Edit</g:remoteLink>
                         </div>
                      </span>
                    <div style="float:left;padding:.8em .8em .8em .8em;font-size:14px;">
                      <g:link class="delete" onclick="return confirm('Are you sure?');" value="Delete" >Delete</g:link>
                    </div>
                </g:form>
            </div>
         <gui:dialog title="Payment" 
           modal="true" form="false"
           triggers="[show:[id:'theShow', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="show" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
         <gui:dialog title="Payment" 
           modal="true" form="false"
           triggers="[show:[id:'theEdit', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="edit" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 