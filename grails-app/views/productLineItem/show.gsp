
<%@ page import="com.grailsPortal.domain.ProductLineItem" %>
        <div class="body">
            <h1>Show ProductLineItem</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:productLineItemInstance, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Dsc:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:productLineItemInstance, field:'dsc')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Gross Cost:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:productLineItemInstance, field:'grossCost')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Line Item Number:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:productLineItemInstance, field:'lineItemNumber')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Is Taxable:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:productLineItemInstance, field:'isTaxable')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Net Cost:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:productLineItemInstance, field:'netCost')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Retail Amount:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:productLineItemInstance, field:'retailAmount')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Wholesale Amount:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:productLineItemInstance, field:'wholesaleAmount')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Product:</td>
                            
                            <td valign="top" class="value"><div id="theShow"><g:remoteLink controller="product" action="show" update="show" id="${productLineItemInstance?.product?.id}">${productLineItemInstance?.product?.encodeAsHTML()}</g:remoteLink></div></td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${productLineItemInstance?.id}" />
                    <div id="theEdit" style="float:left;padding:.8em .8em .8em .8em;font-size:14px;">
                             <g:remoteLink action="edit" id="${productLineItemInstance.id}" update="edit">Edit</g:remoteLink>
                         </div>
                      </span>
                    <div style="float:left;padding:.8em .8em .8em .8em;font-size:14px;">
                      <g:link class="delete" onclick="return confirm('Are you sure?');" value="Delete" >Delete</g:link>
                    </div>
                </g:form>
            </div>
         <gui:dialog title="ProductLineItem" 
           modal="true" form="false"
           triggers="[show:[id:'theShow', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="show" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
         <gui:dialog title="ProductLineItem" 
           modal="true" form="false"
           triggers="[show:[id:'theEdit', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="edit" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 