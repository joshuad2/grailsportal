
<%@ page import="com.grailsPortal.domain.AttributeType" %>
        <div class="body">
            <h1>Create AttributeType</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${attributeTypeInstance}">
            <div class="errors">
                <g:renderErrors bean="${attributeTypeInstance}" as="list" />
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
                                <td valign="top" class="value ${hasErrors(bean:attributeTypeInstance,field:'name','errors')}">
                                    <input type="text" maxlength="100" id="name" name="name" value="${fieldValue(bean:attributeTypeInstance,field:'name')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="cd">Cd:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:attributeTypeInstance,field:'cd','errors')}">
                                    <input type="text" maxlength="20" id="cd" name="cd" value="${fieldValue(bean:attributeTypeInstance,field:'cd')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="dsc">Dsc:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:attributeTypeInstance,field:'dsc','errors')}">
                                    <input type="text" maxlength="200" id="dsc" name="dsc" value="${fieldValue(bean:attributeTypeInstance,field:'dsc')}"/>
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Save" /></span>
                </div>
            </g:form>
