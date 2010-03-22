
<%@ page import="com.grailsPortal.domain.RegistrationEvent" %>
        <div class="body">
            <h1>Edit RegistrationEvent</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${registrationEventInstance}">
            <div class="errors">
                <g:renderErrors bean="${registrationEventInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${registrationEventInstance?.id}" />
                <input type="hidden" name="version" value="${registrationEventInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="registrationFor">Registration For:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:registrationEventInstance,field:'registrationFor','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.Party.list()}" name="registrationFor.id" value="${registrationEventInstance?.registrationFor?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="registrationUser">Registration User:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:registrationEventInstance,field:'registrationUser','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.JsecUser.list()}" name="registrationUser.id" value="${registrationEventInstance?.registrationUser?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="registrationDate">Registration Date:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:registrationEventInstance,field:'registrationDate','errors')}">
                                    <g:datePicker name="registrationDate" value="${registrationEventInstance?.registrationDate}" precision="minute" noSelection="['':'']"></g:datePicker>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="contacts">Contacts:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:registrationEventInstance,field:'contacts','errors')}">
                                    
<ul>
<g:each var="c" in="${registrationEventInstance?.contacts?}">
    <li><g:link controller="registrationEventParty" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="registrationEventParty" params="['registrationEvent.id':registrationEventInstance?.id]" action="create">Add RegistrationEventParty</g:link>

                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="orders">Orders:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:registrationEventInstance,field:'orders','errors')}">
                                    
<ul>
<g:each var="o" in="${registrationEventInstance?.orders?}">
    <li><g:link controller="registrationEventOrderRecord" action="show" id="${o.id}">${o?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="registrationEventOrderRecord" params="['registrationEvent.id':registrationEventInstance?.id]" action="create">Add RegistrationEventOrderRecord</g:link>

                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="attrValues">Attr Values:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:registrationEventInstance,field:'attrValues','errors')}">
                                    
<ul>
<g:each var="a" in="${registrationEventInstance?.attrValues?}">
    <li><g:link controller="registrationEventAttrValue" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="registrationEventAttrValue" params="['registrationEvent.id':registrationEventInstance?.id]" action="create">Add RegistrationEventAttrValue</g:link>

                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="registrationGrade">Registration Grade:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:registrationEventInstance,field:'registrationGrade','errors')}">
                                    <input type="text" id="registrationGrade" name="registrationGrade" value="${fieldValue(bean:registrationEventInstance,field:'registrationGrade')}"/>
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
