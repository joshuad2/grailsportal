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
import org.springframework.validation.FieldError;
import org.springframework.validation.BeanPropertyBindingResult
class PortalTagLib {

	def contactUtilService
	def portalviewService
	def static headingHtml
	static namespace = 'portal'
	
	def getPortalviewService(){
		if (portalviewService==null){
		  def ctx = servletContext.getAttribute(GrailsApplicationAttributes.APPLICATION_CONTEXT)
		  def pvs=ctx.getBean("portalviewService")
		  portalViewService=pvs
		  return pvs
		}else{
		  return portalviewService
		}
	}
	
	def getContactUtilService(){
		if (contactUtilService==null){
		  def ctx = servletContext.getAttribute(GrailsApplicationAttributes.APPLICATION_CONTEXT)
		  def cus=ctx.getBean("contactUtilService")
		  contactUtilService=cus
		  return cus
		}else{
		  return contactUtilService
		}
	}
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
			headingHtml=getPortalviewService().doHeader(attrs,body())
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
		def attributeComponents=portalViewService.getComponentGroupAttributesByViewAndGroup(attrs["viewName"], "address")
		def mode="edit"
		def attributeValues=getPortalviewService().getRegistrationValuesForComponent(attrs["viewname"],"address",attrs["eventId"])
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
	  * does the regular phone tag
	  * @param partyId
	  * @param controller
	  * @param editAction
	  * @param createAction
	  * @return
	  */
	 def doPhone(party,controller,editAction,createAction,contactPhone,contactTypes,isAjax=false){
		 if (editAction==null || editAction==""){
			 editAction="editPhone"
		   }
		   if (createAction==null || createAction==""){
			 createAction="create"
		   }
		   if (controller==null || controller==""){
			 controller="contactPhone"
		   }
		   def cts=[:]
		   contactTypes.each{
			   cts.put it.id, it.name
		   }
		   def t=""
		   if (!isAjax){
			   t="<TABLE id='phoneSection' >"
		   }
		   t+="<tr><td><TABLE id='inputPhone'>"
		   t+=this.doInputPhone (cts, party.id, "contactPhone","updateOrCreatePhone", contactPhone,"Phones:")
		   t+="</TABLE></TD>"
		   t+="<td><div style=\"width: 250px; height:200px; overflow:'auto'\"><TABLE id='phoneList' >"
		   party?.phoneList.each{
				 ContactPhone phn=it
				 t+=this.doDisplayPhone(phn.areaCode,
										phn.phoneNumber,
										phn.contactType,
										controller,
										editAction,
										phn.id)
			   }
		   t+="</TABLE></div>"
		   t+="</td></tr>"
		 if (!isAjax){
			 t+="</TABLE>"
		 }
		 return  t
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
	   def contactPhone=attrs?.contactPhone	
	 out << doPhone(party,controller,editAction,createAction,contactPhone,ContactType.list())
	 }
	/**
	 * DoFormListField
	 * @param label
	 * @param labelField
	 * @param fieldName
	 * @param field
	 * @param fieldLength
	 * @param listOfValues
	 * @return
	 */
	def doFormListField(label, labelField, fieldName, field, fieldLength, listOfValues){
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
		def field=attrs.field
		def fieldLength=attrs.fieldLength
		def listOfValues = JSON.parse(attrs.listOfValues);
		out<<doFormListField(label, labelField, fieldName, field, fieldLength, listOfValues)
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
	def doFormInputField(label, labelField, fieldName, field, fieldLength, fieldValue,haveErrors){
		if (!fieldValue){
			fieldValue=""
		}
		return """
		  <tr class="prop">
			<td valign="top" class="name">
			   <label for="${labelField}}">${label}</label>
			</td>
			<td valign="top" class="value ${haveErrors}">
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
		def field=attrs.field
		def fieldLength=attrs.fieldLength
		def fieldValue=attrs?.fieldValue
		def haveErrors=attrs.haveErrors
		if (!fieldValue){
			fieldValue=""
		}
		out<<doFormInputField(label, labelField, fieldName, field, fieldLength, fieldValue,haveErrors)
	}
	/**
	* doRemoteLinkValue
	* @param controller
	* @param action
	* @param linkId
	* @param linkLabel
	* @param editAction
	* @return
	*/
   def doRemoteLinkValue(controller, editAction, linkId, linkLabel,update){
	   return """
	   <tr>
			  <td colspan="2">
				${g.remoteLink([controller:"${controller}",
						  action:"${editAction}",
						  id:"${linkId}",update:"${update}"],
						  {"${linkLabel}"})}
			   </td>
	   </tr>"""
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
	def doLinkValue(controller, editAction, linkId, linkLabel,parameters){
		return """
		<tr>
			   <td colspan="2">
			     ${g.link([controller:"${controller}",action:"${editAction}",id:"${linkId}"],
		                   {"${linkLabel}"})}
				</td>
			 </tr>"""
  }

  def doPasswordInput(fieldValue,fieldName,label, maxLength, haveErrors){
  if (!fieldValue){
	  fieldValue=""
  }
  return """
	<tr class="prop">
	  <td valign="top" class="name">
		 <label for="${fieldName}}">${label}</label>
	  </td>
	  <td valign="top" class="value ${haveErrors}">
	     ${g.passwordField(["name":"${fieldName}","value":"${fieldValue}","maxLength":"${maxLength}"])}
	  </td>
	</tr>
"""
  }

  def passwordInput={attrs->
  def fieldValue=attrs.fieldValue
  def fieldName=attrs.fieldName
  def label=attrs.label
  def maxLength=attrs.maxLength
  def haveErrors=attrs.haveErrors
  out << doPasswordInput(fieldValue,fieldName,label,maxLength,haveErrors)	  
  }
/**
 * linkValue tag
 */
  def linkValue={attrs->
    def  controller=attrs.controller
    def action=attrs.action
    def linkId=attrs.linkId
    def linkLabel=attrs.linkLabel
	def parameters=attrs.parameters
     out << doLinkValue(controller, action, linkId, linkLabel,parameters)
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
def doFormCheckboxField(label,labelField,fieldName,field,value,haveErrors){
  def checked=""
  if (value!=null && value!=""){
    checked="CHECKED"
  } 
  return """
		<tr class="prop">
		  <td valign="top" class="name">
			 <label for="${labelField}}">${label}</label>
		  </td>
		  <td valign="top" class="value ${haveErrors}">
			 <input type="checkbox" id="${field}" name="${field}" ${checked}/>
		  </td>
		</tr>
  """
}

def formCheckboxField={attrs->
  def label=attrs.label
  def labelField=attrs.labelField
  def fieldName=attrs.fieldName
  def field=attrs.field
  def checked=attrs.checked
  def haveErrors=attrs.haveErrors
  out<< doFormCheckboxField(label,labelField,fieldName,checked,haveErrors)
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
def doDisplayPhone(areaCode,num,contactType,controller,editAction,phnId,deleteAction="deleteRemote"){
	def phoneNumber=doDisplayValue("(${areaCode})${num}","Phone Number:","phoneNumber")
	def ct=doDisplayValue(contactType,"Contact Type:","contactType")
    def phn=ContactPhone.get(phnId)
	def party=phn.party
	def lv=doRemoteLinkValue(controller,editAction,phnId,"Edit","inputPhone")
	def dl=doRemoteLinkValue(controller,deleteAction,phnId,"Delete","phoneSection")
	return "${phoneNumber}${ct}${lv}${dl}"
	}


def doHiddenPhone(contactPhoneId){
	def props=ContactPhone.getProperties()
	
}
def displayPhone={attrs->
	def areaCode=attrs.areaCode
	def num=attrs.num
	def contactType=attrs.contactTpye
	def controller=attrs.controller
	def editAction=attrs.editAction
	def phnId=attrs.phnId
	return this.doDisplayPhone (areaCode, num, contactType, controller, editAction, phnId)
}
/**
 * doInputPhone
 * @param cts - The list of Contact Types
 * @param partyId - The partyId
 * @param createAction - The action it should goto on Saving
 * @param value - The value which is an instance of the ContactPhone domain class
 * @return
 */
  def doInputPhone(cts,partyId,controller,createAction,value,inputLabel){
	def errorVal=""
	if (value==null){
		value=new ContactPhone()
	}else{
	  value?.validate()
	  if (value.hasErrors()){
         errorVal+= g.message(code:"ContactPhone.input.error",args:['',''])
	  }
	}
    def areaCodeInput=doFormInputField("Area Code","areaCode","areaCode","areaCode","4",value.areaCode,g.hasErrors([bean:value,field:"areaCode"]))
    def phoneNumberInput=doFormInputField("Phone Number","phoneNumber","phoneNumber","phoneNumber","20",value.phoneNumber,g.hasErrors([bean:value,field:"phoneNumber"]))
    def contactTypeInput=doFormListField("Contact Type:","ContactType","contactType","contactType","20",cts)
    def activeInput=doFormCheckboxField("Active","active","active","active","${value.active}",g.hasErrors([bean:value,field:"active"]))
    def url=["action":createAction,"controller":controller]
	def startRow=" <tr><td>"
	def endRow="</td></tr>"
	def label="${startRow}${inputLabel}${endRow}"
	def str=submitToRemote(["url":url,"update":"phoneSection","value":"Submit Phone"])
	def dialog="""		   
	            <div class="dialog">
				   <table>
					 <tbody>
					   ${areaCodeInput}${phoneNumberInput}${contactTypeInput}${activeInput}
					   <TR><TD><input type="hidden" name="party" value="${partyId}"/></TD></TR>
					   <TR><TD>${str}</TD></TR>
					 </tbody>
				   </table>
			   </div>
				   
			   """
	def attrs=["action":createAction,"controller":controller]
	def inputForm="${g.form(attrs,dialog)}"
	return errorVal+label+startRow+inputForm+endRow
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
	def cts=[:]
	contactTypes.each{
		cts.put it.id, it.name
	}
    def t="<TABLE>"
	party?.phoneList.each{
	    ContactPhone phn=it
		t+=doDisplayPhone(phn.areaCode,phn.phoneNumber,phn.contactType,controller,editAction,phn.id)
      }
    t+=doInputPhone(cts,partyId,"contactPhone","update",contactPhoneInstance,"Phones:")
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
  def contactTypes=getContactUtilService().getProfilePhoneContactTypes(party)
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
	  def lv=doLinkValue(controller,editAction,addr.id,"Edit Address","[id:'${addr.id}']")
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
  def addressContactTypes=getContactUtilService().getProfileAddressContactTypes(party)
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
	  def lv=doLinkValue(controller,editAction,eml.id,"Edit Email Address","[id:'${eml.id}]")
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