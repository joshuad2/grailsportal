
<%@ page import="com.grailsPortal.domain.PartyContactAddress" %>
        <div class="body">
            <h1>Create PartyContactAddress</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${partyContactAddressInstance}">
            <div class="errors">
                <g:renderErrors bean="${partyContactAddressInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="party">Party:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyContactAddressInstance,field:'party','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.Party.list()}" name="party.id" value="${partyContactAddressInstance?.party?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="address">Address:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyContactAddressInstance,field:'address','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.ContactAddress.list()}" name="address.id" value="${partyContactAddressInstance?.address?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="contactType">Contact Type:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyContactAddressInstance,field:'contactType','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.ContactType.list()}" name="contactType.id" value="${partyContactAddressInstance?.contactType?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="active">Active:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyContactAddressInstance,field:'active','errors')}">
                                    <g:checkBox name="active" value="${partyContactAddressInstance?.active}" ></g:checkBox>
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Save" /></span>
                </div>
            </g:form>
