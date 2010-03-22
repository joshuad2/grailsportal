
<%@ page import="com.grailsPortal.domain.AttributeLookupValue" %>
        <div class="body">
            <h1>Show AttributeLookupValue</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:attributeLookupValueInstance, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Lookup Value:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:attributeLookupValueInstance, field:'lookupValue')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Attribute:</td>
                            
                            <td valign="top" class="value"><div id="theShow"><g:remoteLink controller="attribute" action="show" update="show" id="${attributeLookupValueInstance?.attribute?.id}">${attributeLookupValueInstance?.attribute?.encodeAsHTML()}</g:remoteLink></div></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Display Order:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:attributeLookupValueInstance, field:'displayOrder')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Image Path:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:attributeLookupValueInstance, field:'imagePath')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Css Class:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:attributeLookupValueInstance, field:'cssClass')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Css Style:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:attributeLookupValueInstance, field:'cssStyle')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Active:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:attributeLookupValueInstance, field:'active')}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${attributeLookupValueInstance?.id}" />
                    <div id="theEdit" style="float:left;padding:.8em .8em .8em .8em;font-size:14px;">
                             <g:remoteLink action="edit" id="${attributeLookupValueInstance.id}" update="edit">Edit</g:remoteLink>
                         </div>
                      </span>
                    <div style="float:left;padding:.8em .8em .8em .8em;font-size:14px;">
                      <g:link class="delete" onclick="return confirm('Are you sure?');" value="Delete" >Delete</g:link>
                    </div>
                </g:form>
            </div>
         <gui:dialog title="AttributeLookupValue" 
           modal="true" form="false"
           triggers="[show:[id:'theShow', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="show" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
         <gui:dialog title="AttributeLookupValue" 
           modal="true" form="false"
           triggers="[show:[id:'theEdit', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="edit" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 