
<%@ page import="com.grailsPortal.domain.PortalConfigAttrValue" %>
        <div class="body">
            <h1>Show PortalConfigAttrValue</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:portalConfigAttrValueInstance, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Attr Value:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:portalConfigAttrValueInstance, field:'attrValue')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Portal Config:</td>
                            
                            <td valign="top" class="value"><div id="theShow"><g:remoteLink controller="portalConfig" action="show" update="show" id="${portalConfigAttrValueInstance?.portalConfig?.id}">${portalConfigAttrValueInstance?.portalConfig?.encodeAsHTML()}</g:remoteLink></div></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Attribute:</td>
                            
                            <td valign="top" class="value"><div id="theShow"><g:remoteLink controller="attribute" action="show" update="show" id="${portalConfigAttrValueInstance?.attribute?.id}">${portalConfigAttrValueInstance?.attribute?.encodeAsHTML()}</g:remoteLink></div></td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${portalConfigAttrValueInstance?.id}" />
                    <div id="theEdit" style="float:left;padding:.8em .8em .8em .8em;font-size:14px;">
                             <g:remoteLink action="edit" id="${portalConfigAttrValueInstance.id}" update="edit">Edit</g:remoteLink>
                         </div>
                      </span>
                    <div style="float:left;padding:.8em .8em .8em .8em;font-size:14px;">
                      <g:link class="delete" onclick="return confirm('Are you sure?');" value="Delete" >Delete</g:link>
                    </div>
                </g:form>
            </div>
         <gui:dialog title="PortalConfigAttrValue" 
           modal="true" form="false"
           triggers="[show:[id:'theShow', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="show" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
         <gui:dialog title="PortalConfigAttrValue" 
           modal="true" form="false"
           triggers="[show:[id:'theEdit', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="edit" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 