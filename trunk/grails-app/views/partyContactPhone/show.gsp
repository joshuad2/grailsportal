
<%@ page import="com.grailsPortal.domain.PartyContactPhone" %>
        <div class="body">
            <h1>Show PartyContactPhone</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:partyContactPhoneInstance, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Party:</td>
                            
                            <td valign="top" class="value"><div id="theShow"><g:remoteLink controller="party" action="show" update="show" id="${partyContactPhoneInstance?.party?.id}">${partyContactPhoneInstance?.party?.encodeAsHTML()}</g:remoteLink></div></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Phone:</td>
                            
                            <td valign="top" class="value"><div id="theShow"><g:remoteLink controller="contactPhone" action="show" update="show" id="${partyContactPhoneInstance?.phone?.id}">${partyContactPhoneInstance?.phone?.encodeAsHTML()}</g:remoteLink></div></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Contact Type:</td>
                            
                            <td valign="top" class="value"><div id="theShow"><g:remoteLink controller="contactType" action="show" update="show" id="${partyContactPhoneInstance?.contactType?.id}">${partyContactPhoneInstance?.contactType?.encodeAsHTML()}</g:remoteLink></div></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Active:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:partyContactPhoneInstance, field:'active')}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${partyContactPhoneInstance?.id}" />
                    <div id="theEdit" style="float:left;padding:.8em .8em .8em .8em;font-size:14px;">
                             <g:remoteLink action="edit" id="${partyContactPhoneInstance.id}" update="edit">Edit</g:remoteLink>
                         </div>
                      </span>
                    <div style="float:left;padding:.8em .8em .8em .8em;font-size:14px;">
                      <g:link class="delete" onclick="return confirm('Are you sure?');" value="Delete" >Delete</g:link>
                    </div>
                </g:form>
            </div>
         <gui:dialog title="PartyContactPhone" 
           modal="true" form="false"
           triggers="[show:[id:'theShow', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="show" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
         <gui:dialog title="PartyContactPhone" 
           modal="true" form="false"
           triggers="[show:[id:'theEdit', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="edit" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 