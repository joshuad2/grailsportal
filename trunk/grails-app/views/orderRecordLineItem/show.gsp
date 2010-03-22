
<%@ page import="com.grailsPortal.domain.OrderRecordLineItem" %>
        <div class="body">
            <h1>Show OrderRecordLineItem</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:orderRecordLineItemInstance, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Amount Purchased:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:orderRecordLineItemInstance, field:'amountPurchased')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Line Item Amount:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:orderRecordLineItemInstance, field:'lineItemAmount')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Line Item Number:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:orderRecordLineItemInstance, field:'lineItemNumber')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Product:</td>
                            
                            <td valign="top" class="value"><div id="theShow"><g:remoteLink controller="product" action="show" update="show" id="${orderRecordLineItemInstance?.product?.id}">${orderRecordLineItemInstance?.product?.encodeAsHTML()}</g:remoteLink></div></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Order Record:</td>
                            
                            <td valign="top" class="value"><div id="theShow"><g:remoteLink controller="orderRecord" action="show" update="show" id="${orderRecordLineItemInstance?.orderRecord?.id}">${orderRecordLineItemInstance?.orderRecord?.encodeAsHTML()}</g:remoteLink></div></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Line Item Date:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:orderRecordLineItemInstance, field:'lineItemDate')}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${orderRecordLineItemInstance?.id}" />
                    <div id="theEdit" style="float:left;padding:.8em .8em .8em .8em;font-size:14px;">
                             <g:remoteLink action="edit" id="${orderRecordLineItemInstance.id}" update="edit">Edit</g:remoteLink>
                         </div>
                      </span>
                    <div style="float:left;padding:.8em .8em .8em .8em;font-size:14px;">
                      <g:link class="delete" onclick="return confirm('Are you sure?');" value="Delete" >Delete</g:link>
                    </div>
                </g:form>
            </div>
         <gui:dialog title="OrderRecordLineItem" 
           modal="true" form="false"
           triggers="[show:[id:'theShow', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="show" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
         <gui:dialog title="OrderRecordLineItem" 
           modal="true" form="false"
           triggers="[show:[id:'theEdit', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="edit" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 