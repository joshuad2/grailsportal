
<%@ page import="com.grailsPortal.domain.Attribute" %>
        <div class="body">
            <h1>Edit Attribute</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${attributeInstance}">
            <div class="errors">
                <g:renderErrors bean="${attributeInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${attributeInstance?.id}" />
                <input type="hidden" name="version" value="${attributeInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="cd">Cd:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:attributeInstance,field:'cd','errors')}">
                                    <input type="text" maxlength="20" id="cd" name="cd" value="${fieldValue(bean:attributeInstance,field:'cd')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="dsc">Dsc:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:attributeInstance,field:'dsc','errors')}">
                                    <input type="text" maxlength="200" id="dsc" name="dsc" value="${fieldValue(bean:attributeInstance,field:'dsc')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name">Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:attributeInstance,field:'name','errors')}">
                                    <input type="text" maxlength="100" id="name" name="name" value="${fieldValue(bean:attributeInstance,field:'name')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="attributeDataType">Attribute Data Type:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:attributeInstance,field:'attributeDataType','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.AttributeDataType.list()}" name="attributeDataType.id" value="${attributeInstance?.attributeDataType?.id}" noSelection="['null':'']"></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="attributeType">Attribute Type:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:attributeInstance,field:'attributeType','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.AttributeType.list()}" name="attributeType.id" value="${attributeInstance?.attributeType?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="maximumWidth">Maximum Width:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:attributeInstance,field:'maximumWidth','errors')}">
                                    <input type="text" id="maximumWidth" name="maximumWidth" value="${fieldValue(bean:attributeInstance,field:'maximumWidth')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="pathToAttribute">Path To Attribute:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:attributeInstance,field:'pathToAttribute','errors')}">
                                    <input type="text" maxlength="100" id="pathToAttribute" name="pathToAttribute" value="${fieldValue(bean:attributeInstance,field:'pathToAttribute')}"/>
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
