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
	${g.remoteLink([controller:"portalContact" ,action:"editContactPhone",id:"${phn.phone.id}",update:"editContactPhone${phn.contactType}"],"Edit Phone Number")
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
      ${g.remoteLink([controller:"portalContact",action:"createContact${ct.name}Phone",id:"${partyId}",update:"createContactPhone"],"${ct.name}Phone")
	}"""
}
t+="""
    </div>
    </td>
    </tr>
   </TABLE>
	${gui:dialog([title:"Add a Phone Number", 
              modal:"true",
	          form:"false",
              triggers:[show:[id:'createTheContactPhone', on:'click']], 
              fixedCenter:"true"],
	          "<div class=\"dialog\" id=\"createContactPhone\" style=\"width:600px;height:400px;overflow:scroll\"></div>")
	}
 ${portal.dialog([title:"Edit a Phone Number",
 	             contact:"Phone",
 	             contactTypes:"true",
 	             dialogId:"editContactPhone",
                 modal:"true",
 	             form:"false",
                 fixedCenter:"true"])}
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
		  ${g.remoteLink([controller:"portalContact",action:"editContactAddress",id:"${addr.address.id}",update:"editContactAddress${addr.contactType}"],"Edit Address")
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
    ${g.remoteLink([controller:"portalContact",action:"createContact${ct.name}Address",id:"${partyId}",update:"createContactAddress"],"${ct.name} Address")
}"""
}
t+="""
</div>
</td>
</tr>
</TABLE>
${gui:dialog([title:"Add an Address", 
              modal:"true",
	          form:"false",
              triggers:[show:[id:'createTheContactAddress', on:'click']], 
              fixedCenter:"true"],
	          "<div class=\"dialog\" id=\"createContactAddress\" style=\"width:600px;height:400px;overflow:scroll\"></div>")
	}
${portal.dialog([title:"Edit an Address",
	             contact:"Address",
	             contactTypes:"true",
	             dialogId:"editContactAddress",
                 modal:"true",
	             form:"false",
                 fixedCenter:"true"])}
	"""
out << t
}
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
         ${g.remoteLink([controller:"portalContact",action:"editContactEmail",id:"${eml.email.id}",update:"editContactEmail${eml.contactType}"],"Edit Email")
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
${g.remoteLink([controller:"portalContact",action:"createContact${ct.name}Email",id:"${partyId}",update:"createContactEmail"],"${ct.name}Email")
}"""
}
t+="""
</div>
</td>
</tr>
</TABLE>
${gui:dialog([title:"Add an Email Address", 
              modal:"true",
	          form:"false",
              triggers:[show:[id:'createTheContactEmail', on:'click']], 
              fixedCenter:"true"],
	          "<div class=\"dialog\" id=\"createContactEmail\" style=\"width:600px;height:400px;overflow:scroll\"></div>")
	}
${portal.dialog([title:"Edit an Email Address",
	             contact:"Email",
	             contactTypes:"true",
	             dialogId:"editContactEmail",
                 modal:"true",
	             form:"false",
                 fixedCenter:"true"])}
	"""
out << t
}

def dialog={attrs,body->
def cts= ContactType.list()
if (attrs.triggers=="" || attrs.triggers==null){
cts.each{
def name=it.name
def triggers=[show:[id:"editTheContact"+attrs.contact+name, on:'click']]
if (attrs.contactTypes=="true"){
attrs.triggers=triggers
}
if (attrs.dialogId!=null){
body={"<div class=\"dialog\" id=\""+attrs.dialogId+name+"\" style=\"width:600px;height:400px;overflow:scroll\"></div>"
}
}
out<<gui.dialog(attrs,body)
}
}
else {
out<<gui.dialog(attrs,body)
}
}
}	
