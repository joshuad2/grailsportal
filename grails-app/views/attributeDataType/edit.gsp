
<%@ page import="com.grailsPortal.domain.AttributeDataType" %>
        <div class="body">
            <h1>Edit AttributeDataType</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${attributeDataTypeInstance}">
            <div class="errors">
                <g:renderErrors bean="${attributeDataTypeInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${attributeDataTypeInstance?.id}" />
                <input type="hidden" name="version" value="${attributeDataTypeInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name">Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:attributeDataTypeInstance,field:'name','errors')}">
                                    <input type="text" maxlength="40" id="name" name="name" value="${fieldValue(bean:attributeDataTypeInstance,field:'name')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="dsc">Dsc:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:attributeDataTypeInstance,field:'dsc','errors')}">
                                    <input type="text" maxlength="100" id="dsc" name="dsc" value="${fieldValue(bean:attributeDataTypeInstance,field:'dsc')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="groovyType">Groovy Type:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:attributeDataTypeInstance,field:'groovyType','errors')}">
                                    <input type="text" maxlength="100" id="groovyType" name="groovyType" value="${fieldValue(bean:attributeDataTypeInstance,field:'groovyType')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="size">Size:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:attributeDataTypeInstance,field:'size','errors')}">
                                    <input type="text" maxlength="100" id="size" name="size" value="${fieldValue(bean:attributeDataTypeInstance,field:'size')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="htmlStartTag">Html Start Tag:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:attributeDataTypeInstance,field:'htmlStartTag','errors')}">
                                    <input type="text" maxlength="200" id="htmlStartTag" name="htmlStartTag" value="${fieldValue(bean:attributeDataTypeInstance,field:'htmlStartTag')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="htmlEndTag">Html End Tag:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:attributeDataTypeInstance,field:'htmlEndTag','errors')}">
                                    <input type="text" maxlength="200" id="htmlEndTag" name="htmlEndTag" value="${fieldValue(bean:attributeDataTypeInstance,field:'htmlEndTag')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="isFromCMS">Is From CMS:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:attributeDataTypeInstance,field:'isFromCMS','errors')}">
                                    <g:checkBox name="isFromCMS" value="${attributeDataTypeInstance?.isFromCMS}" ></g:checkBox>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="cmsContentType">Cms Content Type:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:attributeDataTypeInstance,field:'cmsContentType','errors')}">
                                    <input type="text" maxlength="200" id="cmsContentType" name="cmsContentType" value="${fieldValue(bean:attributeDataTypeInstance,field:'cmsContentType')}"/>
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
