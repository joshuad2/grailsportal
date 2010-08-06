
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
package com.grailsPortal.controller
import com.grailsPortal.domain.portalConfig.State;

import org.apache.shiro.authc.AuthenticationException
import org.apache.shiro.authc.UsernamePasswordToken
import org.apache.shiro.SecurityUtils
import com.grailsPortal.exception.ValidationException
import com.grailsPortal.domain.*

class PortalContactController {
	def contactUtilService
	def index = { redirect(action: 'login',
		params: params) }
	
	def editContactEmail={		
		render (model:[contactEmailInstance:ContactEmail.get(params.id)],
		view:'editEmail')
	}
	def editContactPhone={		
		render (model:[contactPhoneInstance:ContactPhone.get(params.id)],
		view:'editPhone')	
	}
	def editContactAddress={		
		render (model:[contactAddressInstance:ContactAddress.get(params.id)],
		view:'editAddress')	
	}	
	def createContactWorkPhone = {
		def contactPhoneInstance = new ContactPhone()
		render(view:'createPhone',model:['contactPhoneInstance':contactPhoneInstance,'contactType':"Work",'partyId':params.id])
	}
	def createContactHomePhone = {
		def contactPhoneInstance = new ContactPhone()
		render(view:'createPhone',model:['contactPhoneInstance':contactPhoneInstance,'contactType':"Home",'partyId':params.id])
	}
	def createContactMobilePhone = {
		def contactPhoneInstance = new ContactPhone()
		render(view:'createPhone',model:['contactPhoneInstance':contactPhoneInstance,'contactType':"Mobile",'partyId':params.id])
	}
	def createContactHomeAddress={
		def contactAddressInstance= new ContactAddress()
		render(view:'createAddress',model:['contactAddressInstance':contactAddressInstance,'contactType':'Home','partyId':params.id])
	}
	def createContactWorkAddress={
		def contactAddressInstance= new ContactAddress()
		render(view:'createAddress',model:['contactAddressInstance':contactAddressInstance,'contactType':'Work','partyId':params.id])
	}
	def createContactMobileAddress={
		def contactAddressInstance= new ContactAddress()
		render(view:'createAddress',model:['contactAddressInstance':contactAddressInstance,'contactType':'Mobile','partyId':params.id])
	}
	def createContactHomeEmail={
		def contactEmailInstance= new ContactEmail()
		render(view:'createEmail',model:['contactEmailInstance':contactEmailInstance,'contactType':'Home','partyId':params.id])
	}
	def createContactWorkEmail={
		def contactEmailInstance= new ContactEmail()
		render(view:'createEmail',model:['contactEmailInstance':contactEmailInstance,'contactType':'Work','partyId':params.id])
	}
	def createContactMobileEmail={
		def contactEmailInstance= new ContactEmail()
		render(view:'createEmail',model:['contactEmailInstance':contactEmailInstance,'contactType':'Mobile','partyId':params.id])
	}
	def deleteContactPhone={
		def cpi=ContactPhone.get(params.id)
		ContactPhone.delete(cpi)
		redirect(controller:session.returnController,action:"edit")
	}
	def saveContactPhone = {
		ContactPhone contactPhoneInstance=null
		Boolean isCreate=false
		if (params.id == null){
			contactPhoneInstance = new ContactPhone()
			isCreate=true
		}else{
			contactPhoneInstance=ContactPhone.get(params.id)
		}
		contactPhoneInstance.areaCode=params.areaCode
		contactPhoneInstance.phoneNumber=params.phoneNumber
		if(!contactPhoneInstance.hasErrors() && contactPhoneInstance.save()) {
			if (!isCreate){
				flash.message="Updated the Phone"
			}
			else{
				flash.message = "Created the Phone Contact"
				def party=Party.get(params.partyId)
				def ct=ContactType.findByName(params.contactType)
				def pcp=new PartyContactPhone()
				pcp.party=party;
				pcp.active=true
				pcp.phone=contactPhoneInstance
				pcp.contactType=ct
				pcp.save(flush:true)
			}
		}
		else{
			flash.message = "Phone ${contactPhoneInstance.id} error"
		}		
		redirect(controller:session.returnController,action:'edit')
	}
	def deleteContactEmail={
		def ce=ContactEmail.get(params.id)
		ContactEmail.delete(ce)
		redirect(controller:session.returnController,action:"edit")
	}
	def saveContactEmail = {
		ContactEmail contactEmailInstance=null
		Boolean isCreate=false
		if (params.id==null){
			contactEmailInstance = new ContactEmail()
			isCreate=true
		}else{
			contactEmailInstance=ContactEmail.get(params.id)	
		}
		contactEmailInstance.emailAddress=params.emailAddress
		if(!contactEmailInstance.hasErrors() && contactEmailInstance.save()) {
			if (!isCreate){
				flash.message="Updated the Address"
			}else{
				def party=Party.get(params.partyId)
				def ct=ContactType.findByName(params.contactType)
				def pce=new PartyContactEmail()
				pce.party=party;
				pce.active=true
				pce.email=contactEmailInstance
				pce.contactType=ct
				pce.save(flush:true)
				flash.message = "Created the Email Address"
			}
		}
		else {
			flash.message = "Email ${contactEmailInstance.id} error"
		}
		
		redirect(controller:session.returnController,action:'edit')
	}
	
	def deleteAddress= {
		def addr=ContactAddress.get(params.id)
		ContactAddress.delete(addr)
        redirect(controller:session.returnController,action:'edit')
	}
	def saveContactAddress = {
		ContactAddress contactAddressInstance=null
		Boolean isCreate=false
		if (params.id==null){
			contactAddressInstance = new ContactAddress()
			isCreate=true
		}else{
			contactAddressInstance=ContactAddress.get(params.id)	
		}
		contactAddressInstance.address1=params.address1
		contactAddressInstance.address2=params.address2
		contactAddressInstance.address3=params.address3
		contactAddressInstance.city=params.city
		contactAddressInstance.state=State.get(params.state.id)
		contactAddressInstance.zipcode=params.zipcode
		if(!contactAddressInstance.hasErrors() && contactAddressInstance.save()) {
			if (!isCreate){
				flash.message="Updated the Address"
				redirect(controller:session.returnController,action:'edit')
				return
			}else{
				flash.message = "Created the Address"
				def party=Party.get(params.partyId)
				def ct=ContactType.findByName(params.contactType)
				def pcp=new PartyContactAddress()
				pcp.party=party;
				pcp.active=true
				pcp.address=contactAddressInstance
				pcp.contactType=ct
				pcp.save(flush:true)
				pcp.validate()
				if (pcp.hasErrors()){
					flash.message="Could not create the Address"
					log.error(pcp.errors.allErrors)
				}	
			}
		}
		else {
			flash.message = "Address ${contactAddressInstance.id} error"
		}
		
		redirect(controller:session.returnController,action:"edit")
	}
}
