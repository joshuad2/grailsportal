
<%@ page import="com.grailsPortal.domain.ProductType" %>
        <div class="body">
            <h1>Edit ProductType</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${productTypeInstance}">
            <div class="errors">
                <g:renderErrors bean="${productTypeInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${productTypeInstance?.id}" />
                <input type="hidden" name="version" value="${productTypeInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="cd">Cd:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:productTypeInstance,field:'cd','errors')}">
                                    <input type="text" maxlength="20" id="cd" name="cd" value="${fieldValue(bean:productTypeInstance,field:'cd')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="dsc">Dsc:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:productTypeInstance,field:'dsc','errors')}">
                                    <input type="text" maxlength="200" id="dsc" name="dsc" value="${fieldValue(bean:productTypeInstance,field:'dsc')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name">Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:productTypeInstance,field:'name','errors')}">
                                    <input type="text" maxlength="100" id="name" name="name" value="${fieldValue(bean:productTypeInstance,field:'name')}"/>
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