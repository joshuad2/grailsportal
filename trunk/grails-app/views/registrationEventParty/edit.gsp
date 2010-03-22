
<%@ page import="com.grailsPortal.domain.RegistrationEventParty" %>
        <div class="body">
            <h1>Edit RegistrationEventParty</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${registrationEventPartyInstance}">
            <div class="errors">
                <g:renderErrors bean="${registrationEventPartyInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${registrationEventPartyInstance?.id}" />
                <input type="hidden" name="version" value="${registrationEventPartyInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="party">Party:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:registrationEventPartyInstance,field:'party','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.Party.list()}" name="party.id" value="${registrationEventPartyInstance?.party?.id}" ></g:select><shiro:hasAllRoles in="['Administrator']">
<div id="createTheParty">
    <g:remoteLink controller="party" id="" action="create" update="createParty">Add</g:remoteLink>
</div>
           <gui:dialog title="Add a Party" modal="true" form="false" triggers="[show:[id:'createTheParty', on:'click']]" fixedCenter="true"]>
             <div class="dialog" id="createParty" style="width:600px;height:400px;overflow:scroll"></div>
           </gui:dialog></shiro:hasAllRoles>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="registrationEvent">Registration Event:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:registrationEventPartyInstance,field:'registrationEvent','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.RegistrationEvent.list()}" name="registrationEvent.id" value="${registrationEventPartyInstance?.registrationEvent?.id}" ></g:select><shiro:hasAllRoles in="['Administrator']">
<div id="createTheRegistrationEvent">
    <g:remoteLink controller="registrationEvent" id="" action="create" update="createRegistrationEvent">Add</g:remoteLink>
</div>
           <gui:dialog title="Add a RegistrationEvent" modal="true" form="false" triggers="[show:[id:'createTheRegistrationEvent', on:'click']]" fixedCenter="true"]>
             <div class="dialog" id="createRegistrationEvent" style="width:600px;height:400px;overflow:scroll"></div>
           </gui:dialog></shiro:hasAllRoles>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="role">Role:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:registrationEventPartyInstance,field:'role','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.PartyRole.list()}" name="role.id" value="${registrationEventPartyInstance?.role?.id}" ></g:select><shiro:hasAllRoles in="['Administrator']">
<div id="createThePartyRole">
    <g:remoteLink controller="partyRole" id="" action="create" update="createPartyRole">Add</g:remoteLink>
</div>
           <gui:dialog title="Add a PartyRole" modal="true" form="false" triggers="[show:[id:'createThePartyRole', on:'click']]" fixedCenter="true"]>
             <div class="dialog" id="createPartyRole" style="width:600px;height:400px;overflow:scroll"></div>
           </gui:dialog></shiro:hasAllRoles>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="active">Active:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:registrationEventPartyInstance,field:'active','errors')}">
                                    <g:checkBox name="active" value="${registrationEventPartyInstance?.active}" ></g:checkBox>
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
