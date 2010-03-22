
<%@ page import="com.grailsPortal.domain.InventoryItem" %>
        <div class="body">
            <h1>Show InventoryItem</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:inventoryItemInstance, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Cd:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:inventoryItemInstance, field:'cd')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Contenturipath:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:inventoryItemInstance, field:'contenturipath')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Dsc:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:inventoryItemInstance, field:'dsc')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Imageuripath:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:inventoryItemInstance, field:'imageuripath')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Name:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:inventoryItemInstance, field:'name')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Number Of Item In Stock:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:inventoryItemInstance, field:'numberOfItemInStock')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Retail Cost:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:inventoryItemInstance, field:'retailCost')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Warning Inv Level:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:inventoryItemInstance, field:'warningInvLevel')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Wholesale Cost:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:inventoryItemInstance, field:'wholesaleCost')}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${inventoryItemInstance?.id}" />
                    <div id="theEdit" style="float:left;padding:.8em .8em .8em .8em;font-size:14px;">
                             <g:remoteLink action="edit" id="${inventoryItemInstance.id}" update="edit">Edit</g:remoteLink>
                         </div>
                      </span>
                    <div style="float:left;padding:.8em .8em .8em .8em;font-size:14px;">
                      <g:link class="delete" onclick="return confirm('Are you sure?');" value="Delete" >Delete</g:link>
                    </div>
                </g:form>
            </div>
         <gui:dialog title="InventoryItem" 
           modal="true" form="false"
           triggers="[show:[id:'theShow', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="show" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
         <gui:dialog title="InventoryItem" 
           modal="true" form="false"
           triggers="[show:[id:'theEdit', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="edit" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 