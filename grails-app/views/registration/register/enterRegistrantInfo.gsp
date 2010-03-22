<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Event Registration - Enter Registrant Information</title>         
    </head>
    <body>
<div class="body">
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" controller="registration" action="newRegistration">Register Another Child</g:link></span>
         </div>
  <div class="dialog" style="width:800px;border: medium;font-size:16;font-family: Verdana;padding-left: 10;padding-right: 20;padding-top: 20;padding-bottom: 20;border-style: solid">
Please enter the Registrant information for the individual you would like to register for our programs.  Please enter the age at the time the camps will be running and the grade that the child will be entering in the fall of 2009.
<br>
The "*" indicates a mandatory field.
</div>
            <h1>Enter the information for the Registration</h1>
            <g:if test="${message}">
              <div class="message">${message}</div>
            </g:if>
            <g:hasErrors bean="${registerInstance}">
            <div class="errors">
                <g:renderErrors bean="${registerInstance.errors}" as="list" />
            </div>
            </g:hasErrors>
            <g:form name="registrantForm" action="register" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                             <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="firstName">First Name*:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:regEvent.registrationFor,field:'firstName','errors')}">
                                  <g:textField size="60" maxLength="100" name="firstName" value="${regEvent.registrationFor.firstName}" />
                                </td>
                            </tr> 
                           <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="lastName">Last Name*:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:regEvent.registrationFor,field:'lastName','errors')}">
                                    <g:textField size="60" maxLength="100" name="lastName" value="${regEvent.registrationFor.lastName}" />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="birthDate">Birth Date*:</label>
                                </td>
                                <td valign="top">
                                     <g:datePicker name="birthDate" value="${regEvent.registrationFor.birthDate}" />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="registrationGrade">Grade:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:regEvent,field:'registrationGrade','errors')}">
                                  <g:select name="registrationGrade" from="${['Unknown','Kindergarten', 'First', 'Second','Third','Fourth','Fifth','Sixth','Seventh','Eighth','Freshman','Sophomore','Junior','Senior']}"  noSelection="['':'-Choose your Grade-']" value="${regEvent.registrationGrade}" />
                                </td>
                            </tr>                                            
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                   <g:submitButton name="gotoParentInfo" value="Next"></g:submitButton>
                   <g:submitButton name="gotoCancel" value="Cancel"></g:submitButton>
                </div>
            </g:form>
        </div>
    </body>
</html>