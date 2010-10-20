package com.grailsPortal.taglib

/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes
import com.grailsPortal.service.config.PortalviewService
import com.grailsPortal.ui.portalView.PortalViewHandler
import com.grailsPortal.domain.ContactType
import com.grailsPortal.domain.Party
import com.grailsPortal.domain.ContactPhone
class PortalTagLib {
	
	def contactUtilService
	static namespace = 'portal'
	/**
	 * This is the heading tag
	 */
	def heading={attrs, body-> 
		def ctx = servletContext.getAttribute(GrailsApplicationAttributes.APPLICATION_CONTEXT)
		def pc=ctx.getBean("portalviewService")
		out << pc.doHeader(attrs,body())
	}
	/**
	 * tag for displaying the address on the page using the attributes defined.  The view
	 * is queried for a specific standard attributeComponentGroup, in this case the address
	 * componengGroup is used.
	 * There are three modes that are implemented, -> read, modify, add 
	 * These modes enable specific ways of dealing with information.  In the future the
	 * fields will have security privilidges associated with them as well so that field
	 * level permissioning can be accomplished.
	 * Two mandatory attributes are needed for this tag
	 *   - viewName
	 *   - eventId  
	 */
	def addressAttributes={attrs->
		def ctx = servletContext.getAttribute(GrailsApplicationAttributes.APPLICATION_CONTEXT)
		def pc=ctx.getBean("PortalviewService")
		def attributeComponents=pc.getComponentGroupAttributesByViewAndGroup(attrs["viewName"], "address")
		def mode="edit"
		def attributeValues=pc.getRegistrationValuesForComponent(attrs["viewname"],"address",attrs["eventId"])
		out << pc.renderComponentGroup(mode,attrs["viewName"],"address",attributeComponents, attributeValues)
	}
	/**
	 * tag for showing all of the party information which is last name and first name in a table form
	 */
	def showParty={attrs->
		def id=attrs.id
		Party instance
		def fnameValue=""
		def lnameValue=""
		if (id!=null && id!=""){
			instance=Party.get(id)
			fnameValue=instance.firstName
			lnameValue=instance.lastName
		}
		else{
			instance=new Party()
		}
		def t="""
		      <tr class="prop">
                 <td valign="top" class="name">
                   <label for="firstName">First Name*:</label>
                 </td>
                 <td valign="top">
                   ${g.textField(["maxlength":"100",
                                 "id":"firstName",
                                 "name":"firstName",
                                 "value":"${fnameValue}"])
}
                 </td>
               </tr>                         
               <tr class="prop">
                 <td valign="top" class="name">
                   <label for="lastName">Last Name*:</label>
                 </td>
                 <td valign="top" >
                   ${g.textField(["maxlength":"100",
		"id":"lastName",
		"name":"lastName",
		"value":"${lnameValue}"])
}
                 </td>
               </tr> 
		"""
out <<t
}
/**
 * tag for displaying the phone on the page using the attributes defined.
 */
def phone={attrs->
def partyId=attrs.id
def controller=attrs.controller
def party=Party.get(partyId)
def contactTypes=contactUtilService.getProfilePhoneContactTypes(party)	
def editAction=attrs.editAction
def createAction=attrs.createAction	
if (editAction==null || editAction==""){
	editAction="edit"
}
if (createAction==null || createAction==""){
	createAction="create"
}
if (controller==null || controller==""){
	controller="contactPhone"
}
def t="""
	          <TABLE> 
	           """
if (party!=null){
	party.phoneList.each{
		def phn=it
		t+="""
                  <tr class="prop">
                    <td valign="top" class="name">
                      <label for="phoneNumber">Phone Number:</label>
                     </td>
                     <td valign="top" class="value">
                     (${phn.areaCode})${phn.phoneNumber}
                     </td>
                   </tr>
                   <tr class="prop">
                     <td valign="top" class="name">
                       <label for="contactType">
                       Type of Phone Number:
                       </label>
                     </td>
                     <td>
                     ${phn.contactType}
                     </td>
                   </tr>   
                   <tr>
                     <td colspan="2">
	                   ${g.link([controller:"${controller}",
		action:"${editAction}",
		id:"${phn.id}"],
	"Edit Phone Number")
} 
                     </td>
                   </tr>
                   <tr>
                     <td colspan="2">
                       <div style="border-style:solid;border-width:2px;"/>
                     </td>
                   </tr>"""
}
}
t+="""
	  <tr><td>Add</td>
  <td>"""
contactTypes.each{
def ct=it
t+="""
      ${g.link([controller:"${controller}",action:"${createAction}",params:["contactType.id":"${ct.id}","party.id":"${party.id}"]],"${ct.name}Phone")
}"""
}
t+="""
    </div>
    </td>
    </tr>
   </TABLE>
	"""
out << t
}
/**
* tag for displaying the phone on the page using the attributes defined.
*/
def flowPhone={attrs->
def partyId=attrs.id
def controller=attrs.controller
def party=Party.get(partyId)
def contactTypes=contactUtilService.getProfilePhoneContactTypes(party)
def editAction=attrs.editAction
def createAction=attrs.createAction
def t="""
			 <TABLE>
			  """
if (party!=null){
   party.phoneList.each{
	   def phn=it
	   t+="""
				 <tr class="prop">
				   <td valign="top" class="name">
					 <label for="phoneNumber">Phone Number:</label>
					</td>
					<td valign="top" class="value">
					(${phn.areaCode})${phn.phoneNumber}
					</td>
				  </tr>
				  <tr class="prop">
					<td valign="top" class="name">
					  <label for="contactType">
					  Type of Phone Number:
					  </label>
					</td>
					<td>
					${phn.contactType}
					</td>
				  </tr>
				  <tr>
					<td colspan="2">
					  ${g.link([controller:"${controller}",
	   action:"${editAction}",
	   id:"${phn.id}"],
   "Edit Phone Number")
}
					</td>
				  </tr>
				  <tr>
					<td colspan="2">
					  <div style="border-style:solid;border-width:2px;"/>
					</td>
				  </tr>"""
}
}
t+="""
	 <tr><td>Add</td>
 <td>"""
contactTypes.each{
def ct=it
t+="""
	 ${g.link([controller:"${controller}",action:"${createAction}",params:["contactType.id":"${ct.id}","party.id":"${party.id}"]],"${ct.name}Phone")
}"""
}
t+="""
   </div>
   </td>
   </tr>
  </TABLE>
   """
out << t
}
/**
 * tag for displaying the phone on the page using the attributes defined.
 */
def editPhone={attrs->
def partyId=attrs.id
def phoneId=attrs.phoneId
def controller=attrs.controller
def party=Party.get(partyId)
def phn=ContactPhone.get(phoneId)
def contactTypes=contactUtilService.getProfilePhoneContactTypes(party)
def editAction=attrs.editAction
if (editAction==null || editAction==""){
editAction="edit"
}
if (controller==null || controller==""){
controller="contactPhone"
}
def t="""
	           <TABLE>
				 <tr class="prop">
				   <td valign="top" class="name">
					 <label for="phoneNumber">Phone Number:</label>
					</td>
					<td valign="top" class="value">
					(${phn.areaCode})${phn.phoneNumber}
					</td>
				  </tr>
				  <tr class="prop">
					<td valign="top" class="name">
					  <label for="contactType">
					  Type of Phone Number:
					  </label>
					</td>
					<td>
					${phn.contactType}
					</td>
				  </tr>
				  <tr>
					<td colspan="2">
					  ${g.link([controller:"${controller}",
action:"${editAction}",
id:"${phn.id}"],
"Edit Phone Number")
}
					</td>
				  </tr>
				  <tr>
					<td colspan="2">
					  <div style="border-style:solid;border-width:2px;"/>
					</td>
				  </tr>
	 <tr><td>Add</td>
 <td>
   </div>
   </td>
   </tr>
  </TABLE>
   """
out << t
}


def address={attrs->
def partyId=attrs.id
def party
if (partyId==null || partyId==""){
party=new Party()
}
party=Party.get(partyId)
def controller=attrs.controller
def p=attrs.params
def addressContactTypes=contactUtilService.getProfileAddressContactTypes(party)
def editAction=attrs.editAction
def createAction=attrs.createAction	
if (editAction==null || editAction==""){
editAction="edit"
}
if (createAction==null || createAction==""){
createAction="create"
}
if (controller==null || controller==""){
controller="contactAddress"
}
def t="""
    <TABLE> 
     """
if (party!=null){
party.addressList.each{
def addr=it
t+="""
    <tr class="prop">
       <td valign="top" class="name"><label for="Address 1">Address*:</label></td>
       <td valign="top" class="value">${addr.address1}</td>
   </tr> 
   <tr class="prop">
       <td valign="top" class="name"><label for="Address 2">Address 2:</label></td>
       <td>${addr.address2}</td>
   </tr>
   <tr class="prop">
       <td valign="top" class="name"><label for="City">City:</label></td>
         <td valign="top" class="value">${addr.city}</td>
   </tr>
   <tr class="prop">
       <td valign="top" class="name"><label for="state">State:</label></td>
         <td valign="top" class="value">${addr.state}</td>
   </tr>           
   <tr class="prop">
       <td valign="top" class="name"><label for="Zipcode">Zip Code:</label></td>
       <td>${addr.zipcode}</td>
   </tr>
   <tr class="prop">
       <td valign="top" class="name"><label for="contactType">Type of Address:</label></td>
       <td>${addr.contactType}</td>
   </tr>
   <tr>
	 <td colspan="2">
		<div id="editTheContactAddress${addr.contactType}">
		  ${g.link([controller:"${controller}",action:"${editAction}",id:"${addr.id}",
params:["party":${party
},"contactType":${addr.contactType
}]],"Edit Address")
}
	    </div>
     </td>
   </tr>                            
   <tr>
	<td colspan="2">
		<div style="border-style:solid;border-width:2px;"></div>
	</td>
   </tr>
</tr>"""
}
}
t+="""
	  <tr><td>Add</td>
<td>
<div id="createTheContactAddress">"""
addressContactTypes.each{
ContactType ct=it
t+="""
    ${g.link([controller:"${controller}",action:"${createAction}",params:["contactType.id":"${ct.id}","party.id":"${party.id}"]],"${ct.name} Address")
}"""
}
t+="""
</div>
</td>
</tr>
</TABLE>
	"""
out << t
}
/**
 * Email tag
 */
def email={attrs,body->
def party
def partyId=attrs.id
if (partyId!=null){
party=Party.get(partyId)
}else{
party=new Party()
}
def controller=attrs.controller
def contactTypes=contactUtilService.getProfileEmailContactTypes(party)
def editAction=attrs.editAction
def createAction=attrs.createAction	
if (editAction==null || editAction==""){
editAction="edit"
}
if (createAction==null || createAction==""){
createAction="create"
}
if (controller==null || controller==""){
controller="contactPhone"
}
def t="""
<TABLE> 
 """
if (party!=null){
party.emailList.each{
def eml=it
t+="""
    <tr class="prop">
    <td valign="top" class="name"><label for="emailAddress">Email Address:</label></td>
    <td valign="top" class="value">${eml.email.emailAddress}</td>
  </tr>
  <tr class="prop">
    <td valign="top" class="name"><label for="contactType">Type of Email:</label></td>
    <td>${eml.contactType}</td>
  </tr>   
 <tr>
  <td colspan="2">
         ${g.link([controller:"contactEmail",
			      action:"${editAction}}",
				  id:"${eml.email.id}"],
"Edit Email")
}
  </td>
 </tr>
 <tr>
    <td colspan="2">
	  <div style="border-style:solid;border-width:2px;"></div>
    </td></tr>"""
}
}
t+="""
<tr><td>Add</td>
<td>
<div id="createTheContactEmail">"""
contactTypes.each{
ContactType ct=it
t+="""
${g.link([controller:"${controller}",action:"${createAction}",params:["contactType.id":"${ct.id}","party.id":"${party.id}"]],"${ct.name}Email")
}"""
}
t+="""
</div>
</td>
</tr>
</TABLE>
	"""
out << t
}
}	
