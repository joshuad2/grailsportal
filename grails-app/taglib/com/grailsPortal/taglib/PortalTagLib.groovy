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
import grails.converters.JSON;
import org.codehaus.groovy.grails.web.json.*; 
import org.codehaus.groovy.grails.plugins.web.taglib.FormTagLib;


class PortalTagLib {

	def contactUtilService
	def portalViewService
	def static headingHtml
	static namespace = 'portal'

	/**
	 * doContactTypesInSelect do the contact types
	 * @param contactType
	 * @return
	 */
	private String doContactTypesInSelect(contactType){
		def t="""
		  <select name="${contactType}">   
		"""
		ContactType.list().each {
			ContactType ct=it
			t+="""	
		    <option value="${ct.id}">${ct.name}</option>
		  """
		}
		t+="</select>"
		return t
	}
	/**
	 * This is the heading tag it has one attribute "refresh" which can refresh
	 * the header since it is only retrieved once from the database.
	 */
	def heading={attrs, body->
		if (headingHtml==null || headingHtml=="" || attrs?.refresh=="Y") {
			//def ctx = servletContext.getAttribute(GrailsApplicationAttributes.APPLICATION_CONTEXT)
			//PortalviewService pc=ctx.getBean("portalviewService")
			headingHtml=portalViewService.doHeader(attrs,body())
			out<< headingHtml
		}
		else{
			out<< headingHtml
		}
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
//		def ctx = servletContext.getAttribute(GrailsApplicationAttributes.APPLICATION_CONTEXT)
//		def pc=ctx.getBean("PortalviewService")
		def attributeComponents=portalViewService.getComponentGroupAttributesByViewAndGroup(attrs["viewName"], "address")
		def mode="edit"
		def attributeValues=portalViewService.getRegistrationValuesForComponent(attrs["viewname"],"address",attrs["eventId"])
		out << portalViewService.renderComponentGroup(mode,attrs["viewName"],"address",attributeComponents, attributeValues)
	}
	/**
	 * Show the standard party
	 * @param fnameValue
	 * @param lNameValue
	 * @return
	 */
	def doShowParty(fnameValue,lnameValue){
		def firstName=doDisplayValue(fnameValue,"First Name:","firstName")
		def lastName=doDisplayValue(lnameValue,"Last Name","lastName")
		out <<firstName+lastName
	}
	/**
	 * tag for showing all of the party information which is last name and first name in a table form
	 */
	 def showParty={attrs->
	   def id=attrs.id
	   def instance
	   def fnameValue=""
	   def lnameValue=""
	   if (id!=null && id!=""){
	     instance=Party.get(id)
	     fnameValue=instance.firstName
	     lnameValue=instance.lastName
	     }
	   else{
	     fnameValue=""
		 lnameValue=""
	 }
	 out << doShowParty(fnameValue,lnameValue)
	 }
	 /**
	  * Phone tag
	  */
	 def phone={attrs->
	   def partyId=attrs.id
	   def controller=attrs.controller
	   def party=Party.get(partyId)
	   def contactTypes=ContactType.list()	
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
	   def t="<TABLE>" 
	   party?.phoneList.each{
			 ContactPhone phn=it
			 t+=doDisplayPhone(phn.areaCode,phn.phoneNumber,phn.contactType,controller,editAction,phn.id)
		   }
	 out << t+"</TABLE>"
	 }
	/**
	 * DoFormListField
	 * @param label
	 * @param labelField
	 * @param fieldName
	 * @param instanceName
	 * @param field
	 * @param fieldLength
	 * @param listOfValues
	 * @return
	 */
	def doFormListField(label, labelField, fieldName, instanceName, field, fieldLength, listOfValues){
		def listHtml="<select name='${fieldName}'>"
		listOfValues.each {
			def ct=it
			listHtml+="<option value='${ct.key}'>${ct.value}</option>"
		}
		listHtml+="</select>"
		return """
		  <tr class="prop">
			<td valign="top" class="name">
			   <label for="${labelField}}">${label}</label>
			</td>
			<td valign="top">${listHtml}</td>
		  </tr>
	"""
	}
	/**
	 * Form List Field
	 */
	def formListField={attrs->
		def label=attrs.label
		def labelField=attrs.labelField
		def fieldName=attrs.fieldName
		def instanceName=attrs.instanceName
		def field=attrs.field
		def fieldLength=attrs.fieldLength
		def listOfValues = JSON.parse(attrs.listOfValues);
		out<<doFormListField(label, labelField, fieldName, instanceName, field, fieldLength, listOfValues)
	}
/**
 * Do the form Input field
 * @param label
 * @param labelField
 * @param fieldName
 * @param instanceName
 * @param field
 * @param fieldLength
 * @param fieldValue
 * @return
 */
	def doFormInputField(label, labelField, fieldName,instanceName, field, fieldLength, fieldValue){
		if (!fieldValue){
			fieldValue=""
		}
		return """
		  <tr class="prop">
			<td valign="top" class="name">
			   <label for="${labelField}}">${label}</label>
			</td>
			<td valign="top">
			   <input type="text" maxlength="${fieldLength}" id="${field}" name="${field}" value="${fieldValue}"/>
			</td>
		  </tr>
	"""
	}
	/**
	 * do the form input field tag
	 */
	def formInputField={attrs->
		def label=attrs.label
		def labelField=attrs.labelField
		def fieldName=attrs.fieldName
		def instanceName=attrs.instanceName
		def field=attrs.field
		def fieldLength=attrs.fieldLength
		def fieldValue=attrs?.fieldValue
		if (!fieldValue){
			fieldValue=""
		}
		out<<doFormInputField(label, labelField, fieldName, instanceName, field, fieldLength, fieldValue)
	}
    /**
     * doLinkValue
     * @param controller
     * @param action
     * @param linkId
     * @param linkLabel
     * @param editAction
     * @return
     */
	def doLinkValue(controller, editAction, linkId, linkLabel){
		return """
		<tr>
			   <td colspan="2">
			     ${g.link([controller:"${controller}",
		                   action:"${editAction}",
		                   id:"${linkId}"],
		                   {"${linkLabel}"})}
				</td>
			 </tr>
			 <tr>
			   <td colspan="2">
				 <div style="border-style:solid;border-width:2px;"/>
			   </td>
			 </tr>"""
  }

/**
 * linkValue tag
 */
  def linkValue={attrs->
    def  controller=attrs.controller
    def action=attrs.action
    def linkId=attrs.linkId
    def linkLabel=attrs.linkLabel
     out << doLinkValue(controller, action, linkId, linkLabel)
   }
/**
 * doDisplayValue
 * @param value
 * @param label
 * @param fieldName
 * @return
 */
  def doDisplayValue(value,label,fieldName){
  return """
		<tr class="prop">
		<td valign="top" class="name">
		  <label for="${fieldName}">${label}</label>
		 </td>
		 <td valign="top" class="value">${value}</td>
	   </tr>
	   """
  }
/**
 * Do the display value
 */
def displayValue={attrs->
  def value=attrs.value
  def label=attrs.label
  def fieldName=attrs.fieldName
  out << doDisplayValue(value,label,fieldName)
  }
/**
 * do formCheckboxField
 * @param label
 * @param labelField
 * @param fieldName
 * @param instanceName
 * @param field
 * @param fieldLength
 * @return
 */
def doFormCheckboxField(label,labelField,fieldName,instanceName,field,value){
  def checked=""
  if (value!=null && value!=""){
    checked="CHECKED"
  } 
  return """
		<tr class="prop">
		  <td valign="top" class="name">
			 <label for="${labelField}}">${label}</label>
		  </td>
		  <td valign="top">
			 <input type="checkbox" id="${field}" name="${field}" ${checked}/>
		  </td>
		</tr>
  """
}

def formCheckboxField={attrs->
  def label=attrs.label
  def labelField=attrs.labelField
  def fieldName=attrs.fieldName
  def instanceName=attrs.instanceName
  def field=attrs.field
  def checked=attrs.checked
  out<< doFormCheckboxField(label,labelField,fieldName,instanceName,checked)
}
/**
 * doDisplayPhone
 * @param areaCode
 * @param num
 * @param contactType
 * @param controller
 * @param editAction
 * @param phnId
 * @return
 */
def doDisplayPhone(areaCode,num,contactType,controller,editAction,phnId){
	def phoneNumber=doDisplayValue("(${areaCode})${num}","Phone Number:","phoneNumber")
	def ct=doDisplayValue(contactType,"Contact Type:","contactType")
	def lv=doLinkValue(controller,editAction,phnId,"Edit Phone Number")
	return "${phoneNumber}${ct}${lv}"
	}
/**
 * doInputPhone
 * @param cts
 * @param partyId
 * @return
 */
  def doInputPhone(cts,partyId,createAction,value){
    def areaCodeInput=doFormInputField("Area Code","areaCode","areaCode","contactPhoneInstance","areaCode","4",value.areaCode)
    def phoneNumberInput=doFormInputField("Phone Number","phoneNumber","phoneNumber","contactPhoneInstance","phoneNumber","20",value.phoneNumber)
    def contactTypeInput=doFormListField("ContactType","contactType","contactPhoneInstance","contactType","20",cts,value.contactType)
    def activeInput=doFormCheckboxField("Active","active","active","contactPhoneInstance","active","")

    return """
     <tr><td>Phones</td></tr>
     <tr><td>
       <form method="post" action="${createAction}" target="_self" >
			   <div class="dialog">
				   <table>
					 <tbody>
					   ${areaCodeInput}${phoneNumberInput}${contactTypeInput}${activeInput}
					   <input type="hidden" name="party" value"${partyId}"/>
					 </tbody>
				   </table>
			   </div>
			   <div class="buttons">
				   <span class="button"><input class="save" type="submit" value="Save" /></span>
			   </div>
	</form>
   </td>
  </tr>
  """
}
/**
 * flowPhone tag
 */
  def flowPhone={attrs->
    def partyId=attrs.id
    def controller=attrs.controller
    def party=Party.get(partyId)
    def contactTypes=ContactType.list()
    def editAction=attrs.editAction
    def createAction=attrs.createAction
    def contactPhoneId=attrs.contactPhoneId
    def contactPhoneInstance
    if (contactPhoneId==null || contactPhoneId==""){
	 contactPhoneInstance=new ContactPhone()
	 contactPhoneInstance.party=party
    }else{
	  contactPhoneInstance=ContactPhone.get(contactPhoneId)
    }
    def ctId
    if (contactPhoneInstance==null || contactPhoneInstance.contactType==null){
	  ctId=1
    }else{
	  ctId=contactPhoneInstance.contactType.id
      }
    def t="<TABLE>"
	party?.phoneList.each{
	    ContactPhone phn=it
		t+=doDisplayPhone(phn.areaCode,phn.phoneNumber,phn.contactType,controller,editAction,phn.id)
      }
    t+=doInputPhone(contactTypes,partyId,createAction,contactPhoneInstance)
    t+="</TABLE>"
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
  def disp=doDisplayPhone(phn.areaCode,phn.phoneNumber,phn.contactType,controller,editAction,phn.id)
  out<<"<TABLE>${disp}</TABLE>"
  }
/**
 * doAddressDisplay
 * @param controller
 * @param editAction
 * @param createAction
 * @param party
 * @return
 */
def doAddressDisplay(controller,editAction,createAction,party){
	def t=""
	party?.addressList.each{
	  def addr=it
	  def addr1Text=this.doDisplayValue("Address 1","Address*:",addr.address1)
	  def addr2Text=this.doDisplayValye("Address 2","Address 2:",addr.address2)
	  def cityText=this.doDisplayValue("City","City:",addr.city)
	  def stateText=this.doDisplayValue("State","State:",addr.state)
	  def zipCodeText=this.doDisplayValue("ZipCode","Zip Code:",addr.zipCode)
	  def contactTypeText=this.doDisplayValue("contactType","Type of Address",addr.contactType)
	  def lv=doLinkValue(controller,editAction,addr.id,"Edit Address")
	  t+="<TABLE>${addr1Text}${addr2Text}${cityText}${stateText}${zipCodeText}${contactTypeText}${lv}</TABLE>"
	}
	return t
}
/**
 * address tag
 */
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
out << doAddressDisplay(controller,editAction,createAction,party)
}
/**
 * Do the email
 * @param party
 * @param controller
 * @param contactTypes
 * @param editAction
 * @param createAction
 * @return
 */
def doEmailDisplay(party,controller,contactTypes,editAction,createAction){
	def t="<TABLE>"
	party?.emailList.each{
	  def eml=it
	  def emailAddressText=this.doDisplayValue("Address 1","Address*:",eml.emailAddress)
	  def contactTypeText=this.doDisplayValue("contactType","Type of Email",eml.contactType)
	  def lv=doLinkValue(controller,editAction,eml.id,"Edit Email Address")
	  t+="${emailAddressText}${contactTypeText}${lv}"
	  }
   return t+"</TABLE>"
   
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
    def contactTypes=ContactType.list()
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
   out << doEmailDisplay(party,controller,contactTypes,editAction,createAction)+body()
   }
}