
<%@ page import="com.grailsPortal.domain.AttributeDataType" %>
        <div class="body">
            <h1>Show AttributeDataType</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:attributeDataTypeInstance, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Name:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:attributeDataTypeInstance, field:'name')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Dsc:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:attributeDataTypeInstance, field:'dsc')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Groovy Type:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:attributeDataTypeInstance, field:'groovyType')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Size:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:attributeDataTypeInstance, field:'size')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Html Start Tag:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:attributeDataTypeInstance, field:'htmlStartTag')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Html End Tag:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:attributeDataTypeInstance, field:'htmlEndTag')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Is From CMS:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:attributeDataTypeInstance, field:'isFromCMS')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Cms Content Type:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:attributeDataTypeInstance, field:'cmsContentType')}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${attributeDataTypeInstance?.id}" />
                    <div id="theEdit" style="float:left;padding:.8em .8em .8em .8em;font-size:14px;">
                             <g:remoteLink action="edit" id="${attributeDataTypeInstance.id}" update="edit">Edit</g:remoteLink>
                         </div>
                      </span>
                    <div style="float:left;padding:.8em .8em .8em .8em;font-size:14px;">
                      <g:link class="delete" onclick="return confirm('Are you sure?');" value="Delete" >Delete</g:link>
                    </div>
                </g:form>
            </div>
         <gui:dialog title="AttributeDataType" 
           modal="true" form="false"
           triggers="[show:[id:'theShow', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="show" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 
         <gui:dialog title="AttributeDataType" 
           modal="true" form="false"
           triggers="[show:[id:'theEdit', on:'click']]" 
           fixedCenter="true">
            <div class="dialog" id="edit" style="width:600px;height:400px;overflow:scroll">
           </div>
        </gui:dialog> 