<%@ page import="com.grailsPortal.domain.ContactAddress" %>
        <div class="body">
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${contactAddressInstance}">
            <div class="errors">
                <g:renderErrors bean="${contactAddressInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="saveAddress" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="address1">Address1:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:contactAddressInstance,field:'address1','errors')}">
                                    <g:hiddenField name="partyId" value="${partyId}"/>
                                    <g:hiddenField name="contactType" value="${contactType}"/>
                                    <input type="text" maxlength="100" id="address1" name="address1" value="${fieldValue(bean:contactAddressInstance,field:'address1')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="address2">Address2:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:contactAddressInstance,field:'address2','errors')}">
                                    <input type="text" maxlength="100" id="address2" name="address2" value="${fieldValue(bean:contactAddressInstance,field:'address2')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="address3">Address3:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:contactAddressInstance,field:'address3','errors')}">
                                    <input type="text" maxlength="100" id="address3" name="address3" value="${fieldValue(bean:contactAddressInstance,field:'address3')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="city">City:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:contactAddressInstance,field:'city','errors')}">
                                    <input type="text" maxlength="100" id="city" name="city" value="${fieldValue(bean:contactAddressInstance,field:'city')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="state">State:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:contactAddressInstance,field:'state','errors')}">
                                    <g:select optionKey="id" from="${com.grailsPortal.domain.State.list()}" name="state.id" value="${contactAddressInstance?.state?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="zipcode">Zipcode:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:contactAddressInstance,field:'zipcode','errors')}">
                                    <input type="text" maxlength="10" id="zipcode" name="zipcode" value="${fieldValue(bean:contactAddressInstance,field:'zipcode')}"/>
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Save" /></span>
                </div>
            </g:form>
