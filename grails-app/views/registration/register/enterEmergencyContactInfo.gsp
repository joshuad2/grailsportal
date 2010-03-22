<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Enter Emergency Contact Information</title> 
    <gui:resources components="['accordion']"/>        
    </head>
    <body>

<div class="body">
        <div class="nav">

            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" controller="registration" action="newRegistration">Register Another Child</g:link></span>
         </div>
  <div class="dialog" style="width:800px;font-size:16;border: medium;font-family: Verdana;padding-top: 20;padding-bottom: 20;padding-left: 10;padding-right: 20;border-style: solid">
Please enter all Emergency Contacts that you would like us to call if there is an emergency with your child.  As many contacts as possible is recommended.
<br>
The "*" indicates a mandatory field.
</div>
            <h1>Emergency Contact Information for Registration</h1>
            <g:if test="${message}">
            <div class="message">${message}</div>
            </g:if>
            <g:hasErrors bean="${errorInstance}">
            <div class="errors">
                <g:renderErrors bean="${errorInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="register" method="post" >
      <gui:accordion>
        <gui:accordionElement title="Emergency Contact 1">
                <div class="dialog">
                    <table>
                        <th>
                        <tr><td colspan="2"><h2>Emergency Contact</h2></td></tr>
                        </th>
                        <tbody> 
                             <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="firstName_1">First Name*:</label>
                                </td>
                                <g:if test="${regEvent.emergencyContact1!=null && regEvent.emergencyContact1.contact!=null}">
                                  <td valign="top" class="value ${hasErrors(bean:regEvent.emergencyContact1.contact,field:'firstName','errors')}">
                                  <g:textField size="60" maxLength="100" name="firstName_1" value="${fieldValue(bean:regEvent.emergencyContact1.contact,field:'firstName')}" />
                                 </g:if>
                                 <g:else>
                                   <td valign="top">
                                   <g:textField size="60" maxLength="100" name="firstName_1" />
                                 </g:else> 
                                </td>
                            </tr> 
                           <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="lastName_1">Last Name*:</label>
                                </td>
                                <g:if test="${regEvent.emergencyContact1!=null && regEvent.emergencyContact1.contact!=null}">
                                   <td valign="top" class="value ${hasErrors(bean:regEvent.emergencyContact1.contact,field:'lastName','errors')}">
                                   <g:textField size="60" maxLength="100" name="lastName_1" value="${fieldValue(bean:regEvent.emergencyContact1.contact,field:'lastName')}" />
                                   </td>
                                 </g:if>
                                 <g:else>
                                   <td valign="top">
                                   <g:textField size="60" maxLength="100" name="lastName_1" />
                                   </td>
                                 </g:else>
                            </tr>
                              <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="homePhoneNumber_1">Home Phone Number:</label>
                                </td>
                                <g:if test="${regEvent.emergencyContact1!=null && regEvent.emergencyContact1.homePhone!=null}">
                                  <td valign="top" class="value ${hasErrors(bean:regEvent.emergencyContact1.homePhone,field:'phoneNumber','errors')}">
                                    <g:textField size="13" maxLength="13" name="homePhoneNumber_1" value="${fieldValue(bean:regEvent.emergencyContact1.homePhone,field:'phoneNumber')}"/>
                                  </td>
                                </g:if>
                                <g:else>
                                  <td valign="top">
                                    <g:textField size="13" maxLength="13" name="homePhoneNumber_1"/>
                                  </td>
                                </g:else>
                            </tr>    
                             <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="cellPhoneNumber_1">Cell Phone Number:</label>
                                </td>            
                                <g:if test="${regEvent.emergencyContact1!=null && regEvent.emergencyContact1.cellPhone!=null}">
                                  <td valign="top" class="value ${hasErrors(bean:regEvent.emergencyContact1.cellPhone,field:'phoneNumber','errors')}">
                                     <g:textField  size="13" maxLength="13" name="cellPhoneNumber_1" value="${fieldValue(bean:regEvent.emergencyContact1.cellPhone,field:'phoneNumber')}"/>
                                  </td>
                                 </g:if>
                                 <g:else>
                                     <td valign="top">
                                     <g:textField  size="13" maxLength="13" name="cellPhoneNumber_1" />
                                  </td>
                                 </g:else>
                            </tr>   
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="workPhoneNumber_1">Work Phone Number:</label>
                                </td>
                                <g:if test="${regEvent.emergencyContact1!=null && regEvent.emergencyContact1.workPhone!=null}">
                                   <td valign="top" class="value ${hasErrors(bean:regEvent.emergencyContact1.workPhone,field:'phoneNumber','errors')}">
                                     <g:textField  size="13" maxLength="13" name="workPhoneNumber_1" value="${fieldValue(bean:regEvent.emergencyContact1.workPhone,field:'phoneNumber')}"/>
                                   </td>
                                 </g:if>
                                 <g:else>
                                     <td valign="top">
                                       <g:textField  size="13" maxLength="13" name="workPhoneNumber_1" />
                                     </td>
                                 </g:else>
                            </tr>
                             <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="emailAddress_1">Email Address:</label>
                                </td>
                                <g:if test="${regEvent.emergencyContact1!=null && regEvent.emergencyContact1.email!=null}">
                                  <td valign="top" class="value ${hasErrors(bean:regEvent.emergencyContact1.email,field:'emailAddress','errors')}">
                                     <g:textField  size="50" maxLength="100" name="emailAddress_1" value="${fieldValue(bean:regEvent.emergencyContact1.email,field:'emailAddress')}"/>
                                  </td>
                                 </g:if>
                                 <g:else>
                                    <td valign="top">
                                       <g:textField  size="50" maxLength="100" name="emailAddress_1" />
                                    </td>
                                 </g:else>
                            </tr>                                    
                        </tbody>
                    </table>
                </div>
        </gui:accordionElement>
        <gui:accordionElement title="Emergency Contact 2">
                <div class="dialog">
                    <table>
                        <th>
                        <tr><td colspan="2"><h2>Emergency Contact</h2></td></tr>
                        </th>
                        <tbody> 
                             <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="firstName_2">First Name*:</label>
                                </td>
                                <g:if test="${regEvent.emergencyContact2!=null && regEvent.emergencyContact2.contact!=null}">
                                  <td valign="top" class="value ${hasErrors(bean:regEvent.emergencyContact2.contact,field:'firstName','errors')}">
                                  <g:textField size="60" maxLength="100" name="firstName_2" value="${fieldValue(bean:regEvent.emergencyContact2.contact,field:'firstName')}" />
                                 </g:if>
                                 <g:else>
                                   <td valign="top">
                                   <g:textField size="60" maxLength="100" name="firstName_2" />
                                 </g:else> 
                                </td>
                            </tr> 
                           <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="lastName_2">Last Name*:</label>
                                </td>
                                <g:if test="${regEvent.emergencyContact2!=null && regEvent.emergencyContact2.contact!=null}">
                                   <td valign="top" class="value ${hasErrors(bean:regEvent.emergencyContact2.contact,field:'lastName','errors')}">
                                   <g:textField size="60" maxLength="100" name="lastName_2" value="${fieldValue(bean:regEvent.emergencyContact2.contact,field:'lastName')}" />
                                   </td>
                                 </g:if>
                                 <g:else>
                                   <td valign="top">
                                   <g:textField size="60" maxLength="100" name="lastName_2" />
                                   </td>
                                 </g:else>
                            </tr>
                              <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="homePhoneNumber_2">Home Phone Number:</label>
                                </td>
                                <g:if test="${regEvent.emergencyContact2!=null && regEvent.emergencyContact2.homePhone!=null}">
                                  <td valign="top" class="value ${hasErrors(bean:regEvent.emergencyContact2.homePhone,field:'phoneNumber','errors')}">
                                    <g:textField size="13" maxLength="13" name="homePhoneNumber_2" value="${fieldValue(bean:regEvent.emergencyContact2.homePhone,field:'phoneNumber')}"/>
                                  </td>
                                </g:if>
                                <g:else>
                                  <td valign="top">
                                    <g:textField size="13" maxLength="13" name="homePhoneNumber_2"/>
                                  </td>
                                </g:else>
                            </tr>    
                             <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="cellPhoneNumber_2">Cell Phone Number:</label>
                                </td>            
                                <g:if test="${regEvent.emergencyContact2!=null && regEvent.emergencyContact2.cellPhone!=null}">
                                  <td valign="top" class="value ${hasErrors(bean:regEvent.emergencyContact2.cellPhone,field:'phoneNumber','errors')}">
                                     <g:textField  size="13" maxLength="13" name="cellPhoneNumber_2" value="${fieldValue(bean:regEvent.emergencyContact2.cellPhone,field:'phoneNumber')}"/>
                                  </td>
                                 </g:if>
                                 <g:else>
                                     <td valign="top">
                                     <g:textField  size="13" maxLength="13" name="cellPhoneNumber_2" />
                                  </td>
                                 </g:else>
                            </tr>   
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="workPhoneNumber_2">Work Phone Number:</label>
                                </td>
                                <g:if test="${regEvent.emergencyContact2!=null && regEvent.emergencyContact2.workPhone!=null}">
                                   <td valign="top" class="value ${hasErrors(bean:regEvent.emergencyContact2.workPhone,field:'phoneNumber','errors')}">
                                     <g:textField  size="13" maxLength="13" name="workPhoneNumber_2" value="${fieldValue(bean:regEvent.emergencyContact2.workPhone,field:'phoneNumber')}"/>
                                   </td>
                                 </g:if>
                                 <g:else>
                                     <td valign="top">
                                       <g:textField  size="13" maxLength="13" name="workPhoneNumber_2" />
                                     </td>
                                 </g:else>
                            </tr>
                             <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="emailAddress_2">Email Address:</label>
                                </td>
                                <g:if test="${regEvent.emergencyContact2!=null && regEvent.emergencyContact2.email!=null}">
                                  <td valign="top" class="value ${hasErrors(bean:regEvent.emergencyContact2.email,field:'emailAddress','errors')}">
                                     <g:textField  size="50" maxLength="100" name="emailAddress_2" value="${fieldValue(bean:regEvent.emergencyContact2.email,field:'emailAddress')}"/>
                                  </td>
                                 </g:if>
                                 <g:else>
                                    <td valign="top">
                                       <g:textField  size="50" maxLength="100" name="emailAddress_2" />
                                    </td>
                                 </g:else>
                            </tr>                                    
                        </tbody>
                    </table>
                </div>
        </gui:accordionElement>
        <gui:accordionElement title="Emergency Contact 3">
                <div class="dialog">
                    <table>
                        <th>
                        <tr><td colspan="2"><h2>Emergency Contact</h3></td></tr>
                        </th>
                        <tbody> 
                             <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="firstName_3">First Name*:</label>
                                </td>
                                <g:if test="${regEvent.emergencyContact3!=null && regEvent.emergencyContact3.contact!=null}">
                                  <td valign="top" class="value ${hasErrors(bean:regEvent.emergencyContact3.contact,field:'firstName','errors')}">
                                  <g:textField size="60" maxLength="100" name="firstName_3" value="${fieldValue(bean:regEvent.emergencyContact3.contact,field:'firstName')}" />
                                 </g:if>
                                 <g:else>
                                   <td valign="top">
                                   <g:textField size="60" maxLength="100" name="firstName_3" />
                                 </g:else> 
                                </td>
                            </tr> 
                           <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="lastName_3">Last Name*:</label>
                                </td>
                                <g:if test="${regEvent.emergencyContact3!=null && regEvent.emergencyContact3.contact!=null}">
                                   <td valign="top" class="value ${hasErrors(bean:regEvent.emergencyContact3.contact,field:'lastName','errors')}">
                                   <g:textField size="60" maxLength="100" name="lastName_3" value="${fieldValue(bean:regEvent.emergencyContact3.contact,field:'lastName')}" />
                                   </td>
                                 </g:if>
                                 <g:else>
                                   <td valign="top">
                                   <g:textField size="60" maxLength="100" name="lastName_3" />
                                   </td>
                                 </g:else>
                            </tr>
                              <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="homePhoneNumber_3">Home Phone Number:</label>
                                </td>
                                <g:if test="${regEvent.emergencyContact3!=null && regEvent.emergencyContact3.homePhone!=null}">
                                  <td valign="top" class="value ${hasErrors(bean:regEvent.emergencyContact3.homePhone,field:'phoneNumber','errors')}">
                                    <g:textField size="13" maxLength="13" name="homePhoneNumber_3" value="${fieldValue(bean:regEvent.emergencyContact3.homePhone,field:'phoneNumber')}"/>
                                  </td>
                                </g:if>
                                <g:else>
                                  <td valign="top">
                                    <g:textField size="13" maxLength="13" name="homePhoneNumber_3"/>
                                  </td>
                                </g:else>
                            </tr>    
                             <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="cellPhoneNumber_3">Cell Phone Number:</label>
                                </td>            
                                <g:if test="${regEvent.emergencyContact3!=null && regEvent.emergencyContact3.cellPhone!=null}">
                                  <td valign="top" class="value ${hasErrors(bean:regEvent.emergencyContact3.cellPhone,field:'phoneNumber','errors')}">
                                     <g:textField  size="13" maxLength="13" name="cellPhoneNumber_3" value="${fieldValue(bean:regEvent.emergencyContact3.cellPhone,field:'phoneNumber')}"/>
                                  </td>
                                 </g:if>
                                 <g:else>
                                     <td valign="top">
                                     <g:textField  size="13" maxLength="13" name="cellPhoneNumber_3" />
                                  </td>
                                 </g:else>
                            </tr>   
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="workPhoneNumber_3">Work Phone Number:</label>
                                </td>
                                <g:if test="${regEvent.emergencyContact3!=null && regEvent.emergencyContact3.workPhone!=null}">
                                   <td valign="top" class="value ${hasErrors(bean:regEvent.emergencyContact3.workPhone,field:'phoneNumber','errors')}">
                                     <g:textField  size="13" maxLength="13" name="workPhoneNumber_3" value="${fieldValue(bean:regEvent.emergencyContact3.workPhone,field:'phoneNumber')}"/>
                                   </td>
                                 </g:if>
                                 <g:else>
                                     <td valign="top">
                                       <g:textField  size="13" maxLength="13" name="workPhoneNumber_3" />
                                     </td>
                                 </g:else>
                            </tr>
                             <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="emailAddress_3">Email Address:</label>
                                </td>
                                <g:if test="${regEvent.emergencyContact3!=null && regEvent.emergencyContact3.email!=null}">
                                  <td valign="top" class="value ${hasErrors(bean:regEvent.emergencyContact3.email,field:'emailAddress','errors')}">
                                     <g:textField  size="50" maxLength="100" name="emailAddress_3" value="${fieldValue(bean:regEvent.emergencyContact3.email,field:'emailAddress')}"/>
                                  </td>
                                 </g:if>
                                 <g:else>
                                    <td valign="top">
                                       <g:textField  size="50" maxLength="100" name="emailAddress_3" />
                                    </td>
                                 </g:else>
                            </tr>                                    
                        </tbody>
                    </table>
                </div>
        </gui:accordionElement>
                 <div class="buttons">
                   <g:submitButton name="gotoMedicalInfo" value="Next"></g:submitButton>
                   <g:submitButton name="gotoCancel" value="Cancel"></g:submitButton>
                </div>
                    </gui:accordion>
            </g:form>
        </div>
    </body>
</html>
