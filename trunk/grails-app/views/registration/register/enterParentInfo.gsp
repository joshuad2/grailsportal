<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
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
            <g:hasErrors bean="${parentInstance}">
            <div class="errors">
                <g:renderErrors bean="${parentInstance.errors}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="register" method="post" >
                <div class="dialog">
                    <table>
                        <tbody> 
                             <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="firstName">First Name*:</label>
                                </td>
                                <g:if test="${regEvent.parent1!=null && regEvent.parent1.parent!=null}">
                                  <td valign="top" class="value ${hasErrors(bean:regEvent.parent1.parent,field:'firstName','errors')}">
                                  <g:textField size="60" maxLength="100" name="firstName" value="${fieldValue(bean:regEvent.parent1.parent,field:'firstName')}" />
                                 </g:if>
                                 <g:else>
                                   <td valign="top">
                                   <g:textField size="60" maxLength="100" name="firstName" />
                                 </g:else> 
                                </td>
                            </tr> 
                           <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="lastName">Last Name*:</label>
                                </td>
                                <g:if test="${regEvent.parent1!=null && regEvent.parent1.parent!=null}">
                                   <td valign="top" class="value ${hasErrors(bean:regEvent.parent1.parent,field:'lastName','errors')}">
                                   <g:textField size="60" maxLength="100" name="lastName" value="${fieldValue(bean:regEvent.parent1.parent,field:'lastName')}" />
                                   </td>
                                 </g:if>
                                 <g:else>
                                   <td valign="top">
                                   <g:textField size="60" maxLength="100" name="lastName" />
                                   </td>
                                 </g:else>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="Address1">Address*:</label>
                                </td>
                                <g:if test="${regEvent.parent1!=null && regEvent.parent1.address!=null}">
                                  <td valign="top" class="value ${hasErrors(bean:regEvent.parent1.address,field:'address1','errors')}">
                                    <g:textField size="60" maxLength="100" name="address1" value="${fieldValue(bean:regEvent.parent1.address,field:'address1')}" />
                                  </td>
                                </g:if>
                                <g:else test="${regEvent.parent1.address!=null}">
                                   <td valign="top">
                                     <g:textField size="60" maxLength="100" name="address1"/>
                                   </td>
                                </g:else>
                            </tr> 
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="Address Line 2">Address 2:</label>
                                </td>
                                <g:if test="${regEvent.parent1!=null && regEvent.parent1.address!=null}">
                                  <td valign="top" class="value ${hasErrors(bean:regEvent.parent1.address,field:'address2','errors')}">
                                    <g:textField size="60" maxLength="100" name="address2" value="${fieldValue(bean:regEvent.parent1.address,field:'address2')}" />
                                  </td>
                                </g:if>
                                <g:else>
                                  <td valign="top">
                                    <g:textField size="60" maxLength="100" name="address2"/>
                                  </td>
                                </g:else>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="City">City*:</label>
                                </td>
                                <g:if test="${regEvent.parent1!=null && regEvent.parent1.address!=null}">
                                  <td valign="top" class="value ${hasErrors(bean:regEvent.parent1.address,field:'city','errors')}">
                                    <g:textField size="60" maxLength="100" name="city" value="${fieldValue(bean:regEvent.parent1.address,field:'city')}" />
                                  </td>
                                </g:if>
                                <g:else>
                                 <td valign="top">
                                    <g:textField size="60" maxLength="100" name="city"/>
                                  </td>
                                </g:else>
                            </tr> 
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="state">State*:</label>
                                </td>
                                <g:if test="${regEvent.parent1!=null && regEvent.parent1.address!=null}">
                                  <td valign="top" class="value ${hasErrors(bean:regEvent.parent1.address,field:'state','errors')}">
                                    <g:select name="state" from="${['FL','GA', 'AL', 'TN','TX','NC','SC','VA','PR']}"  noSelection="['':'-Choose the State-']" value="${fieldValue(bean:regEvent.parent1.address,field:'state')}"/>
                                  </td>
                                 </g:if>
                                 <g:else>
                                    <td valign="top">
                                      <g:select name="state" from="${['FL','GA', 'AL', 'TN','TX','NC','SC','VA','PR']}"  noSelection="['':'-Choose the State-']"/>
                                  </td>
                                 </g:else>
                            </tr>           
                              <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="Zipcode">Zip Code*:</label>
                                </td>
                                <g:if test="${regEvent.parent1!=null && regEvent.parent1.address!=null}">
                                  <td valign="top" class="value ${hasErrors(bean:regEvent.parent1.address,field:'zipcode','errors')}">
                                    <g:textField size="5" maxLength="5" name="zipcode" value="${fieldValue(bean:regEvent.parent1.address,field:'zipcode')}"/>
                                  </td>
                                </g:if>
                                <g:else>
                                    <td valign="top" >
                                    <g:textField size="5" maxLength="5" name="zipcode" />
                                  </td>
                                </g:else>
                            </tr>   
                              <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="phoneNumber">Home Phone Number:</label>
                                </td>
                                <g:if test="${regEvent.parent1!=null && regEvent.parent1.homePhone!=null}">
                                  <td valign="top" class="value ${hasErrors(bean:regEvent.parent1.homePhone,field:'phoneNumber','errors')}">
                                    <g:textField size="13" maxLength="13" name="homePhoneNumber" value="${fieldValue(bean:regEvent.parent1.homePhone,field:'phoneNumber')}"/>
                                  </td>
                                </g:if>
                                <g:else>
                                  <td valign="top">
                                    <g:textField size="13" maxLength="13" name="homePhoneNumber"/>
                                  </td>
                                </g:else>
                            </tr>    
                             <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="phoneNumber">Cell Phone Number*:</label>
                                </td>            
                                <g:if test="${regEvent.parent1!=null && regEvent.parent1.cellPhone!=null}">
                                  <td valign="top" class="value ${hasErrors(bean:regEvent.parent1.cellPhone,field:'phoneNumber','errors')}">
                                     <g:textField  size="13" maxLength="13" name="cellPhoneNumber" value="${fieldValue(bean:regEvent.parent1.cellPhone,field:'phoneNumber')}"/>
                                  </td>
                                 </g:if>
                                 <g:else>
                                     <td valign="top">
                                     <g:textField  size="13" maxLength="13" name="cellPhoneNumber" />
                                  </td>
                                 </g:else>
                            </tr>   
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="phoneNumber">Work Phone Number:</label>
                                </td>
                                <g:if test="${regEvent.parent1!=null && regEvent.parent1.workPhone!=null}">
                                   <td valign="top" class="value ${hasErrors(bean:regEvent.parent1.workPhone,field:'phoneNumber','errors')}">
                                     <g:textField  size="13" maxLength="13" name="workPhoneNumber" value="${fieldValue(bean:regEvent.parent1.workPhone,field:'phoneNumber')}"/>
                                   </td>
                                 </g:if>
                                 <g:else>
                                     <td valign="top">
                                       <g:textField  size="13" maxLength="13" name="workPhoneNumber" />
                                     </td>
                                 </g:else>
                            </tr>
                             <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="phoneNumber">Email Address*:</label>
                                </td>
                                <g:if test="${regEvent.parent1!=null && regEvent.parent1.email!=null}">
                                  <td valign="top" class="value ${hasErrors(bean:regEvent.parent1.email,field:'emailAddress','errors')}">
                                     <g:textField  size="50" maxLength="100" name="emailAddress" value="${fieldValue(bean:regEvent.parent1.email,field:'emailAddress')}"/>
                                  </td>
                                 </g:if>
                                 <g:else>
                                    <td valign="top">
                                       <g:textField  size="50" maxLength="100" name="emailAddress" />
                                    </td>
                                 </g:else>
                            </tr>                                    
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                   <g:submitButton name="gotoParentInfo2" value="Next"></g:submitButton>
                   <g:submitButton name="gotoCancel" value="Cancel"></g:submitButton>
                </div>
            </g:form>
        </div>
    </body>
</html>
