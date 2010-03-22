
<%@ page import="com.grailsPortal.domain.PartyRelationship" %>
        <div class="body">
            <h1>Create PartyRelationship</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${partyRelationshipInstance}">
            <div class="errors">
                <g:renderErrors bean="${partyRelationshipInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="child">Child:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyRelationshipInstance,field:'child','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.Party.list()}" name="child.id" value="${partyRelationshipInstance?.child?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="relationshipType">Relationship Type:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyRelationshipInstance,field:'relationshipType','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.RelationshipType.list()}" name="relationshipType.id" value="${partyRelationshipInstance?.relationshipType?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="parent">Parent:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:partyRelationshipInstance,field:'parent','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.Party.list()}" name="parent.id" value="${partyRelationshipInstance?.parent?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Save" /></span>
                </div>
            </g:form>
