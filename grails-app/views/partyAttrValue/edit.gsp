
<%@ page import="com.grailsPortal.domain.PartyAttrValue" %>
        <div class="body">
            <h1>Edit PartyAttrValue</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${partyAttrValueInstance}">
            <div class="errors">
                <g:renderErrors bean="${partyAttrValueInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${partyAttrValueInstance?.id}" />
                <input type="hidden" name="version" value="${partyAttrValueInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="attrValue">Attr Value:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyAttrValueInstance,field:'attrValue','errors')}">
                                    <input type="text" maxlength="200" id="attrValue" name="attrValue" value="${fieldValue(bean:partyAttrValueInstance,field:'attrValue')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="party">Party:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyAttrValueInstance,field:'party','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.Party.list()}" name="party.id" value="${partyAttrValueInstance?.party?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="attribute">Attribute:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyAttrValueInstance,field:'attribute','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.Attribute.list()}" name="attribute.id" value="${partyAttrValueInstance?.attribute?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="displayOrder">Display Order:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyAttrValueInstance,field:'displayOrder','errors')}">
                                    <input type="text" id="displayOrder" name="displayOrder" value="${fieldValue(bean:partyAttrValueInstance,field:'displayOrder')}" />
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
