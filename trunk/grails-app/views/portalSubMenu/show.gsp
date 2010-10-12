
<%@ page import="com.grailsPortal.domain.menu.PortalSubMenu" %>
<html>
<head>
<meta name="layout" content="main" />
</head>
<body>
<div class="nav"><span class="menuButton"><a class="home"
	href="${resource(dir:'')}">Home</a></span> <span class="menuButton"
	id="createThePortalSubMenu"> <g:link action="create" >New</g:link> </span></div>
        <div class="body">
            <h1>Show PortalSubMenu</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:portalSubMenuInstance, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">The Controller:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:portalSubMenuInstance, field:'theController')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">The Action:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:portalSubMenuInstance, field:'theAction')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Text:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:portalSubMenuInstance, field:'text')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Is Ajax:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:portalSubMenuInstance, field:'isAjax')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Show Spinner:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:portalSubMenuInstance, field:'showSpinner')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Main Menu:</td>
                            
                            <td valign="top" class="value"><div id="theShow"><g:link controller="portalMenu" action="show" id="${portalSubMenuInstance?.mainMenu?.id}">${portalSubMenuInstance?.mainMenu?.encodeAsHTML()}</g:link></div></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Show Admin:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:portalSubMenuInstance, field:'showAdmin')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Show Anonymous:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:portalSubMenuInstance, field:'showAnonymous')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Portal Menu Type:</td>
                            
                            <td valign="top" class="value"><div id="theShow"><g:link controller="portalMenuType" action="show" id="${portalSubMenuInstance?.portalMenuType?.id}">${portalSubMenuInstance?.portalMenuType?.encodeAsHTML()}</g:link></div></td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${portalSubMenuInstance?.id}" />
                    <div id="theEdit" style="float:left;padding:.8em .8em .8em .8em;font-size:14px;">
                             <g:link action="edit" id="${portalSubMenuInstance.id}" update="edit">Edit</g:link>
                         </div>
                      </span>
                    <div style="float:left;padding:.8em .8em .8em .8em;font-size:14px;">
                      <g:link class="delete" onclick="return confirm('Are you sure?');" value="Delete" >Delete</g:link>
                    </div>
                </g:form>
            </div>
      </div>
</body>
</html>