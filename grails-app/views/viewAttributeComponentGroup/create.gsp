
<%@ page import="com.grailsPortal.domain.ViewAttributeComponentGroup" %>
        <div class="body">
            <h1>Create ViewAttributeComponentGroup</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${viewAttributeComponentGroupInstance}">
            <div class="errors">
                <g:renderErrors bean="${viewAttributeComponentGroupInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name">Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:viewAttributeComponentGroupInstance,field:'name','errors')}">
                                    <input type="text" maxlength="100" id="name" name="name" value="${fieldValue(bean:viewAttributeComponentGroupInstance,field:'name')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="displayOrder">Display Order:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:viewAttributeComponentGroupInstance,field:'displayOrder','errors')}">
                                    <input type="text" id="displayOrder" name="displayOrder" value="${fieldValue(bean:viewAttributeComponentGroupInstance,field:'displayOrder')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="view">View:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:viewAttributeComponentGroupInstance,field:'view','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.View.list()}" name="view.id" value="${viewAttributeComponentGroupInstance?.view?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="active">Active:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:viewAttributeComponentGroupInstance,field:'active','errors')}">
                                    <g:checkBox name="active" value="${viewAttributeComponentGroupInstance?.active}" ></g:checkBox>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="mandatory">Mandatory:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:viewAttributeComponentGroupInstance,field:'mandatory','errors')}">
                                    <g:checkBox name="mandatory" value="${viewAttributeComponentGroupInstance?.mandatory}" ></g:checkBox>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="attributeComponentGroup">Attribute Component Group:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:viewAttributeComponentGroupInstance,field:'attributeComponentGroup','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.AttributeComponentGroup.list()}" name="attributeComponentGroup.id" value="${viewAttributeComponentGroupInstance?.attributeComponentGroup?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Save" /></span>
                </div>
            </g:form>
