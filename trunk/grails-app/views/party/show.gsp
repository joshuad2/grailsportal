
<%@ page import="com.grailsPortal.domain.Party" %>
        <div class="body">
            <h1>Show Party</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:partyInstance, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Prefix:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:partyInstance, field:'prefix')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">First Name:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:partyInstance, field:'firstName')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Middle Name:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:partyInstance, field:'middleName')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Last Name:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:partyInstance, field:'lastName')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Suffix:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:partyInstance, field:'suffix')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Birth Date:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:partyInstance, field:'birthDate')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Party Type:</td>
                            
                            <td valign="top" class="value"><div id="theShow"><g:remoteLink controller="partyType" action="show" update="show" id="${partyInstance?.partyType?.id}">${partyInstance?.partyType?.encodeAsHTML()}</g:remoteLink></div></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Address List:</td>
                            
                            <td  valign="top" style="text-align:left;" class="value">
                                <ul>
                                <g:each var="a" in="${partyInstance.addressList}">
                                    <li><div id="theShow"><g:remoteLink controller="partyContactAddress" action="show" update="show" id="${a.id}">${a?.encodeAsHTML()}</g:remoteLink></div></li>
                                </g:each>
                                </ul>
                            </td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Phone List:</td>
                            
                            <td  valign="top" style="text-align:left;" class="value">
                                <ul>
                                <g:each var="p" in="${partyInstance.phoneList}">
                                    <li><div id="theShow"><g:remoteLink controller="partyContactPhone" action="show" update="show" id="${p.id}">${p?.encodeAsHTML()}</g:remoteLink></div></li>
                                </g:each>
                                </ul>
                            </td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Email List:</td>
                            
                            <td  valign="top" style="text-align:left;" class="value">
                                <ul>
                                <g:each var="e" in="${partyInstance.emailList}">
                                    <li><div id="theShow"><g:remoteLink controller="partyContactEmail" action="show" update="show" id="${e.id}">${e?.encodeAsHTML()}</g:remoteLink></div></li>
                                </g:each>
                                </ul>
                            </td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Order Record List:</td>
                            
                            <td  valign="top" style="text-align:left;" class="value">
                                <ul>
                                <g:each var="o" in="${partyInstance.orderRecordList}">
                                    <li><div id="theShow"><g:remoteLink controller="orderRecord" action="show" update="show" id="${o.id}">${o?.encodeAsHTML()}</g:remoteLink></div></li>
                                </g:each>
                                </ul>
                            </td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Party Attr Values:</td>
                            
                            <td  valign="top" style="text-align:left;" class="value">
                                <ul>
                                <g:each var="p" in="${partyInstance.partyAttrValues}">
                                    <li><div id="theShow"><g:remoteLink controller="partyAttrValue" action="show" update="show" id="${p.id}">${p?.encodeAsHTML()}</g:remoteLink></div></li>
                                </g:each>
                                </ul>
                            </td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${partyInstance?.id}" />
                    <div id="theEdit" style="float:left;padding:.8em .8em .8em .8em;font-size:14px;">
                             <g:remoteLink action="edit" id="${partyInstance.id}" update="edit">Edit</g:remoteLink>
                         </div>
                      </span>
                    <div style="float:left;padding:.8em .8em .8em .8em;font-size:14px;">
                      <g:link class="delete" onclick="return confirm('Are you sure?');" value="Delete" >Delete</g:link>
                    </div>
                </g:form>
            </div>
         <gui:dialog title="Party" 
           modal="true" form="false"
           triggers="[show:[id:'theShow', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="show" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
         <gui:dialog title="Party" 
           modal="true" form="false"
           triggers="[show:[id:'theEdit', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="edit" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 