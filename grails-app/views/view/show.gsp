
<%@ page import="com.grailsPortal.domain.View" %>
        <div class="body">
            <h1>Show View</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:viewInstance, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Name:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:viewInstance, field:'name')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">View Name:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:viewInstance, field:'viewName')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Action Name:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:viewInstance, field:'actionName')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Title:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:viewInstance, field:'title')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Portal Config Business Process:</td>
                            
                            <td valign="top" class="value"><div id="theShow"><g:remoteLink controller="portalConfigBusinessProcess" action="show" update="show" id="${viewInstance?.portalConfigBusinessProcess?.id}">${viewInstance?.portalConfigBusinessProcess?.encodeAsHTML()}</g:remoteLink></div></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Display Order:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:viewInstance, field:'displayOrder')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Template:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:viewInstance, field:'template')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Dsc:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:viewInstance, field:'dsc')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Raw Html Header:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:viewInstance, field:'rawHtmlHeader')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Raw Html Start Body:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:viewInstance, field:'rawHtmlStartBody')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Raw Html End Body:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:viewInstance, field:'rawHtmlEndBody')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">View Attributes:</td>
                            
                            <td  valign="top" style="text-align:left;" class="value">
                                <ul>
                                <g:each var="v" in="${viewInstance.viewAttributes}">
                                    <li><div id="theShow"><g:remoteLink controller="viewAttribute" action="show" update="show" id="${v.id}">${v?.encodeAsHTML()}</g:remoteLink></div></li>
                                </g:each>
                                </ul>
                            </td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">View Attribute Component Groups:</td>
                            
                            <td  valign="top" style="text-align:left;" class="value">
                                <ul>
                                <g:each var="v" in="${viewInstance.viewAttributeComponentGroups}">
                                    <li><div id="theShow"><g:remoteLink controller="viewAttributeComponentGroup" action="show" update="show" id="${v.id}">${v?.encodeAsHTML()}</g:remoteLink></div></li>
                                </g:each>
                                </ul>
                            </td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${viewInstance?.id}" />
                    <div id="theEdit" style="float:left;padding:.8em .8em .8em .8em;font-size:14px;">
                             <g:remoteLink action="edit" id="${viewInstance.id}" update="edit">Edit</g:remoteLink>
                         </div>
                      </span>
                    <div style="float:left;padding:.8em .8em .8em .8em;font-size:14px;">
                      <g:link class="delete" onclick="return confirm('Are you sure?');" value="Delete" >Delete</g:link>
                    </div>
                </g:form>
            </div>
         <gui:dialog title="View" 
           modal="true" form="false"
           triggers="[show:[id:'theShow', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="show" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
         <gui:dialog title="View" 
           modal="true" form="false"
           triggers="[show:[id:'theEdit', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="edit" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 