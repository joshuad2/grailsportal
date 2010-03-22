
<%@ page import="com.grailsPortal.domain.InventoryItem" %>
        <div class="body">
            <h1>Create InventoryItem</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${inventoryItemInstance}">
            <div class="errors">
                <g:renderErrors bean="${inventoryItemInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="cd">Cd:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:inventoryItemInstance,field:'cd','errors')}">
                                    <input type="text" maxlength="20" id="cd" name="cd" value="${fieldValue(bean:inventoryItemInstance,field:'cd')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="contenturipath">Contenturipath:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:inventoryItemInstance,field:'contenturipath','errors')}">
                                    <textarea rows="5" cols="40" name="contenturipath">${fieldValue(bean:inventoryItemInstance, field:'contenturipath')}</textarea>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="dsc">Dsc:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:inventoryItemInstance,field:'dsc','errors')}">
                                    <input type="text" maxlength="200" id="dsc" name="dsc" value="${fieldValue(bean:inventoryItemInstance,field:'dsc')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="imageuripath">Imageuripath:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:inventoryItemInstance,field:'imageuripath','errors')}">
                                    <textarea rows="5" cols="40" name="imageuripath">${fieldValue(bean:inventoryItemInstance, field:'imageuripath')}</textarea>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name">Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:inventoryItemInstance,field:'name','errors')}">
                                    <input type="text" maxlength="100" id="name" name="name" value="${fieldValue(bean:inventoryItemInstance,field:'name')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="numberOfItemInStock">Number Of Item In Stock:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:inventoryItemInstance,field:'numberOfItemInStock','errors')}">
                                    <input type="text" id="numberOfItemInStock" name="numberOfItemInStock" value="${fieldValue(bean:inventoryItemInstance,field:'numberOfItemInStock')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="retailCost">Retail Cost:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:inventoryItemInstance,field:'retailCost','errors')}">
                                    <input type="text" id="retailCost" name="retailCost" value="${fieldValue(bean:inventoryItemInstance,field:'retailCost')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="warningInvLevel">Warning Inv Level:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:inventoryItemInstance,field:'warningInvLevel','errors')}">
                                    <input type="text" id="warningInvLevel" name="warningInvLevel" value="${fieldValue(bean:inventoryItemInstance,field:'warningInvLevel')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="wholesaleCost">Wholesale Cost:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:inventoryItemInstance,field:'wholesaleCost','errors')}">
                                    <input type="text" id="wholesaleCost" name="wholesaleCost" value="${fieldValue(bean:inventoryItemInstance,field:'wholesaleCost')}" />
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Save" /></span>
                </div>
            </g:form>
