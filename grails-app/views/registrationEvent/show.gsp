
<%@ page import="com.grailsPortal.domain.RegistrationEvent" %>
        <div class="body">
            <h1>Show RegistrationEvent</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:registrationEventInstance, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Registration For:</td>
                            
                            <td valign="top" class="value"><div id="theShow"><g:remoteLink controller="party" action="show" update="show" id="${registrationEventInstance?.registrationFor?.id}">${registrationEventInstance?.registrationFor?.encodeAsHTML()}</g:remoteLink></div></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Registration User:</td>
                            
                            <td valign="top" class="value"><div id="theShow"><g:remoteLink controller="jsecUser" action="show" update="show" id="${registrationEventInstance?.registrationUser?.id}">${registrationEventInstance?.registrationUser?.encodeAsHTML()}</g:remoteLink></div></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Registration Date:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:registrationEventInstance, field:'registrationDate')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Contacts:</td>
                            
                            <td  valign="top" style="text-align:left;" class="value">
                                <ul>
                                <g:each var="c" in="${registrationEventInstance.contacts}">
                                    <li><div id="theShow"><g:remoteLink controller="registrationEventParty" action="show" update="show" id="${c.id}">${c?.encodeAsHTML()}</g:remoteLink></div></li>
                                </g:each>
                                </ul>
                            </td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Orders:</td>
                            
                            <td  valign="top" style="text-align:left;" class="value">
                                <ul>
                                <g:each var="o" in="${registrationEventInstance.orders}">
                                    <li><div id="theShow"><g:remoteLink controller="registrationEventOrderRecord" action="show" update="show" id="${o.id}">${o?.encodeAsHTML()}</g:remoteLink></div></li>
                                </g:each>
                                </ul>
                            </td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Attr Values:</td>
                            
                            <td  valign="top" style="text-align:left;" class="value">
                                <ul>
                                <g:each var="a" in="${registrationEventInstance.attrValues}">
                                    <li><div id="theShow"><g:remoteLink controller="registrationEventAttrValue" action="show" update="show" id="${a.id}">${a?.encodeAsHTML()}</g:remoteLink></div></li>
                                </g:each>
                                </ul>
                            </td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Registration Grade:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:registrationEventInstance, field:'registrationGrade')}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${registrationEventInstance?.id}" />
                    <div id="theEdit" style="float:left;padding:.8em .8em .8em .8em;font-size:14px;">
                             <g:remoteLink action="edit" id="${registrationEventInstance.id}" update="edit">Edit</g:remoteLink>
                         </div>
                      </span>
                    <div style="float:left;padding:.8em .8em .8em .8em;font-size:14px;">
                      <g:link class="delete" onclick="return confirm('Are you sure?');" value="Delete" >Delete</g:link>
                    </div>
                </g:form>
            </div>
         <gui:dialog title="RegistrationEvent" 
           modal="true" form="false"
           triggers="[show:[id:'theShow', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="show" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
         <gui:dialog title="RegistrationEvent" 
           modal="true" form="false"
           triggers="[show:[id:'theEdit', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="edit" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 