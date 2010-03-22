<%@ page import="com.grailsPortal.domain.ContactType" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <gui:resources components="['accordion','dialog']"/>  
		<g:javascript library="prototype" />
        <title>User Profile</title>         
    </head>
    <body>
        <div class="body">
            <h1>Wellness Essentials Profile</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${authErrorInstance}">
            <div class="errors">
                <g:renderErrors bean="${authErrorInstance.errors}" as="list" />
            </div>
            </g:hasErrors>
              <g:form action="registerUser"  >
<br/>Please enter the information below.
<br/>
The "*" indicates a mandatory field.
                <div class="dialog">
                    <table>
                        <tbody>                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="firstName">First Name*:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:authInstance,field:'firstName','errors')}">
                                    <g:textField maxlength="100" id="firstName" name="firstName" value="${fieldValue(bean:authInstance.party,field:'firstName')}"/>
                                </td>
                            </tr>                         
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="lastName">Last Name*:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:authInstance,field:'lastName','errors')}">
                                    <g:textField maxlength="100" id="lastName" name="lastName" value="${fieldValue(bean:authInstance.party,field:'lastName')}"/>
                                </td>
                            </tr>                         
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="userName">User Name*:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:authInstance,field:'userName','errors')}">
                                  <shiro:isNotLoggedIn>
                                    <g:textField maxlength="100" id="userName" name="userName" value="${fieldValue(bean:authInstance,field:'username')}"/>
                                  </shiro:isNotLoggedIn>
                                  <shiro:isLoggedIn>
                                    ${fieldValue(bean:authInstance,field:'username')}
                                    <g:hiddenField name="userName" id="userName" value="${fieldValue(bean:authInstance,field:'username')}"/>
                                  </shiro:isLoggedIn>  
                                </td>
                            </tr>
							<tr class="prop">
                                <td valign="top" class="name">
                                    <label for="password">Password:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:authInstance,field:'password','errors')}">
                                    <g:passwordField maxlength="20" id="password" name="password"/>
                                </td>
                            </tr>
							<tr class="prop">
                                <td valign="top" class="name">
                                    <label for="verifyPassword">Verify Password:</label>
                                </td>
                                <td valign="top">
                                    <g:passwordField maxlength="20" id="passwordVerify" name="passwordVerify" />
                                </td>
                            </tr>
                           </tr>
                           </tbody>
                           </table>
                        
                           <shiro:isLoggedIn>
                           <gui:accordion>
                            <gui:accordionElement title="Phone Numbers">
                              <portal:phone id="${authInstance.party.id}"/>
                           </gui:accordionElement>
                           <gui:accordionElement title="Email">
                             <portal:email id="${authInstance.party.id}"/>
                           </gui:accordionElement>
                           <gui:accordionElement title="Address">  
                            <portal:address id="${authInstance.party.id }"/>
                          </gui:accordionElement> 
                          </gui:accordion>
                          </shiro:isLoggedIn>
                          <TABLE>
                           <TR>
                            <td></td>
                            <td><g:submitButton value="Save Profile" name="reg" />
                            </tr>
                        </table>
                </div>
            </g:form>
        </div>
    </body>
</html>
