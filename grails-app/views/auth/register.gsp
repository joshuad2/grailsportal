<%@ page import="com.grailsPortal.domain.ContactType" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" /> 
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

<br/>Please enter the information below.
<br/>
The "*" indicates a mandatory field.
                <div class="dialog">
                  <g:form action="registerUser"  >
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
                           <portal:passwordInput fieldName="password" fieldValue="" label="Password:" maxLength="20" />  
                           <portal:passwordInput fieldName="verifyPassword" fieldValue="" label="Verify Password:" maxLength="20" />  
                           <TR>
                            <td></td>
                            <td><g:submitButton value="Save Profile" name="reg" />
                            </tr>
                          </tbody>
                         </table>
                       </g:form>
                           <shiro:isLoggedIn>
                           <TABLE>
                           <tbody>
                             <portal:phone id="${authInstance.party.id}"/>
                             <portal:email id="${authInstance.party.id}"/>
                            <portal:address id="${authInstance.party.id}"/>
                          </tbody>
                          </TABLE>
                          </shiro:isLoggedIn>
                        </div>
        </div>
    </body>
</html>
