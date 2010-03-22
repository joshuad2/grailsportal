
<%@ page import="com.grailsPortal.domain.AttributeComponent" %>
        <div class="body">
            <h1>Edit AttributeComponent</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${attributeComponentInstance}">
            <div class="errors">
                <g:renderErrors bean="${attributeComponentInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${attributeComponentInstance?.id}" />
                <input type="hidden" name="version" value="${attributeComponentInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name">Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:attributeComponentInstance,field:'name','errors')}">
                                    <input type="text" maxlength="100" id="name" name="name" value="${fieldValue(bean:attributeComponentInstance,field:'name')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="attribute">Attribute:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:attributeComponentInstance,field:'attribute','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.Attribute.list()}" name="attribute.id" value="${attributeComponentInstance?.attribute?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="component">Component:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:attributeComponentInstance,field:'component','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.Component.list()}" name="component.id" value="${attributeComponentInstance?.component?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="labelText">Label Text:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:attributeComponentInstance,field:'labelText','errors')}">
                                    <input type="text" maxlength="200" id="labelText" name="labelText" value="${fieldValue(bean:attributeComponentInstance,field:'labelText')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="labelCssStyle">Label Css Style:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:attributeComponentInstance,field:'labelCssStyle','errors')}">
                                    <input type="text" maxlength="200" id="labelCssStyle" name="labelCssStyle" value="${fieldValue(bean:attributeComponentInstance,field:'labelCssStyle')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="labelCssClass">Label Css Class:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:attributeComponentInstance,field:'labelCssClass','errors')}">
                                    <input type="text" maxlength="200" id="labelCssClass" name="labelCssClass" value="${fieldValue(bean:attributeComponentInstance,field:'labelCssClass')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="displayOrder">Display Order:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:attributeComponentInstance,field:'displayOrder','errors')}">
                                    <input type="text" id="displayOrder" name="displayOrder" value="${fieldValue(bean:attributeComponentInstance,field:'displayOrder')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="cssStyle">Css Style:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:attributeComponentInstance,field:'cssStyle','errors')}">
                                    <textarea rows="5" cols="40" name="cssStyle">${fieldValue(bean:attributeComponentInstance, field:'cssStyle')}</textarea>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="cssClass">Css Class:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:attributeComponentInstance,field:'cssClass','errors')}">
                                    <input type="text" maxlength="100" id="cssClass" name="cssClass" value="${fieldValue(bean:attributeComponentInstance,field:'cssClass')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="cssId">Css Id:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:attributeComponentInstance,field:'cssId','errors')}">
                                    <input type="text" maxlength="20" id="cssId" name="cssId" value="${fieldValue(bean:attributeComponentInstance,field:'cssId')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="active">Active:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:attributeComponentInstance,field:'active','errors')}">
                                    <g:checkBox name="active" value="${attributeComponentInstance?.active}" ></g:checkBox>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="attributeComponentGroup">Attribute Component Group:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:attributeComponentInstance,field:'attributeComponentGroup','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.AttributeComponentGroup.list()}" name="attributeComponentGroup.id" value="${attributeComponentInstance?.attributeComponentGroup?.id}" noSelection="['null':'']"></g:select>
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
