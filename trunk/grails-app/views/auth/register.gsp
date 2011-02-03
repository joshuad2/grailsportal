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
            <g:hasErrors bean="${authErrorInstance}" field="">
              <div class="errors">
                <g:renderErrors bean="${authErrorInstance.errors}" field="" as="list" />
              </div>
            </g:hasErrors>
              <g:form action="registerUser"  >
<br/>Please enter the information below.
<br/>
The "*" indicates a mandatory field.
                <div class="dialog">
                    <table>
                        <tbody>   
                          <portal:formInputField 
                            fieldValue="${fieldValue(bean:authInstance.party,field:'firstName')}" 
                            labelField="firstName"
                            fieldName="firstName"
                            fieldLength="100"
                            label="First Name:*" fieldName="firstName"/> 
                          <portal:formInputField 
                            fieldValue="${fieldValue(bean:authInstance.party,field:'lastName')}" 
                            labelField="lastName"
                            fieldName="lastName"
                            fieldLength="100"
                            label="Last Name:*" fieldName="lastName"/>                                            
                          <shiro:isNotLoggedIn>
                            <portal:formInputField 
                                      fieldValue="${fieldValue(bean:authInstance,field:'username')}"
                                      label="User Name*:"
                                      labelField="userName"
                                      fieldLength="100"
                                      fieldName="userName"/>
                           </shiro:isNotLoggedIn>
                           <shiro:isLoggedIn>
                             <portal:displayValue value="${fieldValue(bean:authInstance,field:'username')}"
                                                  label="User Name:"
                                                  fieldName="name"/>
                             <g:hiddenField name="userName" id="userName" value="${fieldValue(bean:authInstance,field:'username')}"/>
                           </shiro:isLoggedIn>  
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
