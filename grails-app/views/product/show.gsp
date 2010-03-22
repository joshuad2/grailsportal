
<%@ page import="com.grailsPortal.domain.Product" %>
        <div class="body">
            <h1>Show Product</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:productInstance, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Cd:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:productInstance, field:'cd')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Dsc:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:productInstance, field:'dsc')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Ecommerce Code:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:productInstance, field:'ecommerceCode')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Name:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:productInstance, field:'name')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Net Cost Amount:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:productInstance, field:'netCostAmount')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Net Sales Amount:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:productInstance, field:'netSalesAmount')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Product Imageuripath:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:productInstance, field:'productImageuripath')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Sales Channel:</td>
                            
                            <td valign="top" class="value"><div id="theShow"><g:remoteLink controller="salesChannel" action="show" update="show" id="${productInstance?.salesChannel?.id}">${productInstance?.salesChannel?.encodeAsHTML()}</g:remoteLink></div></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Product Type:</td>
                            
                            <td valign="top" class="value"><div id="theShow"><g:remoteLink controller="productType" action="show" update="show" id="${productInstance?.productType?.id}">${productInstance?.productType?.encodeAsHTML()}</g:remoteLink></div></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Product Line Items:</td>
                            
                            <td  valign="top" style="text-align:left;" class="value">
                                <ul>
                                <g:each var="p" in="${productInstance.productLineItems}">
                                    <li><div id="theShow"><g:remoteLink controller="productLineItem" action="show" update="show" id="${p.id}">${p?.encodeAsHTML()}</g:remoteLink></div></li>
                                </g:each>
                                </ul>
                            </td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${productInstance?.id}" />
                    <div id="theEdit" style="float:left;padding:.8em .8em .8em .8em;font-size:14px;">
                             <g:remoteLink action="edit" id="${productInstance.id}" update="edit">Edit</g:remoteLink>
                         </div>
                      </span>
                    <div style="float:left;padding:.8em .8em .8em .8em;font-size:14px;">
                      <g:link class="delete" onclick="return confirm('Are you sure?');" value="Delete" >Delete</g:link>
                    </div>
                </g:form>
            </div>
         <gui:dialog title="Product" 
           modal="true" form="false"
           triggers="[show:[id:'theShow', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="show" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
         <gui:dialog title="Product" 
           modal="true" form="false"
           triggers="[show:[id:'theEdit', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="edit" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 