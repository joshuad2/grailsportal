
<%@ page import="com.grailsPortal.domain.ViewAttribute" %>
        <div class="body">
            <h1>Show ViewAttribute</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:viewAttributeInstance, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">View:</td>
                            
                            <td valign="top" class="value"><div id="theShow"><g:remoteLink controller="view" action="show" update="show" id="${viewAttributeInstance?.view?.id}">${viewAttributeInstance?.view?.encodeAsHTML()}</g:remoteLink></div></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Attribute Component:</td>
                            
                            <td valign="top" class="value"><div id="theShow"><g:remoteLink controller="attributeComponent" action="show" update="show" id="${viewAttributeInstance?.attributeComponent?.id}">${viewAttributeInstance?.attributeComponent?.encodeAsHTML()}</g:remoteLink></div></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Display Order:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:viewAttributeInstance, field:'displayOrder')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Mandatory:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:viewAttributeInstance, field:'mandatory')}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${viewAttributeInstance?.id}" />
                    <span class="button">
                        <div id="theEdit">
                             <g:remoteLink action="edit" id="${viewAttributeInstance.id}" update="edit">Edit</g:remoteLink>
                         </div>
                      </span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </g:form>
            </div>
         <gui:dialog title="ViewAttribute" 
           modal="true" form="false"
           triggers="[show:[id:'theShow', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="show" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
         <gui:dialog title="ViewAttribute" 
           modal="true" form="false"
           triggers="[show:[id:'theEdit', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="edit" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 