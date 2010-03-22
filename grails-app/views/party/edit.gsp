
<%@ page import="com.grailsPortal.domain.Party" %>
        <div class="body">
            <h1>Edit Party</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${partyInstance}">
            <div class="errors">
                <g:renderErrors bean="${partyInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${partyInstance?.id}" />
                <input type="hidden" name="version" value="${partyInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="prefix">Prefix:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyInstance,field:'prefix','errors')}">
                                    <input type="text" maxlength="20" id="prefix" name="prefix" value="${fieldValue(bean:partyInstance,field:'prefix')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="firstName">First Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyInstance,field:'firstName','errors')}">
                                    <input type="text" maxlength="100" id="firstName" name="firstName" value="${fieldValue(bean:partyInstance,field:'firstName')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="middleName">Middle Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyInstance,field:'middleName','errors')}">
                                    <input type="text" maxlength="50" id="middleName" name="middleName" value="${fieldValue(bean:partyInstance,field:'middleName')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="lastName">Last Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyInstance,field:'lastName','errors')}">
                                    <input type="text" maxlength="100" id="lastName" name="lastName" value="${fieldValue(bean:partyInstance,field:'lastName')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="suffix">Suffix:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyInstance,field:'suffix','errors')}">
                                    <input type="text" maxlength="20" id="suffix" name="suffix" value="${fieldValue(bean:partyInstance,field:'suffix')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="birthDate">Birth Date:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyInstance,field:'birthDate','errors')}">
                                    <g:datePicker name="birthDate" value="${partyInstance?.birthDate}" precision="minute" noSelection="['':'']"></g:datePicker>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="partyType">Party Type:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyInstance,field:'partyType','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.PartyType.list()}" name="partyType.id" value="${partyInstance?.partyType?.id}" noSelection="['null':'']"></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="addressList">Address List:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyInstance,field:'addressList','errors')}">
                                    
<ul>
<g:each var="a" in="${partyInstance?.addressList?}">
    <li><g:link controller="partyContactAddress" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="partyContactAddress" params="['party.id':partyInstance?.id]" action="create">Add PartyContactAddress</g:link>

                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="phoneList">Phone List:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyInstance,field:'phoneList','errors')}">
                                    
<ul>
<g:each var="p" in="${partyInstance?.phoneList?}">
    <li><g:link controller="partyContactPhone" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="partyContactPhone" params="['party.id':partyInstance?.id]" action="create">Add PartyContactPhone</g:link>

                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="emailList">Email List:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyInstance,field:'emailList','errors')}">
                                    
<ul>
<g:each var="e" in="${partyInstance?.emailList?}">
    <li><g:link controller="partyContactEmail" action="show" id="${e.id}">${e?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="partyContactEmail" params="['party.id':partyInstance?.id]" action="create">Add PartyContactEmail</g:link>

                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="orderRecordList">Order Record List:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyInstance,field:'orderRecordList','errors')}">
                                    
<ul>
<g:each var="o" in="${partyInstance?.orderRecordList?}">
    <li><g:link controller="orderRecord" action="show" id="${o.id}">${o?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="orderRecord" params="['party.id':partyInstance?.id]" action="create">Add OrderRecord</g:link>

                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="partyAttrValues">Party Attr Values:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyInstance,field:'partyAttrValues','errors')}">
                                    
<ul>
<g:each var="p" in="${partyInstance?.partyAttrValues?}">
    <li><g:link controller="partyAttrValue" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="partyAttrValue" params="['party.id':partyInstance?.id]" action="create">Add PartyAttrValue</g:link>

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
