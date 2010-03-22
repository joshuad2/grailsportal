
<%@ page import="com.grailsPortal.domain.AttributeComponent" %>
        <div class="body">
            <h1>Show AttributeComponent</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:attributeComponentInstance, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Name:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:attributeComponentInstance, field:'name')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Attribute:</td>
                            
                            <td valign="top" class="value"><div id="theShow"><g:remoteLink controller="attribute" action="show" update="show" id="${attributeComponentInstance?.attribute?.id}">${attributeComponentInstance?.attribute?.encodeAsHTML()}</g:remoteLink></div></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Component:</td>
                            
                            <td valign="top" class="value"><div id="theShow"><g:remoteLink controller="component" action="show" update="show" id="${attributeComponentInstance?.component?.id}">${attributeComponentInstance?.component?.encodeAsHTML()}</g:remoteLink></div></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Label Text:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:attributeComponentInstance, field:'labelText')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Label Css Style:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:attributeComponentInstance, field:'labelCssStyle')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Label Css Class:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:attributeComponentInstance, field:'labelCssClass')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Display Order:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:attributeComponentInstance, field:'displayOrder')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Css Style:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:attributeComponentInstance, field:'cssStyle')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Css Class:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:attributeComponentInstance, field:'cssClass')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Css Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:attributeComponentInstance, field:'cssId')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Active:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:attributeComponentInstance, field:'active')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Attribute Component Group:</td>
                            
                            <td valign="top" class="value"><div id="theShow"><g:remoteLink controller="attributeComponentGroup" action="show" update="show" id="${attributeComponentInstance?.attributeComponentGroup?.id}">${attributeComponentInstance?.attributeComponentGroup?.encodeAsHTML()}</g:remoteLink></div></td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${attributeComponentInstance?.id}" />
                    <div id="theEdit" style="float:left;padding:.8em .8em .8em .8em;font-size:14px;">
                             <g:remoteLink action="edit" id="${attributeComponentInstance.id}" update="edit">Edit</g:remoteLink>
                         </div>
                      </span>
                    <div style="float:left;padding:.8em .8em .8em .8em;font-size:14px;">
                      <g:link class="delete" onclick="return confirm('Are you sure?');" value="Delete" >Delete</g:link>
                    </div>
                </g:form>
            </div>
         <gui:dialog title="AttributeComponent" 
           modal="true" form="false"
           triggers="[show:[id:'theShow', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="show" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
         <gui:dialog title="AttributeComponent" 
           modal="true" form="false"
           triggers="[show:[id:'theEdit', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="edit" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 