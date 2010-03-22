
<%@ page import="com.grailsPortal.domain.Subscription" %>
        <div class="body">
            <h1>Show Subscription</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:subscriptionInstance, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Active:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:subscriptionInstance, field:'active')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">End Date:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:subscriptionInstance, field:'endDate')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Paid In Full:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:subscriptionInstance, field:'paidInFull')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Renew Date:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:subscriptionInstance, field:'renewDate')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Start Date:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:subscriptionInstance, field:'startDate')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Total Amount:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:subscriptionInstance, field:'totalAmount')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Total Paid:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:subscriptionInstance, field:'totalPaid')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Order Record:</td>
                            
                            <td valign="top" class="value"><div id="theShow"><g:remoteLink controller="orderRecord" action="show" update="show" id="${subscriptionInstance?.orderRecord?.id}">${subscriptionInstance?.orderRecord?.encodeAsHTML()}</g:remoteLink></div></td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${subscriptionInstance?.id}" />
                    <div id="theEdit" style="float:left;padding:.8em .8em .8em .8em;font-size:14px;">
                             <g:remoteLink action="edit" id="${subscriptionInstance.id}" update="edit">Edit</g:remoteLink>
                         </div>
                      </span>
                    <div style="float:left;padding:.8em .8em .8em .8em;font-size:14px;">
                      <g:link class="delete" onclick="return confirm('Are you sure?');" value="Delete" >Delete</g:link>
                    </div>
                </g:form>
            </div>
         <gui:dialog title="Subscription" 
           modal="true" form="false"
           triggers="[show:[id:'theShow', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="show" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
         <gui:dialog title="Subscription" 
           modal="true" form="false"
           triggers="[show:[id:'theEdit', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="edit" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 