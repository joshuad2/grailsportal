
<%@ page import="com.grailsPortal.domain.PortalConfigBusinessProcess" %>
        <div class="body">
            <h1>Create PortalConfigBusinessProcess</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${portalConfigBusinessProcessInstance}">
            <div class="errors">
                <g:renderErrors bean="${portalConfigBusinessProcessInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="portalConfig">Portal Config:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:portalConfigBusinessProcessInstance,field:'portalConfig','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.PortalConfig.list()}" name="portalConfig.id" value="${portalConfigBusinessProcessInstance?.portalConfig?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name">Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:portalConfigBusinessProcessInstance,field:'name','errors')}">
                                    <input type="text" maxlength="100" id="name" name="name" value="${fieldValue(bean:portalConfigBusinessProcessInstance,field:'name')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="purpose">Purpose:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:portalConfigBusinessProcessInstance,field:'purpose','errors')}">
                                    <input type="text" maxlength="200" id="purpose" name="purpose" value="${fieldValue(bean:portalConfigBusinessProcessInstance,field:'purpose')}"/>
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Save" /></span>
                </div>
            </g:form>
