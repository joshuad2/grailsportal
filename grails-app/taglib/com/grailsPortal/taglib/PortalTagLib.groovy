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
	 * tag for displaying the phone on the page using the attributes defined.
	 */
	def phone={attrs->
		def partyId=attrs.id
		def party=Party.get(partyId)
		
		def contactTypes=contactUtilService.getProfilePhoneContactTypes(party)
		def addressContactTypes=contactUtilService.getProfileAddressContactTypes(party)
		def emailContactTypes=contactUtilService.getProfileEmailContactTypes(party)
		
		def t="""
	          <TABLE> 
	           """
		if (party!=null){
			party.phoneList.each{
				def phn=it
				t+="""
    <tr class="prop">
      <td valign="top" class="name"><label for="phoneNumber">Phone Number:</label></td>
      <td valign="top" class="value">(${phn.phone.areaCode})${phn.phone.phoneNumber}</td>
    </tr>
    <tr class="prop">
      <td valign="top" class="name"><label for="contactType">Type of Phone Number:</label></td>
      <td>${phn.contactType}</td>"""
				t+="""
  </tr>   
  <tr><td colspan="2">
  <div id="editTheContactPhone${phn.contactType}">"""
  t+="""
	${g.link([controller:"partyContactPhone" ,
	  action:"edit",
	  id:"${phn.phone.id}"],
	  "Edit Phone Number")
	  }
	</div>
  </td></tr>
  <tr><td colspan="2"><div style="border-style:solid;border-width:2px;"></div></td></tr>"""
		}
	}
	t+="""
	  <tr><td>Add</td>
  <td>
  <div id="createTheContactPhone">"""
	contactTypes.each{
		def ct=it
		t+="""
      ${g.link([controller:"partyContactPhone",action:"create",id:"${partyId}"],"${ct.name}Phone")
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



def address={attrs->
def partyId=attrs.id
def party=Party.get(partyId)
if (party==null){
	return
}
def addressContactTypes=contactUtilService.getProfileAddressContactTypes(party)
def t="""
    <TABLE> 
     """
party.addressList.each{
	def addr=it
	t+="""
    <tr class="prop">
       <td valign="top" class="name"><label for="Address1">Address*:</label></td>
       <td valign="top" class="value">${addr.address.address1}</td>
   </tr> 
   <tr class="prop">
       <td valign="top" class="name"><label for="Address Line 2">Address 2:</label></td>
       <td>${addr.address.address2}</td>
   </tr>
   <tr class="prop">
       <td valign="top" class="name"><label for="City">City:</label></td>
         <td valign="top" class="value">${addr.address.city}</td>
   </tr>
   <tr class="prop">
       <td valign="top" class="name"><label for="state">State:</label></td>
         <td valign="top" class="value">${addr.address.state}</td>
   </tr>           
   <tr class="prop">
       <td valign="top" class="name"><label for="Zipcode">Zip Code:</label></td>
       <td>${addr.address.zipcode}</td>
   </tr>
   <tr class="prop">
       <td valign="top" class="name"><label for="contactType">Type of Address:</label></td>
       <td>${addr.contactType}</td>
   </tr>
   <tr>
	 <td colspan="2">
		<div id="editTheContactAddress${addr.contactType}">
		  ${g.link([controller:"partyContactAddress",action:"edit",id:"${addr.address.id}"],"Edit Address")
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
t+="""
	  <tr><td>Add</td>
<td>
<div id="createTheContactAddress">"""
addressContactTypes.each{
def ct=it
t+="""
    ${g.link([controller:"partyContactAddress",action:"createContact${ct.name}Address",id:"${partyId}"],"${ct.name} Address")
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
def partyId=attrs.id
def party=Party.get(partyId)
if (party==null){
return
}
def contactTypes=contactUtilService.getProfileEmailContactTypes(party)
def t="""
<TABLE> 
 """
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
    <div id="editTheContactEmail${eml.contactType}">
         ${g.link([controller:"partyContactEmail",
			      action:"edit",
				  id:"${eml.email.id}"],
			      "Edit Email")
          }
    </div>
  </td>
</tr>
<tr>
  <td colspan="2">
	<div style="border-style:solid;border-width:2px;"></div>
  </td></tr>"""
}
t+="""
<tr><td>Add</td>
<td>
<div id="createTheContactEmail">"""
contactTypes.each{
def ct=it
t+="""
${g.link([controller:"partyContactEmail",action:"create",id:"${partyId}"],"${ct.name}Email")
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
