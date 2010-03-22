<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Enter Doctor Information</title>         
    </head>
    <body>
        <div class="body">
        <div class="nav">

            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" controller="registration" action="newRegistration">Register Another Child</g:link></span>
         </div>
          <div class="dialog" style="width:800px;font-size:16;border: medium;font-family: Verdana;padding-left: 10;padding-right: 20;padding-top: 20;padding-bottom: 20;border-style: solid">
Please enter the name of the Pediatrician you would like us to call in an emergency. 
<br>
The "*" indicates a mandatory field.
</div>
            <h1>Doctor Information</h1>
            <g:if test="${message}">
            <div class="message">${message}</div>
            </g:if>
            <g:hasErrors bean="${medInstance}">
            <div class="errors">
                <g:renderErrors bean="${medInstance.errors}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="register" method="post" >
                <div id="mainParent" class="dialog">
                    <table>
                        <tbody> 
                             <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="firstName">First Name:</label>
                                </td>
                              <g:if test="${regEvent.registrationDoctor!=null && regEvent.registrationDoctor.doctor!=null}">
                                <td valign="top" class="value ${hasErrors(bean:regEvent.registrationDoctor.doctor,field:'firstName','errors')}">
                                    <input size="60" maxLength="100" type="text" id="firstName" name="firstName" value="${fieldValue(bean:regEvent.registrationDoctor.doctor,field:'firstName')}" />
                                </td>
                               </g:if>
                               <g:else>
                                    <td valign="top" >
                                    <input size="60" maxLength="100" type="text" id="firstName" name="firstName"/>
                                </td>
                               </g:else>
                            </tr> 
                            
                           <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="lastName">Last Name:</label>
                                </td>
                               <g:if test="${regEvent.registrationDoctor!=null && regEvent.registrationDoctor.doctor!=null}">
                                 <td valign="top" class="value ${hasErrors(bean:regEvent.registrationDoctor.doctor,field:'lastName','errors')}">
                                    <input size="60" maxLength="100" type="text" id="lastName" name="lastName" value="${fieldValue(bean:regEvent.registrationDoctor.doctor,field:'lastName')}" />
                                </td>
                                </g:if>
                               <g:else>
                                 <td valign="top">
                                    <input size="60" maxLength="100" type="text" id="lastName" name="lastName" />
                                </td>
                                </g:else>
                            </tr>     
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="phoneNumber">Office Phone Number:</label>
                                </td>
                               <g:if test="${regEvent.registrationDoctor!=null && regEvent.registrationDoctor.doctor!=null}">
                                <td valign="top" class="value ${hasErrors(bean:regEvent.registrationDoctor,field:'officePhoneNumber','errors')}">
                                    <input type="text" size="13" maxLength="13" id="workPhoneNumber" name="workPhoneNumber" value="${fieldValue(bean:regEvent.registrationDoctor,field:'officePhoneNumber')}"/>
                                </td>
                                </g:if>
                                <g:else>
                                    <td valign="top" >
                                    <input type="text" size="13" maxLength="13" id="workPhoneNumber" name="workPhoneNumber" />
                                </td>
                                </g:else>
                            </tr>
                                                            
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                   <g:submitButton name="gotoFinish" value="Finish"></g:submitButton>
                   <g:submitButton name="gotoCancel" value="Cancel"></g:submitButton>
                </div>
            </g:form>
        </div>
    </body>
</html>
