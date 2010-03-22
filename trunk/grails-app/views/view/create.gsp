
<%@ page import="com.grailsPortal.domain.View" %>
        <div class="body">
            <h1>Create View</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${viewInstance}">
            <div class="errors">
                <g:renderErrors bean="${viewInstance}" as="list" />
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
                                <td valign="top" class="value ${hasErrors(bean:viewInstance,field:'name','errors')}">
                                    <input type="text" maxlength="100" id="name" name="name" value="${fieldValue(bean:viewInstance,field:'name')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="viewName">View Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:viewInstance,field:'viewName','errors')}">
                                    <input type="text" maxlength="200" id="viewName" name="viewName" value="${fieldValue(bean:viewInstance,field:'viewName')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="actionName">Action Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:viewInstance,field:'actionName','errors')}">
                                    <input type="text" maxlength="100" id="actionName" name="actionName" value="${fieldValue(bean:viewInstance,field:'actionName')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="title">Title:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:viewInstance,field:'title','errors')}">
                                    <input type="text" maxlength="100" id="title" name="title" value="${fieldValue(bean:viewInstance,field:'title')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="portalConfigBusinessProcess">Portal Config Business Process:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:viewInstance,field:'portalConfigBusinessProcess','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.PortalConfigBusinessProcess.list()}" name="portalConfigBusinessProcess.id" value="${viewInstance?.portalConfigBusinessProcess?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="displayOrder">Display Order:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:viewInstance,field:'displayOrder','errors')}">
                                    <g:select from="${1..200}" id="displayOrder" name="displayOrder" value="${viewInstance?.displayOrder}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="template">Template:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:viewInstance,field:'template','errors')}">
                                    <input type="text" maxlength="100" id="template" name="template" value="${fieldValue(bean:viewInstance,field:'template')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="dsc">Dsc:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:viewInstance,field:'dsc','errors')}">
                                    <input type="text" maxlength="200" id="dsc" name="dsc" value="${fieldValue(bean:viewInstance,field:'dsc')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="rawHtmlHeader">Raw Html Header:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:viewInstance,field:'rawHtmlHeader','errors')}">
                                    <textarea rows="5" cols="40" name="rawHtmlHeader">${fieldValue(bean:viewInstance, field:'rawHtmlHeader')}</textarea>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="rawHtmlStartBody">Raw Html Start Body:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:viewInstance,field:'rawHtmlStartBody','errors')}">
                                    <input type="text" maxlength="200" id="rawHtmlStartBody" name="rawHtmlStartBody" value="${fieldValue(bean:viewInstance,field:'rawHtmlStartBody')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="rawHtmlEndBody">Raw Html End Body:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:viewInstance,field:'rawHtmlEndBody','errors')}">
                                    <input type="text" maxlength="200" id="rawHtmlEndBody" name="rawHtmlEndBody" value="${fieldValue(bean:viewInstance,field:'rawHtmlEndBody')}"/>
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Save" /></span>
                </div>
            </g:form>
