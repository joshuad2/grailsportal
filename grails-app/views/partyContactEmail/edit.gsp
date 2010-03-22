
<%@ page import="com.grailsPortal.domain.PartyContactEmail" %>
        <div class="body">
            <h1>Edit PartyContactEmail</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${partyContactEmailInstance}">
            <div class="errors">
                <g:renderErrors bean="${partyContactEmailInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${partyContactEmailInstance?.id}" />
                <input type="hidden" name="version" value="${partyContactEmailInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="party">Party:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyContactEmailInstance,field:'party','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.Party.list()}" name="party.id" value="${partyContactEmailInstance?.party?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="email">Email:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyContactEmailInstance,field:'email','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.ContactEmail.list()}" name="email.id" value="${partyContactEmailInstance?.email?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="contactType">Contact Type:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyContactEmailInstance,field:'contactType','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.ContactType.list()}" name="contactType.id" value="${partyContactEmailInstance?.contactType?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="active">Active:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyContactEmailInstance,field:'active','errors')}">
                                    <g:checkBox name="active" value="${partyContactEmailInstance?.active}" ></g:checkBox>
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
