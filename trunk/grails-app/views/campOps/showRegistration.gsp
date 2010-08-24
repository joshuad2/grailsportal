	<html>
    <head>
        <title>Wellness Essentials Registration Report</title>
        <link rel="stylesheet" href="${resource(dir:'css',file:'register.css')}" />
		<meta name="layout" content="print" />
    </head>
<body>
<div class="body">
<div id="registerOuterBlock" class="registerOuterBlock"> 
  <div id="registerInnerBlock" class="registerInnerBlock">
    <div id="orderInformation" class="orderInformation">
      <div id="registeredBy" class="registeredBy">Registered By:${regEvent.registrationUser.party.firstName+" "+regEvent.registrationUser.party.lastName}</div>
      <div id="totalCost" class="totalCost" >Total Cost:${regEvent.orderRecord.totalAmount}</div>
      <div id="registrationIdNum" class="registrationIdNum" >Registration Number:${regEvent.orderRecord.id}</div>
    </div>
  <div id="camperInformation" class="camperInformation">
    <div id="camperHeader" class="camperHeader">Registrant Information</div>
    <div id="camperName">Name: ${regEvent.registrationFor.firstName+" "+regEvent.registrationFor.lastName}</div>
  </div>
  <div id="ParentInformation1" class="ParentInformation">
      <div id="parentHeader1" class="parentHeader">Parent/Guardian Information</div>
      <div id="parentName">Name: ${regEvent.parent1.parent.firstName+" "+regEvent.parent1.parent.lastName}</div>
      <div id="parentAddress">Address: ${regEvent.parent1.address.address1+" "}</div>
      <g:if test="${regEvent.parent1.address.address2!=null}">
        <div id="parentAddress2">${regEvent.parent1.address.address2}</div>
      </g:if>
      <div id="parentCity">City: ${regEvent.parent1.address.city}</div>
      <div id="parentState">State: ${regEvent.parent1.address.state}</div>
      <div id="parentZip">Zip Code: ${regEvent.parent1.address.zipcode}</div>
      <g:if test="${regEvent.parent1.homePhone!=null}">
        <div id="parentHomePhone">Home Phone: ${regEvent.parent1.homePhone.phoneNumber}</div>
      </g:if>
      <div id="parentCellPhone">Cell Phone: ${regEvent.parent1.cellPhone.phoneNumber}</div>
      <g:if test="${regEvent.parent1.workPhone!=null}">
        <div id="parentWorkPhone">Work Phone: ${regEvent.parent1.workPhone.phoneNumber}</div>
      </g:if>
      <div id="parentEmailAddress">Email Address: ${regEvent.parent1.email.emailAddress}</div>
  </div>
  <g:if test="${regEvent.parent2!=null && regEvent.parent2.parent!=null}">
    <div id="ParentInformation2" class="ParentInformation">
      <div id="parentHeader2" class="parentHeader">Parent/Guardian Information</div>
      <div id="parentName">Name: ${regEvent.parent2.parent.firstName+" "+regEvent.parent2.parent.lastName}</div>
      <div id="parentAddress">Address: ${regEvent.parent2.address.address1+" "}</div>
      <g:if test="${regEvent.parent2.address.address2!=null}">
        <div id="parentAddress2">${regEvent.parent2.address.address2}</div>
      </g:if>
      <div id="parentCity">City: ${regEvent.parent2.address.city}</div>
      <div id="parentState">State: ${regEvent.parent2.address.state}</div>
      <div id="parentZip">Zip Code: ${regEvent.parent2.address.zipcode}</div>
      <g:if test="${regEvent.parent2.homePhone!=null}">
        <div id="parentHomePhone">Home Phone: ${regEvent.parent2.homePhone.phoneNumber}</div>
      </g:if>
      <div id="parentCellPhone">Cell Phone: ${regEvent.parent2.cellPhone.phoneNumber}</div>
      <g:if test="${regEvent.parent2.workPhone!=null}">
        <div id="parentWorkPhone">Work Phone: ${regEvent.parent2.workPhone.phoneNumber}</div>
      </g:if>
      <div id="parentEmailAddress">Email Address: ${regEvent.parent2.email.emailAddress}</div>
  </div>
 </g:if>
    <g:if test="${regEvent.emergencyContact1==null && regEvent.emergencyContact2==null && regEvent.emergencyContact3==null}">
      <div id="EmergencyContactInformation" class="EmergencyContactInformation">No Emergency Contact Information</div>
    </g:if>
    <g:else>
    <div id="eci" class="eci">
    <div id="ec1Header" class="ecHeader">Emergency Contact Information</div>
    <g:if test="${regEvent.emergencyContact1!=null}">
      <div id="ec1Info" class="ecInfo">
        <div id="ec1Name">Name: ${regEvent.emergencyContact1.contact.firstName+" "+regEvent.emergencyContact1.contact.lastName}</div>
        <g:if test="${regEvent.emergencyContact1.homePhone!=null}">
          <div id="ec1HomePhone">Home Phone: ${regEvent.emergencyContact1.homePhone.phoneNumber}</div>
        </g:if>
        <g:if test="${regEvent.emergencyContact1.cellPhone}">
          <div id="ec1CellPhone">Cell Phone: ${regEvent.emergencyContact1.cellPhone.phoneNumber}</div>
        </g:if>
        <g:if test="${regEvent.emergencyContact1.workPhone!=null}">
          <div id="ec1WorkPhone">Work Phone: ${regEvent.emergencyContact1.workPhone.phoneNumber}</div>
        </g:if>
        <g:if test="${regEvent.emergencyContact1.email}">
          <div id="ec1EmailAddress">Email Address: ${regEvent.emergencyContact1.email.emailAddress}</div>
        </g:if>
      </div>
    </g:if>
    <g:if test="${regEvent.emergencyContact2!=null}">
      <div id="ec2Info" class="ecInfo">
        <div id="ec1Name">Name: ${regEvent.emergencyContact2.contact.firstName+" "+regEvent.emergencyContact2.contact.lastName}</div>
        <g:if test="${regEvent.emergencyContact2.homePhone!=null}">
          <div id="ec1HomePhone">Home Phone: ${regEvent.emergencyContact2.homePhone.phoneNumber}</div>
        </g:if>
        <g:if test="${regEvent.emergencyContact2.cellPhone!=null}">
           <div id="ec1CellPhone">Cell Phone: ${regEvent.emergencyContact2.cellPhone.phoneNumber}</div>
        </g:if>
        <g:if test="${regEvent.emergencyContact2.workPhone!=null}">
           <div id="ec1WorkPhone">Work Phone: ${regEvent.emergencyContact2.workPhone.phoneNumber}</div>
        </g:if>
        <g:if test="${regEvent.emergencyContact2.email!=null}">
           <div id="ec1EmailAddress">Email Address: ${regEvent.emergencyContact2.email.emailAddress}</div>
        </g:if>
      </div>
    </g:if>
    <g:if test="${regEvent.emergencyContact3!=null}">
      <div id="ec3Info" class="ecInfo">
        <div id="ec1Name">Name: ${regEvent.emergencyContact3.contact.firstName+" "+regEvent.emergencyContact3.contact.lastName}</div>
        <g:if test="${regEvent.emergencyContact3.homePhone!=null}">
          <div id="ec1HomePhone">Home Phone: ${regEvent.emergencyContact3.homePhone.phoneNumber}</div>
        </g:if>
        <g:if test="${regEvent.emergencyContact3.cellPhone!=null}">
          <div id="ec1CellPhone">Cell Phone: ${regEvent.emergencyContact3.cellPhone.phoneNumber}</div>
        </g:if>
        <g:if test="${regEvent.emergencyContact3.workPhone!=null}">
          <div id="ec1WorkPhone">Work Phone: ${regEvent.emergencyContact3.workPhone.phoneNumber}</div>
        </g:if>
        <g:if test="${regEvent.emergencyContact3.email!=null}">
          <div id="ec1EmailAddress">Email Address: ${regEvent.emergencyContact3.email.emailAddress}</div>
        </g:if>
      </div>
    </g:if>
  </div>
  </g:else>
  <div id="drPart" class="drPart">
  <div id="drHeader" class="drHeader">Doctor Information</div>
  <g:if test="${regEvent.registrationDoctor!=null}">
        <div id="drInfo" class="drInfo">
          <div id="ec1Name">Name: ${regEvent.registrationDoctor.doctor.firstName+" "+regEvent.registrationDoctor.doctor.lastName}</div>
          <g:if test="${regEvent.registrationDoctor.officePhoneNumber!=null}">
            <div id="drWorkPhone">Office Phone Number: ${regEvent.registrationDoctor.officePhoneNumber.phoneNumber}</div>
          </g:if>
        </div>
    </g:if>
    <g:else>
      <div id="noDrInfo" class="noDrInfo">No Doctor Information</div>
    </g:else>
  </div>
  </div>
  </body>
  </html>