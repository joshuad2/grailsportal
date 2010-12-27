<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Enter Second Parent Information</title>         
    </head>
    <body>
      <div class="dialog" style="width:800px;font-size:16;border: medium;font-family: Verdana;padding-top: 20;padding-bottom: 20;padding-left: 10;padding-right: 20;border-style: solid">
Please enter the information for the parent that will be secondarily responsible for the registrant.  
<br>
The "*" indicates a mandatory field.
</div>
        <div class="body">
                <div class="nav">

            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" controller="registration" action="newRegistration">Register Another Registrant</g:link></span>
         </div>
            <h1>Second Parent Information for Registration</h1>
            <g:if test="${message}">
            <div class="message">${message}</div>
            </g:if>
            <g:hasErrors bean="${parent2}">
            <div class="errors">
                <g:renderErrors bean="${parent2.errors}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="register" method="post" >
                <div class="dialog">
                    <table>
                        <th>
                        <tr><td colspan="2"><h2 >Secondary Parent</h2></td></tr>
                        </th>
            <h1>Parent Information for Registration</h1>
            <g:if test="${message}">
            <div class="message">${message}</div>
            </g:if>
            <g:hasErrors bean="${parent2}">
            <div class="errors">
                <g:renderErrors bean="${parent2.errors}" as="list" />
            </div>
            </g:hasErrors>
                <div class="dialog">
                           <portal:showParty id="${parent2.id}"/> 
                           <gui:accordion>
                            <gui:accordionElement title="Phone Numbers">
                              <portal:phone id="${parent2.id}"/>
                           </gui:accordionElement>
                           <gui:accordionElement title="Email">
                             <portal:email id="${parent2.id}"/>
                           </gui:accordionElement>
                           <gui:accordionElement title="Address">  
                            <portal:address id="${parent2.id }"/>
                          </gui:accordionElement> 
                          </gui:accordion>                              
                </div>
                <div class="buttons">
                   <g:submitButton name="gotoEmergencyContactInfo" value="Next"></g:submitButton>  
                   <g:submitButton name="gotoEmergencyContactInfoNoSave" value="Skip"></g:submitButton>       
                   <g:submitButton name="gotoCancel" value="Cancel"></g:submitButton>
                </div>
            </g:form>
        </div>
    </body>
    
</html>
