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
import com.grailsPortal.domain.ContactEmail
import com.grailsPortal.domain.ContactAddress
import com.grailsPortal.domain.portalConfig.State
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
	 * DoFormListField
	 * @param label
	 * @param labelField
	 * @param fieldName
	 * @param field
	 * @param fieldLength
	 * @param listOfValues
	 * @return
	 */
	def doFormListField(label, labelField, fieldName, field, fieldLength, listOfValues,value="",addRow=true){
		def listHtml="<select name='${fieldName}'>"
		listOfValues.each {
			def ct=it
			listHtml+="<option value='${ct.key}'>${ct.value}</option>"
		}
		listHtml+="</select>"
		def doAddRowStart=""
		def doAddRowEnd=""
		  if (addRow){
			  doAddRowStart="<tr class=\"prop\">"
			  doAddRowEnd="</tr>"
		  }
		
		return """
		  ${doAddRowStart}
			<td valign="top" class="name">
			   <label for="${labelField}}">${label}</label>
			</td>
			<td valign="top">${listHtml}</td>
		  ${doAddRowEnd}
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
	def doFormInputField(label, labelField, fieldName, field, fieldLength, fieldValue="",haveErrors,addRow="true"){
		if (!fieldValue){
			fieldValue=""
		}
		def doAddRowStart=""
		def doAddRowEnd=""
		  if (addRow){
			  doAddRowStart="<tr class=\"prop\">"
			  doAddRowEnd="</tr>"
		  }
		return """
		  ${doAddRowStart}
			<td valign="top" class="name">
			   <label for="${labelField}">${label}</label>
			</td>
			<td valign="top" class="value ${haveErrors}">
			   <input type="text" maxlength="${fieldLength}" id="${field}" name="${field}" value="${fieldValue}"/>
			</td>
		  ${doAddRowEnd}
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
		def haveErrors=attrs?.haveErrors
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
   def doRemoteLinkValue(controller, editAction, linkId, linkLabel,update,addRow="true"){
	   def doAddRowStart=""
	   def doAddRowEnd=""
	   def numCols="2"
		 if (addRow){
			 doAddRowStart="<tr class=\"prop\">"
			 doAddRowEnd="</tr>"
			 numCols="1"
		 }
	   return """
	   ${doAddRowStart}
			  <td colspan="${numCols}">
				${g.remoteLink([controller:"${controller}",
						  action:"${editAction}",
						  id:"${linkId}",update:"${update}"],
						  {"${linkLabel}"})}
			   </td>
	   ${doAddRowEnd}"""
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
	def doLinkValue(controller, editAction, linkId, linkLabel,parameters,addRow="true"){
		def doAddRowStart=""
		def doAddRowEnd=""
		def numCols="1"
		  if (addRow){
			  doAddRowStart="<tr class=\"prop\">"
			  doAddRowEnd="</tr>"
			  numCols="2"
		  }
		return """
		${doAddRowStart}
			   <td colspan="${numCols}">
			     ${g.link([controller:"${controller}",action:"${editAction}",id:"${linkId}"],
		                   {"${linkLabel}"})}
				</td>
			 ${doAddRowEnd}
			 """
  }

  def doPasswordInput(fieldValue,fieldName,label, maxLength, haveErrors,addRow="true"){
  if (!fieldValue){
	  fieldValue=""
  }
  def doAddRowStart=""
  def doAddRowEnd=""
	if (addRow){
		doAddRowStart="<tr class=\"prop\">"
		doAddRowEnd="</tr>"
	}
  return """
	${doAddRowStart}
	  <td valign="top" class="name">
		 <label for="${fieldName}}">${label}</label>
	  </td>
	  <td valign="top" class="value ${haveErrors}">
	     ${g.passwordField(["name":"${fieldName}","value":"${fieldValue}","maxLength":"${maxLength}"])}
	  </td>
	${doAddRowEnd}
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
  def doDisplayValue(value,label,fieldName,addRow=true){
	  def doAddRowStart=""
	  def doAddRowEnd=""
		if (addRow){
			doAddRowStart="<tr class=\"prop\">"
			doAddRowEnd="</tr>"
        }
		def doLabel=""
		if (label!=""){
		  doLabel="""
			<td valign="top" class="name">
			<label for="${fieldName}">${label}</label>
		   </td>
		   """
		}
  return """
		${doAddRowStart}
         ${doLabel}
		 <td valign="top" class="value">${value}</td>
	   ${doAddRowEnd}
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
def doFormCheckboxField(label,labelField,fieldName,field,value,haveErrors,addRow=true){
  def checked=""
  if (value!=null && value!=""){
    checked="CHECKED"
  } 
  def doAddRowStart=""
  def doAddRowEnd=""
	if (addRow){
		doAddRowStart="<tr class=\"prop\">"
		doAddRowEnd="</tr>"
	}
  return """
		${doAddRowStart}
		  <td valign="top" class="name">
			 <label for="${labelField}}">${label}</label>
		  </td>
		  <td valign="top" class="value ${haveErrors}">
			 <input type="checkbox" id="${field}" name="${field}" ${checked}/>
		  </td>
		${doAddRowEnd}
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
 * Displays the address
 * @param address1
 * @param address2
 * @param city
 * @param state
 * @param zipcode
 * @param contactType
 * @param controller
 * @param editAction
 * @param addrId
 * @return
 */
def doDisplayAddress(address1,address2,city,state,zipcode,contactType,controller,editAction,addrId,deleteAction="deleteRemote"){
	 def addr1Text=this.doDisplayValue(address1,"Address 1","Address:")
	 def addr2Text=this.doDisplayValue(address2,"Address 2","Address 2:")
	 def cityText=this.doDisplayValue(city,"City","City:")
	 def stateText=this.doDisplayValue(state,"State","State:")
	 def zipCodeText=this.doDisplayValue(zipcode,"ZipCode","Zip Code:")
	 def contactTypeText=this.doDisplayValue(contactType,"contactType","Type of Address")
	 def lv=doRemoteLinkValue(controller,editAction,addrId,"Edit","inputAddress")
	 def dl=doRemoteLinkValue(controller,deleteAction,addrId,"Delete","addressSection")
	 return "<TABLE>${addr1Text}${addr2Text}${cityText}${stateText}${zipCodeText}${contactTypeText}${lv}${dl}</TABLE>"
}
/**
 * Display the email
 * @param emailAddress
 * @param contactType
 * @param controller
 * @param editAction
 * @param emlId
 * @return
 */
def doDisplayEmail(emailAddress,contactType,controller,editAction,emlId,deleteAction="deleteRemote"){
   def emailAddressDisplay=doDisplayValue(emailAddress,"Email Address:","emailAddress")
   def ct=doDisplayValue(contactType,"Contact Type:","contactType")
   def eml=ContactEmail.get(emlId)
   def party=eml.party
   def lv=doRemoteLinkValue(controller,editAction,emlId,"Edit","inputEmail")
   def dl=doRemoteLinkValue(controller,deleteAction,emlId,"Delete","emailSection")
   return "${emailAddressDisplay}${ct}${lv}${dl}"
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
	def areaCodeError=g.hasErrors([bean:value,field:"areaCode"],'errors')
	def phoneNumberError=g.hasErrors([bean:value,field:"phoneNumber"],'errors')
    def areaCodeInput=doFormInputField("Area Code","areaCode","areaCode","areaCode","4",value.areaCode,areaCodeError)
    def phoneNumberInput=doFormInputField("Phone Number","phoneNumber","phoneNumber","phoneNumber","20",value.phoneNumber,phoneNumberError)
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
 * Create the form for the creation and editing of addresses
 * @param cts
 * @param partyId
 * @param controller
 * @param createAction
 * @param value
 * @param inputLabel
 * @return
 */
  def doInputAddress(cts,partyId,controller,createAction,value,inputLabel){
	  def errorVal=""
	  if (value==null){
		  value=new ContactAddress()
	  }else{
		value?.validate()
		if (value.hasErrors()){
		   errorVal+= g.message(code:"ContactAddress.input.error",args:['',''])
		}
	  }
	  def states=[:]
	  State.list().each{
		  State s=it
		  states.put s.id, s.name
	  }
	  def address1Error=g.hasErrors([bean:value,field:"address1"],'errors')
	  def cityError=g.hasErrors([bean:value,field:"city"],'errors')
	  def zipCodeError=g.hasErrors([bean:value,field:"city"],'errors')
	  def address1Input=doFormInputField("Address:","address1","address1","address1","100",value?.address1,address1Error)
	  def address2Input=doFormInputField("Address 2:","address2","address2","address2","100",value?.address2)
	  def cityInput=doFormInputField("City:","city","city","city","80",value?.city,cityError)
	  def stateInput=doFormListField("State:","state","state","state","30",states)
	  def zipCodeInput=doFormInputField("ZipCode:","zipcode","zipcode","zipcode","10",value?.zipcode,zipCodeError)
	  def contactTypeInput=doFormListField("Contact Type:","ContactType","contactType","contactType","20",cts)
	  def activeInput=doFormCheckboxField("Active","active","active","active","${value.active}",g.hasErrors([bean:value,field:"active"]))
	  def url=["action":createAction,"controller":controller]
	  def startRow=" <tr><td>"
	  def endRow="</td></tr>"
	  def label="${startRow}${inputLabel}${endRow}"
	  def str=submitToRemote(["url":url,"update":"addressSection","value":"Submit Address"])
	  def dialog="""
				  <div class="dialog">
					 <table>
					   <tbody>
						 ${address1Input}${address2Input}${cityInput}${stateInput}${zipCodeInput}${contactTypeInput}${activeInput}
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
  * doInputPhone
  * @param cts - The list of Contact Types
  * @param partyId - The partyId
  * @param createAction - The action it should goto on Saving
  * @param value - The value which is an instance of the ContactPhone domain class
  * @return
  */
   def doInputEmail(cts,partyId,controller,createAction,value,inputLabel){
	 def errorVal=""
	 if (value==null){
		 value=new ContactEmail()
	 }else{
	   value?.validate()
	   if (value.hasErrors()){
		  errorVal+= g.message(code:"ContactEmail.input.error",args:['',''])
	   }
	 }
	 def emailAddressError=g.hasErrors([bean:value,field:"emailAddress"],'errors')
	 def emailAddressInput=doFormInputField("Email Address","emailAddress","emailAddress","emailAddress","100",value.emailAddress,emailAddressError)
	 def contactTypeInput=doFormListField("Contact Type:","ContactType","contactType","contactType","20",cts)
	 def activeInput=doFormCheckboxField("Active","active","active","active","${value.active}",g.hasErrors([bean:value,field:"active"]))
	 def url=["action":createAction,"controller":controller]
	 def startRow=" <tr><td>"
	 def endRow="</td></tr>"
	 def label="${startRow}${inputLabel}${endRow}"
	 def str=submitToRemote(["url":url,"update":"emailSection","value":"Submit Email"])
	 def dialog="""
				 <div class="dialog">
					<table>
					  <tbody>
						${emailAddressInput}${contactTypeInput}${activeInput}
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
 * do the address
 * @param party
 * @param controller
 * @param editAction
 * @param createAction
 * @param contactAddress
 * @param contactTypes
 * @return
 */
def doAddress(party,controller,editAction,createAction,contactAddress,contactTypes,isAjax=false){
		 if (editAction==null || editAction==""){
			 editAction="editAddress"
		   }
		   if (createAction==null || createAction==""){
			 createAction="create"
		   }
		   if (controller==null || controller==""){
			 controller="contactAddress"
		   }
		   def cts=[:]
		   contactTypes.each{
			   cts.put it.id, it.name
		   }
		   def t=""
		   if (!isAjax){
			   t="<TABLE id='addressSection' >"
		   }
		   t+="<tr><td><TABLE id='inputAddress'>"
		   t+=this.doInputAddress(cts, party.id, "contactAddress","updateOrCreateAddress", contactAddress,"Address:")
		   t+="</TABLE></TD>"
		   t+="<td><div style=\"width: 250px; height:200px; overflow:'auto'\"><TABLE id='phoneList' >"
		   party?.addressList.each{
				 ContactAddress addr=it
				 t+=this.doDisplayAddress(addr.address1,
										addr.address2,
										addr.city,
										addr.state,
										addr.zipcode,
										addr.contactType,
										controller,
										editAction,
										addr.id)
			   }
		   t+="</TABLE></div>"
		   t+="</td></tr>"
		 if (!isAjax){
			 t+="</TABLE>"
		 }
		 return  t
	 }
/**
 * address tag
 */
def address={attrs->
	   def partyId=attrs.id
	   def controller=attrs.controller
	   def party=Party.get(partyId)
	   def contactTypes=ContactType.list()	
	   def editAction=attrs.editAction
	   def createAction=attrs.createAction
	   def contactAddress=attrs?.contactAddress	
	 out << doAddress(party,controller,editAction,createAction,contactAddress,ContactType.list())
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
		   t+=this.doDisplayPhone(phn?.areaCode,
								  phn?.phoneNumber,
								  phn?.contactType,
								  controller,
								  editAction,
								  phn?.id)
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
* does the regular phone tag
* @param partyId
* @param controller
* @param editAction
* @param createAction
* @return
*/
def doEmail(party,controller,editAction,createAction,contactEmail,contactTypes,isAjax=false){
   if (editAction==null || editAction==""){
	   editAction="editEmail"
	 }
	 if (createAction==null || createAction==""){
	   createAction="create"
	 }
	 if (controller==null || controller==""){
	   controller="contactEmail"
	 }
	 def cts=[:]
	 contactTypes.each{
		 cts.put it.id, it.name
	 }
	 def t=""
	 if (!isAjax){
		 t="<TABLE id='emailSection' >"
	 }
	 t+="<tr><td><TABLE id='inputEmail'>"
	 t+=this.doInputEmail (cts, party.id, "contactEmail","updateOrCreateEmail", contactEmail,"Email Addresses:")
	 t+="</TABLE></TD>"
	 t+="<td><div style=\"width: 250px; height:200px; overflow:'auto'\"><TABLE id='emailList' >"
	 party?.emailList.each{
		   ContactEmail eml=it
		   t+=this.doDisplayEmail(eml?.emailAddress,
								  eml?.contactType,
								  controller,
								  editAction,
								  eml?.id)
		 }
	 t+="</TABLE></div>"
	 t+="</td></tr>"
   if (!isAjax){
	   t+="</TABLE>"
   }
   return  t
}
/**
 * Email tag
 */
def email={attrs->
 def partyId=attrs.id
 def controller=attrs.controller
 def party=Party.get(partyId)
 def contactTypes=ContactType.list()
 def editAction=attrs.editAction
 def createAction=attrs.createAction
 def contactEmail=attrs?.contactEmail
out << doEmail(party,controller,editAction,createAction,contactEmail,ContactType.list())
}
}