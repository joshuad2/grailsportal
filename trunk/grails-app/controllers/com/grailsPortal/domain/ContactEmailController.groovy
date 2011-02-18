

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
class ContactEmailController {
/**
 *  ContactEmailController
 *  @author Joshua Davis    
 */
	def scaffold = "true"
	def editEmail={
		def ptl = new PortalTagLib()
		def cp=ContactEmail.get(params.id)
		def inputLabel="Edit Email:"
		def cts=[:]
		ContactType.list().each { cts.put it.id, it.name }
		def retVal= ptl.doInputEmail (cts, cp.party.id, "ContactEmail", "updateOrCreateEmail", cp, inputLabel)
		render retVal
		}
	def updateOrCreateEmail={
		def activeParam=params.active
		def partyId=params.party
		def isCreate=false
		def isActive=false
		if (activeParam!=null){
			isActive=true
		}
		def retVal=""
		ContactEmail cp
		if (params.id==null){
		  cp=new ContactEmail()
		  isCreate=true
		}else{
		 cp=ContactEmail.get(params.id)
		}
		cp.active=isActive
		cp.contactType=ContactType.get(params.contactType)
		cp.party=Party.get(partyId)
		cp.emailAddress=params.emailAddress
		cp.save(flush:true)
		def controller="contactEmail"
		def action="editEmail"
		PortalTagLib ptl=new PortalTagLib()
		render ptl.doEmail (cp.party, controller, action, "updateOrCreateEmail", cp,ContactType.list(),true)
	}
	
	def deleteRemote={
		def contactEmailId=params.id
		def cp=ContactEmail.get(contactEmailId)
		def party=cp.party
		cp.delete(flush:true)
		cp=null
		PortalTagLib ptl=new PortalTagLib()
		render ptl.doEmail (party, "ContactEmail", "editEmail", "updateOrCreateEmail", cp,ContactType.list(),true)
	}
}
