
<%@ page import="com.grailsPortal.domain.PartyAttrValue" %>
        <div class="body">
            <h1>Show PartyAttrValue</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:partyAttrValueInstance, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Attr Value:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:partyAttrValueInstance, field:'attrValue')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Party:</td>
                            
                            <td valign="top" class="value"><div id="theShow"><g:remoteLink controller="party" action="show" update="show" id="${partyAttrValueInstance?.party?.id}">${partyAttrValueInstance?.party?.encodeAsHTML()}</g:remoteLink></div></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Attribute:</td>
                            
                            <td valign="top" class="value"><div id="theShow"><g:remoteLink controller="attribute" action="show" update="show" id="${partyAttrValueInstance?.attribute?.id}">${partyAttrValueInstance?.attribute?.encodeAsHTML()}</g:remoteLink></div></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Display Order:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:partyAttrValueInstance, field:'displayOrder')}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${partyAttrValueInstance?.id}" />
                    <div id="theEdit" style="float:left;padding:.8em .8em .8em .8em;font-size:14px;">
                             <g:remoteLink action="edit" id="${partyAttrValueInstance.id}" update="edit">Edit</g:remoteLink>
                         </div>
                      </span>
                    <div style="float:left;padding:.8em .8em .8em .8em;font-size:14px;">
                      <g:link class="delete" onclick="return confirm('Are you sure?');" value="Delete" >Delete</g:link>
                    </div>
                </g:form>
            </div>
         <gui:dialog title="PartyAttrValue" 
           modal="true" form="false"
           triggers="[show:[id:'theShow', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="show" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
         <gui:dialog title="PartyAttrValue" 
           modal="true" form="false"
           triggers="[show:[id:'theEdit', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="edit" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 