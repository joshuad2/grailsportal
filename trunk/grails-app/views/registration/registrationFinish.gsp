<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link rel="stylesheet" href="${resource(dir:'css',file:'register.css')}" />
        <meta name="layout" content="main" />
        <title>Registration - Finished Registration</title>         
    </head>
<body>
<div class="body">
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" controller="registration" action="newRegistration">Register Another Child</g:link></span>
            <span class="menuButton"><g:link class="list" controller="registration" action="register">Edit this Registration</g:link></span>
         </div>
  <div id="registerOuter" class="registerOuter">
<div id="registerOuterBlock" class="registerOuterBlock"> 
  <div id="registerInnerBlock" class="registerInnerBlock">
    <div id="orderInformation" class="orderInformation">
      <div id="registeredBy" class="registeredBy">Registered By:${request.regEvent.registrationUser.party.firstName+" "+request.regEvent.registrationUser.party.lastName}</div>
      <div id="totalCost" class="totalCost" >Total Cost:${request.regEvent.orderRecord.totalAmount}</div>
      <div id="registrationIdNum" class="registrationIdNum" >Registration Number:${request.regEvent.orderRecord.id}</div>
    </div>
  <div id="registrationInformation" class="registrationInformation">
    <div id="registrationHeader" class="registrationHeader">registration Information</div>
    <div id="registrationName">Name: ${request.regEvent.registrationFor.firstName+" "+request.regEvent.registrationFor.lastName}</div>
  </div>
  <div id="ParentInformation1" class="ParentInformation">
      <div id="parentHeader1" class="parentHeader">Parent/Guardian Information</div>
      <div id="parentName">Name: ${request.regEvent.parent1.parent.firstName+" "+request.regEvent.parent1.parent.lastName}</div>
      <div id="parentAddress">Address: ${request.regEvent.parent1.address.address1+" "}</div>
      <g:if test="${request.regEvent.parent1.address.address2!=null}">
        <div id="parentAddress2">${request.regEvent.parent1.address.address2}</div>
      </g:if>
      <div id="parentCity">City: ${request.regEvent.parent1.address.city}</div>
      <div id="parentState">State: ${request.regEvent.parent1.address.state}</div>
      <div id="parentZip">Zip Code: ${request.regEvent.parent1.address.zipcode}</div>
      <g:if test="${request.regEvent.parent1.homePhone!=null}">
        <div id="parentHomePhone">Home Phone: ${request.regEvent.parent1.homePhone.phoneNumber}</div>
      </g:if>
      <div id="parentCellPhone">Cell Phone: ${request.regEvent.parent1.cellPhone.phoneNumber}</div>
      <g:if test="${request.regEvent.parent1.workPhone!=null}">
        <div id="parentWorkPhone">Work Phone: ${request.regEvent.parent1.workPhone.phoneNumber}</div>
      </g:if>
      <div id="parentEmailAddress">Email Address: ${request.regEvent.parent1.email.emailAddress}</div>
  </div>
  <g:if test="${request.regEvent.parent2!=null && request.regEvent.parent2.parent!=null}">
    <div id="ParentInformation2" class="ParentInformation">
      <div id="parentHeader2" class="parentHeader">Parent/Guardian Information</div>
      <div id="parentName">Name: ${request.regEvent.parent2.parent.firstName+" "+request.regEvent.parent2.parent.lastName}</div>
      <div id="parentAddress">Address: ${request.regEvent.parent2.address.address1+" "}</div>
      <g:if test="${request.regEvent.parent2.address.address2!=null}">
        <div id="parentAddress2">${request.regEvent.parent2.address.address2}</div>
      </g:if>
      <div id="parentCity">City: ${request.regEvent.parent2.address.city}</div>
      <div id="parentState">State: ${request.regEvent.parent2.address.state}</div>
      <div id="parentZip">Zip Code: ${request.regEvent.parent2.address.zipcode}</div>
      <g:if test="${request.regEvent.parent2.homePhone!=null}">
        <div id="parentHomePhone">Home Phone: ${request.regEvent.parent2.homePhone.phoneNumber}</div>
      </g:if>
      <div id="parentCellPhone">Cell Phone: ${request.regEvent.parent2.cellPhone.phoneNumber}</div>
      <g:if test="${request.regEvent.parent2.workPhone!=null}">
        <div id="parentWorkPhone">Work Phone: ${request.regEvent.parent2.workPhone.phoneNumber}</div>
      </g:if>
      <div id="parentEmailAddress">Email Address: ${request.regEvent.parent2.email.emailAddress}</div>
  </div>
 </g:if>
    <g:if test="${request.regEvent.emergencyContact1==null && request.regEvent.emergencyContact2==null && request.regEvent.emergencyContact3==null}">
      <div id="EmergencyContactInformation" class="EmergencyContactInformation">No Emergency Contact Information</div>
    </g:if>
    <g:else>
    <div id="eci" class="eci">
    <div id="ec1Header" class="ecHeader">Emergency Contact Information</div>
    <g:if test="${request.regEvent.emergencyContact1!=null}">
      <div id="ec1Info" class="ecInfo">
        <div id="ec1Name">Name: ${request.regEvent.emergencyContact1.contact.firstName+" "+request.regEvent.emergencyContact1.contact.lastName}</div>
        <g:if test="${request.regEvent.emergencyContact1.homePhone!=null}">
          <div id="ec1HomePhone">Home Phone: ${request.regEvent.emergencyContact1.homePhone.phoneNumber}</div>
        </g:if>
        <g:if test="${request.regEvent.emergencyContact1.cellPhone}">
          <div id="ec1CellPhone">Cell Phone: ${request.regEvent.emergencyContact1.cellPhone.phoneNumber}</div>
        </g:if>
        <g:if test="${request.regEvent.emergencyContact1.workPhone!=null}">
          <div id="ec1WorkPhone">Work Phone: ${request.regEvent.emergencyContact1.workPhone.phoneNumber}</div>
        </g:if>
        <g:if test="${request.regEvent.emergencyContact1.email}">
          <div id="ec1EmailAddress">Email Address: ${request.regEvent.emergencyContact1.email.emailAddress}</div>
        </g:if>
      </div>
    </g:if>
    <g:if test="${request.regEvent.emergencyContact2!=null}">
      <div id="ec2Info" class="ecInfo">
        <div id="ec1Name">Name: ${request.regEvent.emergencyContact2.contact.firstName+" "+request.regEvent.emergencyContact2.contact.lastName}</div>
        <g:if test="${request.regEvent.emergencyContact2.homePhone!=null}">
          <div id="ec1HomePhone">Home Phone: ${request.regEvent.emergencyContact2.homePhone.phoneNumber}</div>
        </g:if>
        <g:if test="${request.regEvent.emergencyContact2.cellPhone!=null}">
           <div id="ec1CellPhone">Cell Phone: ${request.regEvent.emergencyContact2.cellPhone.phoneNumber}</div>
        </g:if>
        <g:if test="${request.regEvent.emergencyContact2.workPhone!=null}">
           <div id="ec1WorkPhone">Work Phone: ${request.regEvent.emergencyContact2.workPhone.phoneNumber}</div>
        </g:if>
        <g:if test="${request.regEvent.emergencyContact2.email!=null}">
           <div id="ec1EmailAddress">Email Address: ${request.regEvent.emergencyContact2.email.emailAddress}</div>
        </g:if>
      </div>
    </g:if>
    <g:if test="${request.regEvent.emergencyContact3!=null}">
      <div id="ec3Info" class="ecInfo">
        <div id="ec1Name">Name: ${request.regEvent.emergencyContact3.contact.firstName+" "+request.regEvent.emergencyContact3.contact.lastName}</div>
        <g:if test="${request.regEvent.emergencyContact3.homePhone!=null}">
          <div id="ec1HomePhone">Home Phone: ${request.regEvent.emergencyContact3.homePhone.phoneNumber}</div>
        </g:if>
        <g:if test="${request.regEvent.emergencyContact3.cellPhone!=null}">
          <div id="ec1CellPhone">Cell Phone: ${request.regEvent.emergencyContact3.cellPhone.phoneNumber}</div>
        </g:if>
        <g:if test="${request.regEvent.emergencyContact3.workPhone!=null}">
          <div id="ec1WorkPhone">Work Phone: ${request.regEvent.emergencyContact3.workPhone.phoneNumber}</div>
        </g:if>
        <g:if test="${request.regEvent.emergencyContact3.email!=null}">
          <div id="ec1EmailAddress">Email Address: ${request.regEvent.emergencyContact3.email.emailAddress}</div>
        </g:if>
      </div>
    </g:if>
  </div>
  </g:else>
  <g:if test="${request.regEvent.pickupContact1==null && request.regEvent.pickupContact2==null && request.regEvent.pickupContact3==null}">
      <div id="puContactInformation" class="puContactInfo">No Pickup Contact Information</div>
  </g:if>
  <g:else>
    <div id="pui" class="puci">
    <div id="puciHeader" class="puciHeader">Pickup Contact Information</div>
    <g:if test="${request.regEvent.pickupContact1!=null}">
      <div id="puInfo1" class="pucInfo">
        <div id="ec1Name">Name: ${request.regEvent.pickupContact1.contact.firstName+" "+request.regEvent.pickupContact1.contact.lastName}</div>
        <g:if test="${request.regEvent.pickupContact1.homePhone!=null}">
          <div id="ec1HomePhone">Home Phone: ${request.regEvent.pickupContact1.homePhone.phoneNumber}</div>
        </g:if>
        <g:if test="${request.regEvent.pickupContact1.cellPhone}">
          <div id="ec1CellPhone">Cell Phone: ${request.regEvent.pickupContact1.cellPhone.phoneNumber}</div>
        </g:if>
        <g:if test="${request.regEvent.pickupContact1.workPhone!=null}">
          <div id="ec1WorkPhone">Work Phone: ${request.regEvent.pickupContact1.workPhone.phoneNumber}</div>
        </g:if>
        <g:if test="${request.regEvent.pickupContact1.email}">
          <div id="ec1EmailAddress">Email Address: ${request.regEvent.pickupContact1.email.emailAddress}</div>
        </g:if>
      </div>
    </g:if>
    <g:if test="${request.regEvent.pickupContact2!=null}">
      <div id="puInfo2" class="pucInfo">
        <div id="ec1Name">Name: ${request.regEvent.pickupContact2.contact.firstName+" "+request.regEvent.pickupContact2.contact.lastName}</div>
        <g:if test="${request.regEvent.pickupContact2.homePhone!=null}">
          <div id="ec1HomePhone">Home Phone: ${request.regEvent.pickupContact2.homePhone.phoneNumber}</div>
        </g:if>
        <g:if test="${request.regEvent.pickupContact2.cellPhone!=null}">
           <div id="ec1CellPhone">Cell Phone: ${request.regEvent.pickupContact2.cellPhone.phoneNumber}</div>
        </g:if>
        <g:if test="${request.regEvent.pickupContact2.workPhone!=null}">
           <div id="ec1WorkPhone">Work Phone: ${request.regEvent.pickupContact2.workPhone.phoneNumber}</div>
        </g:if>
        <g:if test="${request.regEvent.pickupContact2.email!=null}">
           <div id="ec1EmailAddress">Email Address: ${request.regEvent.pickupContact2.email.emailAddress}</div>
        </g:if>
      </div>
    </g:if>
    <g:if test="${request.regEvent.pickupContact3!=null}">
      <div id="puInfo3" class="pucInfo">
        <div id="ec1Name">Name: ${request.regEvent.pickupContact3.contact.firstName+" "+request.regEvent.pickupContact3.contact.lastName}</div>
        <g:if test="${request.regEvent.pickupContact3.homePhone!=null}">
          <div id="ec1HomePhone">Home Phone: ${request.regEvent.pickupContact3.homePhone.phoneNumber}</div>
        </g:if>
        <g:if test="${request.regEvent.pickupContact3.cellPhone!=null}">
          <div id="ec1CellPhone">Cell Phone: ${request.regEvent.pickupContact3.cellPhone.phoneNumber}</div>
        </g:if>
        <g:if test="${request.regEvent.pickupContact3.workPhone!=null}">
          <div id="ec1WorkPhone">Work Phone: ${request.regEvent.pickupContact3.workPhone.phoneNumber}</div>
        </g:if>
        <g:if test="${request.regEvent.pickupContact3.email!=null}">
          <div id="ec1EmailAddress">Email Address: ${request.regEvent.pickupContact3.email.emailAddress}</div>
        </g:if>
      </div>
    </g:if>
  </div>
  </g:else>
  <div id="drPart" class="drPart">
  <div id="drHeader" class="drHeader">Doctor Information</div>
  <g:if test="${request.regEvent.registrationDoctor!=null}">
        <div id="drInfo" class="drInfo">
          <div id="ec1Name">Name: ${request.regEvent.registrationDoctor.doctor.firstName+" "+request.regEvent.registrationDoctor.doctor.lastName}</div>
          <g:if test="${request.regEvent.registrationDoctor.officePhoneNumber!=null}">
            <div id="drWorkPhone">Office Phone Number: ${request.regEvent.registrationDoctor.officePhoneNumber.phoneNumber}</div>
          </g:if>
        </div>
    </g:if>
    <g:else>
      <div id="noDrInfo" class="noDrInfo">No Doctor Information</div>
    </g:else>
  </div>
  </div>
  </div>
</body>
</html>