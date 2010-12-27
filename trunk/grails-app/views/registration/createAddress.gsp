
<%@ page import="com.grailsPortal.domain.ContactAddress" %>
<html>
<head>
<meta name="layout" content="main" />
</head>
<body>
<div class="nav"><span class="menuButton"><a class="home"
	href="${resource(dir:'')}">Home</a></span>
</div>
        <div class="body">
            <h1>Create Address</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${contactAddressInstance}">
            <div class="errors">
                <g:renderErrors bean="${contactAddressInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form  action="register" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="address1">Address1:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:contactAddressInstance,field:'address1','errors')}">
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
                                    			<g:select optionKey="id" 
			          from="${com.grailsPortal.domain.portalConfig.State.list()}" 
			          name="state.id"
			 value="${contactAddressInstance?.state?.id}"}>
			</g:select>
			
<div id="createTheState">
<g:link controller="state" id="" action="create"">Add</g:link>
           </div>
           
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
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="party">Party:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:contactAddressInstance,field:'party','errors')}">
                                    <shiro:hasAllRoles in="['user']">		    <div>
		    ${fieldValue(bean:contactAddressInstance,field:'party')}
		    </div>
		    <g:hiddenField name="party" 
		                   value="${fieldValue(bean:contactAddressInstance,field:'party')}" />
		    </shiro:hasAllRoles><shiro:lacksAllRoles in="['user']">			<g:select optionKey="id" 
			          from="${com.grailsPortal.domain.Party.list()}" 
			          name="party.id"
			 value="${contactAddressInstance?.party?.id}"}>
			</g:select>
			</shiro:lacksAllRoles><shiro:hasAllRoles in="['admin']">
<div id="createTheParty">
<g:link controller="party" id="" action="create"">Add</g:link>
           </div>
                </shiro:hasAllRoles>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="active">Active:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:contactAddressInstance,field:'active','errors')}">
                                    <g:checkBox name="active" value="${contactAddressInstance?.active}" ></g:checkBox>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="contactType">Contact Type:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:contactAddressInstance,field:'contactType','errors')}">
                                    <shiro:hasAllRoles in="['user']">		    <div>
		    ${fieldValue(bean:contactAddressInstance,field:'contactType')}
		    </div>
		    <g:hiddenField name="contactType" 
		                   value="${fieldValue(bean:contactAddressInstance,field:'contactType')}" />
		    </shiro:hasAllRoles><shiro:lacksAllRoles in="['user']">			<g:select optionKey="id" 
			          from="${com.grailsPortal.domain.ContactType.list()}" 
			          name="contactType.id"
			 value="${contactAddressInstance?.contactType?.id}"}>
			</g:select>
			</shiro:lacksAllRoles><shiro:hasAllRoles in="['admin']">
<div id="createTheContactType">
<g:link controller="contactType" id="" action="create"">Add</g:link>
           </div>
                </shiro:hasAllRoles>
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" name="createAddress" type="submit" value="Save" /></span>
                </div>
            </g:form>
