
<%@ page import="com.grailsPortal.domain.Party" %>
        <div class="body">
            <h1>Create Party</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${partyInstance}">
            <div class="errors">
                <g:renderErrors bean="${partyInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
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
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Save" /></span>
                </div>
            </g:form>
