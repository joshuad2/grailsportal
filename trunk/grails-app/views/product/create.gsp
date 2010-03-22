
<%@ page import="com.grailsPortal.domain.Product" %>
        <div class="body">
            <h1>Create Product</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${productInstance}">
            <div class="errors">
                <g:renderErrors bean="${productInstance}" as="list" />
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
                                <td valign="top" class="value ${hasErrors(bean:productInstance,field:'cd','errors')}">
                                    <input type="text" maxlength="20" id="cd" name="cd" value="${fieldValue(bean:productInstance,field:'cd')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="dsc">Dsc:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:productInstance,field:'dsc','errors')}">
                                    <input type="text" maxlength="200" id="dsc" name="dsc" value="${fieldValue(bean:productInstance,field:'dsc')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="ecommerceCode">Ecommerce Code:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:productInstance,field:'ecommerceCode','errors')}">
                                    <input type="text" maxlength="100" id="ecommerceCode" name="ecommerceCode" value="${fieldValue(bean:productInstance,field:'ecommerceCode')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name">Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:productInstance,field:'name','errors')}">
                                    <input type="text" maxlength="100" id="name" name="name" value="${fieldValue(bean:productInstance,field:'name')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="netCostAmount">Net Cost Amount:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:productInstance,field:'netCostAmount','errors')}">
                                    <input type="text" id="netCostAmount" name="netCostAmount" value="${fieldValue(bean:productInstance,field:'netCostAmount')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="netSalesAmount">Net Sales Amount:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:productInstance,field:'netSalesAmount','errors')}">
                                    <input type="text" id="netSalesAmount" name="netSalesAmount" value="${fieldValue(bean:productInstance,field:'netSalesAmount')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="productImageuripath">Product Imageuripath:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:productInstance,field:'productImageuripath','errors')}">
                                    <textarea rows="5" cols="40" name="productImageuripath">${fieldValue(bean:productInstance, field:'productImageuripath')}</textarea>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="salesChannel">Sales Channel:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:productInstance,field:'salesChannel','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.SalesChannel.list()}" name="salesChannel.id" value="${productInstance?.salesChannel?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="productType">Product Type:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:productInstance,field:'productType','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.ProductType.list()}" name="productType.id" value="${productInstance?.productType?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Save" /></span>
                </div>
            </g:form>
