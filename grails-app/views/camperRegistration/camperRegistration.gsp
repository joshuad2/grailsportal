<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Camper Registration</title>         
    </head>
    <body>
<div class="body">
  <div class="dialog" style="width:800px;border: medium;font-size:16;font-family: Verdana;padding-left: 10;padding-right: 20;padding-top: 20;padding-bottom: 20;border-style: solid">
Please enter the Registrant information for the individual you would like to register for our programs.  Please enter the age at the time the camps will be running and the grade that the child will be entering in the fall of 2011.
<br>
The "*" indicates a mandatory field.
</div>
            <h1>Registering a chld for Camp:</h1>
            <g:if test="${message}">
              <div class="message">${message}</div>
            </g:if>
            <g:hasErrors bean="${registerInstance}">
            <div class="errors">
                <g:renderErrors bean="${registerInstance?.errors}" as="list" />
            </div>
            </g:hasErrors>
            <g:form name="registrantForm" action="campRegister" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                          <portal:formInputField 
                            fieldValue="${fieldValue(bean:regEvent?.registrationFor,field:'firstName')}" 
                            labelField="firstName"
                            fieldName="firstName"
                            field="firstName"
                            fieldLength="100"
                            label="First Name:*" fieldName="firstName"/> 
                          <portal:formInputField 
                            fieldValue="${fieldValue(bean:regEvent?.registrationFor,field:'lastName')}" 
                            labelField="lastName"
                            fieldName="lastName"
                            fieldLength="100"
                            field="lastName"
                            label="Last Name:*" fieldName="lastName"/>                                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="birthDate">Birth Date*:</label>
                                </td>
                                <td valign="top">
                                     <g:datePicker name="birthDate" value="${regEvent?.registrationFor?.birthDate}" />
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
                   <g:submitButton name="gotoParentInfo" value="save"></g:submitButton>
                </div>
            </g:form>
            <g:if test="regEvent.id != null">
              <registration:product regEventId="${regEvent?.id}" eCommerceCode="2011Camp" />
            </g:if>
        </div>
    </body>
</html>