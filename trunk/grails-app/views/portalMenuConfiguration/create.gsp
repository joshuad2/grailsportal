
<%@ page import="com.grailsPortal.domain.menu.PortalMenuConfiguration" %>
        <div class="body">
            <h1>Create PortalMenuConfiguration</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${portalMenuConfigurationInstance}">
            <div class="errors">
                <g:renderErrors bean="${portalMenuConfigurationInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="menuName">Menu Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:portalMenuConfigurationInstance,field:'menuName','errors')}">
                                    <input type="text" maxlength="100" id="menuName" name="menuName" value="${fieldValue(bean:portalMenuConfigurationInstance,field:'menuName')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="position">Position:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:portalMenuConfigurationInstance,field:'position','errors')}">
                                    <g:select id="position" name="position" from="${portalMenuConfigurationInstance.constraints.position.inList}" value="${portalMenuConfigurationInstance.position}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="hideDelay">Hide Delay:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:portalMenuConfigurationInstance,field:'hideDelay','errors')}">
                                    <input type="text" maxlength="20" id="hideDelay" name="hideDelay" value="${fieldValue(bean:portalMenuConfigurationInstance,field:'hideDelay')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="lazyLoad">Lazy Load:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:portalMenuConfigurationInstance,field:'lazyLoad','errors')}">
                                    <g:select id="lazyLoad" name="lazyLoad" from="${portalMenuConfigurationInstance.constraints.lazyLoad.inList}" value="${portalMenuConfigurationInstance.lazyLoad}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="style">Style:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:portalMenuConfigurationInstance,field:'style','errors')}">
                                    <input type="text" maxlength="200" id="style" name="style" value="${fieldValue(bean:portalMenuConfigurationInstance,field:'style')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="width">Width:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:portalMenuConfigurationInstance,field:'width','errors')}">
                                    <input type="text" maxlength="30" id="width" name="width" value="${fieldValue(bean:portalMenuConfigurationInstance,field:'width')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="height">Height:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:portalMenuConfigurationInstance,field:'height','errors')}">
                                    <input type="text" maxlength="30" id="height" name="height" value="${fieldValue(bean:portalMenuConfigurationInstance,field:'height')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="borderStyle">Border Style:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:portalMenuConfigurationInstance,field:'borderStyle','errors')}">
                                    <textarea rows="5" cols="40" name="borderStyle">${fieldValue(bean:portalMenuConfigurationInstance, field:'borderStyle')}</textarea>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="borderWidth">Border Width:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:portalMenuConfigurationInstance,field:'borderWidth','errors')}">
                                    <input type="text" maxlength="30" id="borderWidth" name="borderWidth" value="${fieldValue(bean:portalMenuConfigurationInstance,field:'borderWidth')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="borderHeight">Border Height:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:portalMenuConfigurationInstance,field:'borderHeight','errors')}">
                                    <input type="text" maxlength="30" id="borderHeight" name="borderHeight" value="${fieldValue(bean:portalMenuConfigurationInstance,field:'borderHeight')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="margin">Margin:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:portalMenuConfigurationInstance,field:'margin','errors')}">
                                    <input type="text" maxlength="30" id="margin" name="margin" value="${fieldValue(bean:portalMenuConfigurationInstance,field:'margin')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="isActive">Is Active:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:portalMenuConfigurationInstance,field:'isActive','errors')}">
                                    <g:select id="isActive" name="isActive" from="${portalMenuConfigurationInstance.constraints.isActive.inList}" value="${portalMenuConfigurationInstance.isActive}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="portalConfig">Portal Config:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:portalMenuConfigurationInstance,field:'portalConfig','errors')}">
                                    
      <g:select optionKey="id" from="${com.grailsPortal.domain.portalConfig.PortalConfig.findAllBy(from ${com.grailsPortal.domain.portalConfig.PortalConfig})}" name="portalConfig.id" value="${portalMenuConfigurationInstance?.portalConfig?.id}" ></g:select>
<div id="createThePortalConfig">
<g:remoteLink controller="portalConfig" id="" action="create" update="createPortalConfig">Add</g:remoteLink>
           </div>
           <gui:dialog title="Add a PortalConfig" modal="true" form="false" triggers="[show:[id:'createThePortalConfig', on:'click']]" fixedCenter="true"]>
             <div class="dialog" id="createPortalConfig" style="width:600px;height:400px;overflow:scroll"></div>
           </gui:dialog>
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Save" /></span>
                </div>
            </g:form>
