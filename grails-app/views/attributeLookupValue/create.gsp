
<%@ page import="com.grailsPortal.domain.AttributeLookupValue" %>
        <div class="body">
            <h1>Create AttributeLookupValue</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${attributeLookupValueInstance}">
            <div class="errors">
                <g:renderErrors bean="${attributeLookupValueInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="lookupValue">Lookup Value:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:attributeLookupValueInstance,field:'lookupValue','errors')}">
                                    <input type="text" id="lookupValue" name="lookupValue" value="${fieldValue(bean:attributeLookupValueInstance,field:'lookupValue')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="attribute">Attribute:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:attributeLookupValueInstance,field:'attribute','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.Attribute.list()}" name="attribute.id" value="${attributeLookupValueInstance?.attribute?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="displayOrder">Display Order:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:attributeLookupValueInstance,field:'displayOrder','errors')}">
                                    <input type="text" id="displayOrder" name="displayOrder" value="${fieldValue(bean:attributeLookupValueInstance,field:'displayOrder')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="imagePath">Image Path:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:attributeLookupValueInstance,field:'imagePath','errors')}">
                                    <input type="text" id="imagePath" name="imagePath" value="${fieldValue(bean:attributeLookupValueInstance,field:'imagePath')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="cssClass">Css Class:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:attributeLookupValueInstance,field:'cssClass','errors')}">
                                    <input type="text" id="cssClass" name="cssClass" value="${fieldValue(bean:attributeLookupValueInstance,field:'cssClass')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="cssStyle">Css Style:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:attributeLookupValueInstance,field:'cssStyle','errors')}">
                                    <input type="text" id="cssStyle" name="cssStyle" value="${fieldValue(bean:attributeLookupValueInstance,field:'cssStyle')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="active">Active:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:attributeLookupValueInstance,field:'active','errors')}">
                                    <g:checkBox name="active" value="${attributeLookupValueInstance?.active}" ></g:checkBox>
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Save" /></span>
                </div>
            </g:form>
