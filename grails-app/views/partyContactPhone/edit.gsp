
<%@ page import="com.grailsPortal.domain.PartyContactPhone" %>
        <div class="body">
            <h1>Edit PartyContactPhone</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${partyContactPhoneInstance}">
            <div class="errors">
                <g:renderErrors bean="${partyContactPhoneInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${partyContactPhoneInstance?.id}" />
                <input type="hidden" name="version" value="${partyContactPhoneInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="party">Party:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyContactPhoneInstance,field:'party','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.Party.list()}" name="party.id" value="${partyContactPhoneInstance?.party?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="phone">Phone:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyContactPhoneInstance,field:'phone','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.ContactPhone.list()}" name="phone.id" value="${partyContactPhoneInstance?.phone?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="contactType">Contact Type:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyContactPhoneInstance,field:'contactType','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.ContactType.list()}" name="contactType.id" value="${partyContactPhoneInstance?.contactType?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="active">Active:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyContactPhoneInstance,field:'active','errors')}">
                                    <g:checkBox name="active" value="${partyContactPhoneInstance?.active}" ></g:checkBox>
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
