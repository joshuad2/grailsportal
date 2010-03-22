
<%@ page import="com.grailsPortal.domain.ProductLineItem" %>
        <div class="body">
            <h1>Edit ProductLineItem</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${productLineItemInstance}">
            <div class="errors">
                <g:renderErrors bean="${productLineItemInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${productLineItemInstance?.id}" />
                <input type="hidden" name="version" value="${productLineItemInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="dsc">Dsc:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:productLineItemInstance,field:'dsc','errors')}">
                                    <input type="text" maxlength="200" id="dsc" name="dsc" value="${fieldValue(bean:productLineItemInstance,field:'dsc')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="grossCost">Gross Cost:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:productLineItemInstance,field:'grossCost','errors')}">
                                    <input type="text" id="grossCost" name="grossCost" value="${fieldValue(bean:productLineItemInstance,field:'grossCost')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="lineItemNumber">Line Item Number:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:productLineItemInstance,field:'lineItemNumber','errors')}">
                                    <g:select from="${1..200}" id="lineItemNumber" name="lineItemNumber" value="${productLineItemInstance?.lineItemNumber}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="isTaxable">Is Taxable:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:productLineItemInstance,field:'isTaxable','errors')}">
                                    <textarea rows="5" cols="40" name="isTaxable">${fieldValue(bean:productLineItemInstance, field:'isTaxable')}</textarea>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="netCost">Net Cost:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:productLineItemInstance,field:'netCost','errors')}">
                                    <input type="text" id="netCost" name="netCost" value="${fieldValue(bean:productLineItemInstance,field:'netCost')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="retailAmount">Retail Amount:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:productLineItemInstance,field:'retailAmount','errors')}">
                                    <input type="text" id="retailAmount" name="retailAmount" value="${fieldValue(bean:productLineItemInstance,field:'retailAmount')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="wholesaleAmount">Wholesale Amount:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:productLineItemInstance,field:'wholesaleAmount','errors')}">
                                    <input type="text" id="wholesaleAmount" name="wholesaleAmount" value="${fieldValue(bean:productLineItemInstance,field:'wholesaleAmount')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="product">Product:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:productLineItemInstance,field:'product','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.Product.list()}" name="product.id" value="${productLineItemInstance?.product?.id}" ></g:select>
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
