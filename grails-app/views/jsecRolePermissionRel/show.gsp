
<%@ page import="com.grailsPortal.domain.JsecRolePermissionRel" %>
        <div class="body">
            <h1>Show JsecRolePermissionRel</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:jsecRolePermissionRelInstance, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Actions:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:jsecRolePermissionRelInstance, field:'actions')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Target:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:jsecRolePermissionRelInstance, field:'target')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Permission:</td>
                            
                            <td valign="top" class="value"><div id="theShow"><g:remoteLink controller="jsecPermission" action="show" update="show" id="${jsecRolePermissionRelInstance?.permission?.id}">${jsecRolePermissionRelInstance?.permission?.encodeAsHTML()}</g:remoteLink></div></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Role:</td>
                            
                            <td valign="top" class="value"><div id="theShow"><g:remoteLink controller="jsecRole" action="show" update="show" id="${jsecRolePermissionRelInstance?.role?.id}">${jsecRolePermissionRelInstance?.role?.encodeAsHTML()}</g:remoteLink></div></td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${jsecRolePermissionRelInstance?.id}" />
                    <div id="theEdit" style="float:left;padding:.8em .8em .8em .8em;font-size:14px;">
                             <g:remoteLink action="edit" id="${jsecRolePermissionRelInstance.id}" update="edit">Edit</g:remoteLink>
                         </div>
                      </span>
                    <div style="float:left;padding:.8em .8em .8em .8em;font-size:14px;">
                      <g:link class="delete" onclick="return confirm('Are you sure?');" value="Delete" >Delete</g:link>
                    </div>
                </g:form>
            </div>
         <gui:dialog title="JsecRolePermissionRel" 
           modal="true" form="false"
           triggers="[show:[id:'theShow', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="show" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
         <gui:dialog title="JsecRolePermissionRel" 
           modal="true" form="false"
           triggers="[show:[id:'theEdit', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="edit" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 