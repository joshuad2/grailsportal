
<%@ page import="com.grailsPortal.domain.OrderRecord" %>
        <div class="body">
            <h1>Show OrderRecord</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:orderRecordInstance, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Order Number:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:orderRecordInstance, field:'orderNumber')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Gross Amount:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:orderRecordInstance, field:'grossAmount')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Order Record Type:</td>
                            
                            <td valign="top" class="value"><div id="theShow"><g:remoteLink controller="orderRecordType" action="show" update="show" id="${orderRecordInstance?.orderRecordType?.id}">${orderRecordInstance?.orderRecordType?.encodeAsHTML()}</g:remoteLink></div></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Order Status:</td>
                            
                            <td valign="top" class="value"><div id="theShow"><g:remoteLink controller="orderStatus" action="show" update="show" id="${orderRecordInstance?.orderStatus?.id}">${orderRecordInstance?.orderStatus?.encodeAsHTML()}</g:remoteLink></div></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Party:</td>
                            
                            <td valign="top" class="value"><div id="theShow"><g:remoteLink controller="party" action="show" update="show" id="${orderRecordInstance?.party?.id}">${orderRecordInstance?.party?.encodeAsHTML()}</g:remoteLink></div></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Order Comment:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:orderRecordInstance, field:'orderComment')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Order Date:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:orderRecordInstance, field:'orderDate')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Tax Amount:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:orderRecordInstance, field:'taxAmount')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Total Amount:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:orderRecordInstance, field:'totalAmount')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Subscriptions:</td>
                            
                            <td  valign="top" style="text-align:left;" class="value">
                                <ul>
                                <g:each var="s" in="${orderRecordInstance.subscriptions}">
                                    <li><div id="theShow"><g:remoteLink controller="subscription" action="show" update="show" id="${s.id}">${s?.encodeAsHTML()}</g:remoteLink></div></li>
                                </g:each>
                                </ul>
                            </td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Registration Event Order Records:</td>
                            
                            <td  valign="top" style="text-align:left;" class="value">
                                <ul>
                                <g:each var="r" in="${orderRecordInstance.registrationEventOrderRecords}">
                                    <li><div id="theShow"><g:remoteLink controller="registrationEventOrderRecord" action="show" update="show" id="${r.id}">${r?.encodeAsHTML()}</g:remoteLink></div></li>
                                </g:each>
                                </ul>
                            </td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${orderRecordInstance?.id}" />
                    <div id="theEdit" style="float:left;padding:.8em .8em .8em .8em;font-size:14px;">
                             <g:remoteLink action="edit" id="${orderRecordInstance.id}" update="edit">Edit</g:remoteLink>
                         </div>
                      </span>
                    <div style="float:left;padding:.8em .8em .8em .8em;font-size:14px;">
                      <g:link class="delete" onclick="return confirm('Are you sure?');" value="Delete" >Delete</g:link>
                    </div>
                </g:form>
            </div>
         <gui:dialog title="OrderRecord" 
           modal="true" form="false"
           triggers="[show:[id:'theShow', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="show" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
         <gui:dialog title="OrderRecord" 
           modal="true" form="false"
           triggers="[show:[id:'theEdit', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="edit" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 