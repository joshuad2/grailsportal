
<%@ page import="com.grailsPortal.domain.AttributeComponentGroup" %>
        <div class="body">
            <h1>Edit AttributeComponentGroup</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${attributeComponentGroupInstance}">
            <div class="errors">
                <g:renderErrors bean="${attributeComponentGroupInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${attributeComponentGroupInstance?.id}" />
                <input type="hidden" name="version" value="${attributeComponentGroupInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name">Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:attributeComponentGroupInstance,field:'name','errors')}">
                                    <input type="text" maxlength="100" id="name" name="name" value="${fieldValue(bean:attributeComponentGroupInstance,field:'name')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="dsc">Dsc:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:attributeComponentGroupInstance,field:'dsc','errors')}">
                                    <input type="text" maxlength="200" id="dsc" name="dsc" value="${fieldValue(bean:attributeComponentGroupInstance,field:'dsc')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="cd">Cd:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:attributeComponentGroupInstance,field:'cd','errors')}">
                                    <input type="text" maxlength="20" id="cd" name="cd" value="${fieldValue(bean:attributeComponentGroupInstance,field:'cd')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="attributeComponents">Attribute Components:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:attributeComponentGroupInstance,field:'attributeComponents','errors')}">
                                    
<ul>
<g:each var="a" in="${attributeComponentGroupInstance?.attributeComponents?}">
    <li><g:link controller="attributeComponent" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="attributeComponent" params="['attributeComponentGroup.id':attributeComponentGroupInstance?.id]" action="create">Add AttributeComponent</g:link>

                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="active">Active:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:attributeComponentGroupInstance,field:'active','errors')}">
                                    <g:checkBox name="active" value="${attributeComponentGroupInstance?.active}" ></g:checkBox>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="mandatory">Mandatory:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:attributeComponentGroupInstance,field:'mandatory','errors')}">
                                    <g:checkBox name="mandatory" value="${attributeComponentGroupInstance?.mandatory}" ></g:checkBox>
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
