<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <gui:resources components="['accordion']"/>  
		<g:javascript library="prototype" />
        <title>Registration - Enter Parent Information</title>         
    </head>
    <body>
        <div class="body">
          <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" controller="registration" action="newRegistration">Register Another Child</g:link></span>
          </div>
          <div class="dialog" style="width:800px;font-size:16;border: medium;font-family: Verdana;padding-top: 20;padding-bottom: 20;padding-left: 10;padding-right: 20;border-style: solid">
Please enter the information for the parent that will be primarily responsible for the registrant.  
<br>
The "*" indicates a mandatory field.
         </div>
            <h1>Parent Information for Registration</h1>
            <g:hasErrors bean="${parent}">
              <div class="errors">
                <g:renderErrors bean="${parent.errors}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="register" method="post" >
                <div class="dialog">
                      <portal:formInputField 
                            fieldValue="${fieldValue(bean:parent,field:'firstName')}" 
                            labelField="firstName"
                            fieldName="firstName"
                            field="firstName"
                            fieldLength="100"
                            label="First Name:*" fieldName="firstName"/> 
                          <portal:formInputField 
                            fieldValue="${fieldValue(bean:parent,field:'lastName')}" 
                            labelField="lastName"
                            fieldName="lastName"
                            fieldLength="100"
                            field="lastName"
                            label="Last Name:*" fieldName="lastName"/>
                  <div class="buttons">
                   <g:submitButton name="saveParentInfo" value="Save"></g:submitButton> 
                  </div>
            </g:form>
            <g:if test="${parent.id != null}">
              <portal:phone id="${parent.id}"/>
              <portal:email id="${parent.id}"/>
              <portal:address id="${parent.id}"/>                         
              <g:form>
                <div class="buttons">
                   <g:submitButton name="gotoParentInfo2" value="Next"></g:submitButton>
                   <g:submitButton name="gotoCancel" value="Cancel"></g:submitButton>
                </div>
              </g:form>
            </g:if>   
        </div>
    </body>
</html>
