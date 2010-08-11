
<%@ page import="com.grailsPortal.domain.menu.PortalMenuConfiguration" %>
        <div class="body">
            <h1>Show PortalMenuConfiguration</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:portalMenuConfigurationInstance, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Menu Name:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:portalMenuConfigurationInstance, field:'menuName')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Position:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:portalMenuConfigurationInstance, field:'position')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Hide Delay:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:portalMenuConfigurationInstance, field:'hideDelay')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Lazy Load:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:portalMenuConfigurationInstance, field:'lazyLoad')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Style:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:portalMenuConfigurationInstance, field:'style')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Width:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:portalMenuConfigurationInstance, field:'width')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Height:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:portalMenuConfigurationInstance, field:'height')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Border Style:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:portalMenuConfigurationInstance, field:'borderStyle')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Border Width:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:portalMenuConfigurationInstance, field:'borderWidth')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Border Height:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:portalMenuConfigurationInstance, field:'borderHeight')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Margin:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:portalMenuConfigurationInstance, field:'margin')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Is Active:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:portalMenuConfigurationInstance, field:'isActive')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Portal Config:</td>
                            
                            <td valign="top" class="value"><div id="theShow"><g:remoteLink controller="portalConfig" action="show" update="show" id="${portalMenuConfigurationInstance?.portalConfig?.id}">${portalMenuConfigurationInstance?.portalConfig?.encodeAsHTML()}</g:remoteLink></div></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Portal Menus:</td>
                            
                            <td  valign="top" style="text-align:left;" class="value">
                                <ul>
                                <g:each var="p" in="${portalMenuConfigurationInstance.portalMenus}">
                                    <li><div id="theShow"><g:remoteLink controller="portalMenu" action="show" update="show" id="${p.id}">${p?.encodeAsHTML()}</g:remoteLink></div></li>
                                </g:each>
                                </ul>
                            </td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${portalMenuConfigurationInstance?.id}" />
                    <div id="theEdit" style="float:left;padding:.8em .8em .8em .8em;font-size:14px;">
                             <g:remoteLink action="edit" id="${portalMenuConfigurationInstance.id}" update="edit">Edit</g:remoteLink>
                         </div>
                      </span>
                    <div style="float:left;padding:.8em .8em .8em .8em;font-size:14px;">
                      <g:link class="delete" onclick="return confirm('Are you sure?');" value="Delete" >Delete</g:link>
                    </div>
                </g:form>
            </div>
         <gui:dialog title="PortalMenuConfiguration" 
           modal="true" form="false"
           triggers="[show:[id:'theShow', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="show" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
         <gui:dialog title="PortalMenuConfiguration" 
           modal="true" form="false"
           triggers="[show:[id:'theEdit', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="edit" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 