
<%@ page import="com.grailsPortal.domain.BusinessProcessStep" %>
        <div class="body">
            <h1>Edit BusinessProcessStep</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${businessProcessStepInstance}">
            <div class="errors">
                <g:renderErrors bean="${businessProcessStepInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${businessProcessStepInstance?.id}" />
                <input type="hidden" name="version" value="${businessProcessStepInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="cd">Cd:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:businessProcessStepInstance,field:'cd','errors')}">
                                    <input type="text" maxlength="20" id="cd" name="cd" value="${fieldValue(bean:businessProcessStepInstance,field:'cd')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name">Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:businessProcessStepInstance,field:'name','errors')}">
                                    <input type="text" maxlength="100" id="name" name="name" value="${fieldValue(bean:businessProcessStepInstance,field:'name')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="dsc">Dsc:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:businessProcessStepInstance,field:'dsc','errors')}">
                                    <input type="text" maxlength="200" id="dsc" name="dsc" value="${fieldValue(bean:businessProcessStepInstance,field:'dsc')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="views">Views:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:businessProcessStepInstance,field:'views','errors')}">
                                    <g:select name="views"
from="${com.grailsPortal.domain.View.list()}"
size="5" multiple="yes" optionKey="id"
value="${businessProcessStepInstance?.views}" />

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
