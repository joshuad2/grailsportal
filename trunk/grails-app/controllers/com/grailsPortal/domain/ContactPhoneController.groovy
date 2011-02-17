
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

package com.grailsPortal.domain

import com.grailsPortal.taglib.PortalTagLib

class ContactPhoneController {
/**
 *  ContactPhoneController
 *  @author Joshua Davis    
 */
	def scaffold = "true"
	def editPhone={
		def ptl = new PortalTagLib()
		def cp=ContactPhone.get(params.id)
		def inputLabel="Edit Phone:"
		def cts=[:]
		ContactType.list().each { cts.put it.id, it.name }
		def retVal= ptl.doInputPhone (cts, cp.party.id, "ContactPhone", "updateOrCreatePhone", cp, inputLabel)
	    render retVal
		}
	def updateOrCreatePhone={
		def activeParam=params.active
		def partyId=params.party
		def isCreate=false
		def isActive=false
		if (activeParam!=null){
			isActive=true
		}
		def retVal=""
		ContactPhone cp
		if (params.id==null){
		  cp=new ContactPhone()
		  isCreate=true
		}else{
		 cp=ContactPhone.get(params.id)
		}
		cp.active=isActive
		cp.areaCode=params.areaCode
		cp.contactType=ContactType.get(params.contactType)
		cp.internationalCode="1"
		cp.party=Party.get(partyId)
		cp.phoneNumber=params.phoneNumber
		cp.save(flush:true)
	    def controller="contactPhone"
		def action="editPhone"
		PortalTagLib ptl=new PortalTagLib()
		render ptl.doPhone (cp.party, controller, action, "updateOrCreatePhone", cp,ContactType.list(),true)
	}
	
	def deleteRemote={
		def contactPhoneId=params.id
		def cp=ContactPhone.get(contactPhoneId)
		def party=cp.party
	    cp.delete(flush:true)
		cp=null
		PortalTagLib ptl=new PortalTagLib()
		render ptl.doPhone (party, "ContactPhone", "editPhone", "updateOrCreatePhone", cp,ContactType.list(),true)
	}
}
