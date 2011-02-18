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
import com.grailsPortal.domain.ContactAddress
import com.grailsPortal.domain.portalConfig.State;
class ContactAddressController {
/**
 *  ContactAddressController
 *  @author Joshua Davis    
 */
	def scaffold = "true"
	def editAddress={
		def ptl = new PortalTagLib()
		def ca=ContactAddress.get(params.id)
		def inputLabel="Edit Address:"
		def cts=[:]
		ContactType.list().each { cts.put it.id, it.name }
		def retVal= ptl.doInputAddress (cts, ca?.party.id, "ContactAddress", "updateOrCreateAddress", ca, inputLabel)
		render retVal
		}
	def updateOrCreateAddress={
		def activeParam=params.active
		def partyId=params.party
		def isCreate=false
		def isActive=false
		if (activeParam!=null){
			isActive=true
		}
		def retVal=""
		ContactAddress ca
		if (params.id==null){
		  ca=new ContactAddress()
		  isCreate=true
		}else{
		 ca=ContactAddress.get(params.id)
		}
		ca.active=isActive
		ca.address1=params.address1
		ca.address2=params.address2
		ca.city=params.city
		def stateId=params.state
		def stateValue=State.get(stateId)
		ca.state=stateValue
		ca.zipcode=params.zipcode
		
		ca.contactType=ContactType.get(params.contactType)
		ca.party=Party.get(partyId)
		ca.save(flush:true)
		def controller="contactAddress"
		def action="editAddress"
		PortalTagLib ptl=new PortalTagLib()
		render ptl.doAddress (ca.party, controller, action, "updateOrCreateAddress", ca,ContactType.list(),true)
	}
	
	def deleteRemote={
		def contactAddressId=params.id
		def cp=ContactAddress.get(contactAddressId)
		def party=cp.party
		cp.delete(flush:true)
		cp=null
		PortalTagLib ptl=new PortalTagLib()
		render ptl.doAddress (party, "ContactAddress", "editAddress", "updateOrCreateAddress", cp,ContactType.list(),true)
	}
	
}
