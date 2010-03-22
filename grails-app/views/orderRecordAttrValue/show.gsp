
<%@ page import="com.grailsPortal.domain.OrderRecordAttrValue" %>
        <div class="body">
            <h1>Show OrderRecordAttrValue</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:orderRecordAttrValueInstance, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Attr Value:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:orderRecordAttrValueInstance, field:'attrValue')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Order Record:</td>
                            
                            <td valign="top" class="value"><div id="theShow"><g:remoteLink controller="orderRecord" action="show" update="show" id="${orderRecordAttrValueInstance?.orderRecord?.id}">${orderRecordAttrValueInstance?.orderRecord?.encodeAsHTML()}</g:remoteLink></div></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Attribute:</td>
                            
                            <td valign="top" class="value"><div id="theShow"><g:remoteLink controller="attribute" action="show" update="show" id="${orderRecordAttrValueInstance?.attribute?.id}">${orderRecordAttrValueInstance?.attribute?.encodeAsHTML()}</g:remoteLink></div></td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${orderRecordAttrValueInstance?.id}" />
                    <div id="theEdit" style="float:left;padding:.8em .8em .8em .8em;font-size:14px;">
                             <g:remoteLink action="edit" id="${orderRecordAttrValueInstance.id}" update="edit">Edit</g:remoteLink>
                         </div>
                      </span>
                    <div style="float:left;padding:.8em .8em .8em .8em;font-size:14px;">
                      <g:link class="delete" onclick="return confirm('Are you sure?');" value="Delete" >Delete</g:link>
                    </div>
                </g:form>
            </div>
         <gui:dialog title="OrderRecordAttrValue" 
           modal="true" form="false"
           triggers="[show:[id:'theShow', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="show" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
         <gui:dialog title="OrderRecordAttrValue" 
           modal="true" form="false"
           triggers="[show:[id:'theEdit', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="edit" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 