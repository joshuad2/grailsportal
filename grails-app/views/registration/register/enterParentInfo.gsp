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
            <g:if test="${message}">
            <div class="message">${message}</div>
            </g:if>
            <g:hasErrors bean="${parent}">
            <div class="errors">
                <g:renderErrors bean="${parent.errors}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="register" method="post" >
                <div class="dialog">
                           <portal:showParty id="${parent.id}"/> 
                  <g:if test="${parent.id != null}">
                           <gui:accordion>
                            <gui:accordionElement title="Phone Numbers">
                              <portal:flowPhone id="${parent.id}" controller="registration" editAction="register" createAction="createPhone"/>
                           </gui:accordionElement>
                           <gui:accordionElement title="Email">
                             <portal:email id="${parent.id}" controller="registration" editAction="register" createAction="createEmail"/>
                           </gui:accordionElement>
                           <gui:accordionElement title="Address" >  
                            <portal:address id="${parent.id}" controller="registration" editAction="register" createAction="createAddress"/>
                          </gui:accordionElement> 
                          </gui:accordion>  
                  </g:if>                            
                </div>
                <div class="buttons">
                   <g:submitButton name="saveParentInfo" value="Save"></g:submitButton>
                   <g:submitButton name="gotoParentInfo2" value="Next"></g:submitButton>
                   <g:submitButton name="gotoCancel" value="Cancel"></g:submitButton>
                </div>
            </g:form>
        </div>
    </body>
</html>
