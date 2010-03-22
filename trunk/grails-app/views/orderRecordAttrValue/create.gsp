
<%@ page import="com.grailsPortal.domain.OrderRecordAttrValue" %>
        <div class="body">
            <h1>Create OrderRecordAttrValue</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${orderRecordAttrValueInstance}">
            <div class="errors">
                <g:renderErrors bean="${orderRecordAttrValueInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="attrValue">Attr Value:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:orderRecordAttrValueInstance,field:'attrValue','errors')}">
                                    <textarea rows="5" cols="40" name="attrValue">${fieldValue(bean:orderRecordAttrValueInstance, field:'attrValue')}</textarea>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="orderRecord">Order Record:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:orderRecordAttrValueInstance,field:'orderRecord','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.OrderRecord.list()}" name="orderRecord.id" value="${orderRecordAttrValueInstance?.orderRecord?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="attribute">Attribute:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:orderRecordAttrValueInstance,field:'attribute','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.Attribute.list()}" name="attribute.id" value="${orderRecordAttrValueInstance?.attribute?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Save" /></span>
                </div>
            </g:form>
