
<%@ page import="com.grailsPortal.domain.menu.PortalSubMenu" %>
<html>
<head>
<meta name="layout" content="main" />
<gui:resources components="dialog" />
<g:javascript library="prototype" />
</head>
<body>
<div class="nav"><span class="menuButton"><a class="home"
	href="${resource(dir:'')}">Home</a></span>
</div>
        <div class="body">
            <h1>Create PortalSubMenu</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${portalSubMenuInstance}">
            <div class="errors">
                <g:renderErrors bean="${portalSubMenuInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="theController">The Controller:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:portalSubMenuInstance,field:'theController','errors')}">
                                    <input type="text" maxlength="40" id="theController" name="theController" value="${fieldValue(bean:portalSubMenuInstance,field:'theController')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="theAction">The Action:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:portalSubMenuInstance,field:'theAction','errors')}">
                                    <input type="text" maxlength="40" id="theAction" name="theAction" value="${fieldValue(bean:portalSubMenuInstance,field:'theAction')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="text">Text:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:portalSubMenuInstance,field:'text','errors')}">
                                    <input type="text" maxlength="100" id="text" name="text" value="${fieldValue(bean:portalSubMenuInstance,field:'text')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="isAjax">Is Ajax:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:portalSubMenuInstance,field:'isAjax','errors')}">
                                    <g:select id="isAjax" name="isAjax" from="${portalSubMenuInstance.constraints.isAjax.inList}" value="${portalSubMenuInstance.isAjax}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="showSpinner">Show Spinner:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:portalSubMenuInstance,field:'showSpinner','errors')}">
                                    <g:select id="showSpinner" name="showSpinner" from="${portalSubMenuInstance.constraints.showSpinner.inList}" value="${portalSubMenuInstance.showSpinner}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="mainMenu">Main Menu:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:portalSubMenuInstance,field:'mainMenu','errors')}">
                                    
      <g:select optionKey="id" from="${com.grailsPortal.domain.menu.PortalMenu.list()}" name="mainMenu.id" value="${portalSubMenuInstance?.mainMenu?.id}" ></g:select>
<div id="createThePortalMenu">
<g:link controller="portalMenu" id="" action="create"">Add</g:link>
           </div>
           
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="showAdmin">Show Admin:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:portalSubMenuInstance,field:'showAdmin','errors')}">
                                    <g:select id="showAdmin" name="showAdmin" from="${portalSubMenuInstance.constraints.showAdmin.inList}" value="${portalSubMenuInstance.showAdmin}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="showAnonymous">Show Anonymous:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:portalSubMenuInstance,field:'showAnonymous','errors')}">
                                    <g:select id="showAnonymous" name="showAnonymous" from="${portalSubMenuInstance.constraints.showAnonymous.inList}" value="${portalSubMenuInstance.showAnonymous}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="portalMenuType">Portal Menu Type:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:portalSubMenuInstance,field:'portalMenuType','errors')}">
                                    
      <g:select optionKey="id" from="${com.grailsPortal.domain.menu.PortalMenuType.list()}" name="portalMenuType.id" value="${portalSubMenuInstance?.portalMenuType?.id}" ></g:select>
<div id="createThePortalMenuType">
<g:link controller="portalMenuType" id="" action="create"">Add</g:link>
           </div>
           
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Save" /></span>
                </div>
            </g:form>
