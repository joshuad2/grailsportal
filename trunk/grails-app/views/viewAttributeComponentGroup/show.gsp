
<%@ page import="com.grailsPortal.domain.ViewAttributeComponentGroup" %>
        <div class="body">
            <h1>Show ViewAttributeComponentGroup</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:viewAttributeComponentGroupInstance, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Name:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:viewAttributeComponentGroupInstance, field:'name')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Display Order:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:viewAttributeComponentGroupInstance, field:'displayOrder')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">View:</td>
                            
                            <td valign="top" class="value"><div id="theShow"><g:remoteLink controller="view" action="show" update="show" id="${viewAttributeComponentGroupInstance?.view?.id}">${viewAttributeComponentGroupInstance?.view?.encodeAsHTML()}</g:remoteLink></div></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Active:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:viewAttributeComponentGroupInstance, field:'active')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Mandatory:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:viewAttributeComponentGroupInstance, field:'mandatory')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Attribute Component Group:</td>
                            
                            <td valign="top" class="value"><div id="theShow"><g:remoteLink controller="attributeComponentGroup" action="show" update="show" id="${viewAttributeComponentGroupInstance?.attributeComponentGroup?.id}">${viewAttributeComponentGroupInstance?.attributeComponentGroup?.encodeAsHTML()}</g:remoteLink></div></td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${viewAttributeComponentGroupInstance?.id}" />
                    <div id="theEdit" style="float:left;padding:.8em .8em .8em .8em;font-size:14px;">
                             <g:remoteLink action="edit" id="${viewAttributeComponentGroupInstance.id}" update="edit">Edit</g:remoteLink>
                         </div>
                      </span>
                    <div style="float:left;padding:.8em .8em .8em .8em;font-size:14px;">
                      <g:link class="delete" onclick="return confirm('Are you sure?');" value="Delete" >Delete</g:link>
                    </div>
                </g:form>
            </div>
         <gui:dialog title="ViewAttributeComponentGroup" 
           modal="true" form="false"
           triggers="[show:[id:'theShow', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="show" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
         <gui:dialog title="ViewAttributeComponentGroup" 
           modal="true" form="false"
           triggers="[show:[id:'theEdit', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="edit" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 