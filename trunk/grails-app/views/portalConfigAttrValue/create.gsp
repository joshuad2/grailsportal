
<%@ page import="com.grailsPortal.domain.PortalConfigAttrValue" %>
        <div class="body">
            <h1>Create PortalConfigAttrValue</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${portalConfigAttrValueInstance}">
            <div class="errors">
                <g:renderErrors bean="${portalConfigAttrValueInstance}" as="list" />
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
                                <td valign="top" class="value ${hasErrors(bean:portalConfigAttrValueInstance,field:'attrValue','errors')}">
                                    <input type="text" maxlength="200" id="attrValue" name="attrValue" value="${fieldValue(bean:portalConfigAttrValueInstance,field:'attrValue')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="portalConfig">Portal Config:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:portalConfigAttrValueInstance,field:'portalConfig','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.PortalConfig.list()}" name="portalConfig.id" value="${portalConfigAttrValueInstance?.portalConfig?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="attribute">Attribute:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:portalConfigAttrValueInstance,field:'attribute','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.Attribute.list()}" name="attribute.id" value="${portalConfigAttrValueInstance?.attribute?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Save" /></span>
                </div>
            </g:form>