
<%@ page import="com.grailsPortal.domain.ViewAttribute" %>
        <div class="body">
            <h1>Create ViewAttribute</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${viewAttributeInstance}">
            <div class="errors">
                <g:renderErrors bean="${viewAttributeInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="view">View:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:viewAttributeInstance,field:'view','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.View.list()}" name="view.id" value="${viewAttributeInstance?.view?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="attributeComponent">Attribute Component:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:viewAttributeInstance,field:'attributeComponent','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.AttributeComponent.list()}" name="attributeComponent.id" value="${viewAttributeInstance?.attributeComponent?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="displayOrder">Display Order:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:viewAttributeInstance,field:'displayOrder','errors')}">
                                    <g:select from="${1..100}" id="displayOrder" name="displayOrder" value="${viewAttributeInstance?.displayOrder}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="mandatory">Mandatory:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:viewAttributeInstance,field:'mandatory','errors')}">
                                    <g:checkBox name="mandatory" value="${viewAttributeInstance?.mandatory}" ></g:checkBox>
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Save" /></span>
                </div>
            </g:form>
