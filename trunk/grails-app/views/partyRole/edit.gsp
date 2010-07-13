
<%@ page import="com.grailsPortal.domain.PartyRole" %>
        <div class="body">
            <h1>Edit PartyRole</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${partyRoleInstance}">
            <div class="errors">
                <g:renderErrors bean="${partyRoleInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${partyRoleInstance?.id}" />
                <input type="hidden" name="version" value="${partyRoleInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name">Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyRoleInstance,field:'name','errors')}">
                                    <input type="text" maxlength="100" id="name" name="name" value="${fieldValue(bean:partyRoleInstance,field:'name')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="cd">Cd:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyRoleInstance,field:'cd','errors')}">
                                    <input type="text" maxlength="20" id="cd" name="cd" value="${fieldValue(bean:partyRoleInstance,field:'cd')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="dsc">Dsc:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyRoleInstance,field:'dsc','errors')}">
                                    <input type="text" maxlength="200" id="dsc" name="dsc" value="${fieldValue(bean:partyRoleInstance,field:'dsc')}"/>
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