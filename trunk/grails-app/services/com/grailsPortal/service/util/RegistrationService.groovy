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
package com.grailsPortal.service.util
import org.apache.shiro.SecurityUtils
import org.apache.shiro.subject.Subject
import org.apache.shiro.session.Session
import java.text.SimpleDateFormat
import com.grailsPortal.domain.*;
import com.grailsPortal.exception.ValidationException
import com.grailsPortal.service.config.*;
class RegistrationService {
	static profiled=true
	private static final String elements=["email",
		                                  "phone",
										  "address"]
	private static final String INCOMPLETE="Incomplete"
	private static final String DATEFORMAT="yyyy-MM-dd"
	boolean transactional = true
	
	def RegistrationEventParty doRegistrationParty(
	PortalviewService portalviewService,
	RegistrationEvent regEvent,
	PartyRole role,
	PartyType partyType,
	String firstName,
	String lastName,
	Date birthDate,
	ArrayList emailParams,
	ArrayList addressParams,
	ArrayList phoneParams,
	ArrayList otherParams
	) throws ValidationException{
		def rps=findRegistrationPartyByPartyTypeAndRole(regEvent,partyType,role)
		rps.each{
			Party party=it.party
			doParty(portalviewService,
				    partyType,
					party,
					firstName,
					lastName,
					birthDate,
					emailParams,
					phoneParams,
					phoneParams,
					otherParams
					)
		}
	}
	/**
	 * DoParty does the basic things for creating a party
	 */
	def Party doParty(
	portalviewService,
	partyType,
    theParty,
	firstName,
	lastName,
	birthDate,
	emailParams,
	addressParams,
	phoneParams,
	otherParams
	) throws ValidationException{
		if (theParty == null){
			theParty=new Party()
		}
		theParty.firstName=firstName
		theParty.lastName=lastName
		theParty.birthDate=birthDate
		theParty.partyType=partyType
		return validate(theParty)
	}
	
	def validate={obj->
		if(!obj.validate()){
			throw new ValidationException(obj)
		}
		obj.save()
		return obj
	}
	
	/**
	 * This method handles the order records.
	 * @TODO parameterize and make options for all of the default Order record information
	 */
	def OrderRecord doOrderRecord(rec, 
	party,
	defaultOrderComment,
	defaultGrossAmount,
	defaultTaxAmount,
	defaultTotalAmount,
	defaultOrderDate,
	defaultOrderStatus,
	defaultOrderRecordTypeCD){
		
		OrderRecord orderRecord=rec
		orderRecord.orderComment=defaultOrderComment
		orderRecord.grossAmount=defaultGrossAmount
		orderRecord.taxAmount=defaultTaxAmount
		orderRecord.totalAmount=defaultTotalAmount
		orderRecord.orderDate=defaultOrderDate
		orderRecord.orderNumber=orderRecord.orderDate.getTime().toString()
		orderRecord.orderStatus=OrderStatus.findByCd(defaultOrderStatus)
		orderRecord.orderRecordType=OrderRecordType.findByCd(defaultOrderRecordTypeCD)
		orderRecord.party=party
		return validate(orderRecord)
	} 
	
	def RegistrationEventOrderRecord handleRegistrationEventOrderRecord(orderRecord, registrationEvent){
		RegistrationEventOrderRecord reor=new RegistrationEventOrderRecord()
		reor.order=orderRecord
		reor.event=registrationEvent
		reor.isActive=true
		reor=validate(reor)
		if (registrationEvent.orders==null){
			registrationEvent.orders=[reor]
		}else{
		   registrationEvent.orders.add(reor)
		}
		return reor
	}
	
	def getRegistrationEventOrderRecordByProduct(productId,registrationEventId){
		def p =Product.get(productId)
		def re=RegistrationEvent.get(registrationEventId)
		def query="from RegistrationEventOrderRecord as reor where "+
		          " 1<=(select count(*) from "+
		          " OrderRecord as orec,"+
				  " OrderRecordLineItem as orli "+
				  " where "+
				  " reor.order=orec and "+
				  " orli.orderRecord=orec and "+
				  " orli.product = ? and "+
				  " reor.event=?)"
		def reors=RegistrationEventOrderRecord.findAll(query,p,re)
		def theList=[]
		reors.each{
			RegistrationEventOrderRecord reor=it
			theList.add (reor)
		}
		return theList
	}
	def deleteOrders(theList){
		theList.each{
			RegistrationEventOrderRecord roer=it
			def orderRecord=roer.order
			def delList=[]
			orderRecord.lineItems.each{
			  delList.add it
			}
			orderRecord.lineItems=null
			delList.each{ 
			 OrderRecordLineItem orli=it
			  orli.orderRecord=null
			  
			  orli.product=null
			  orli.delete(flush:true)
			  }
			roer.delete(flush:true)
			orderRecord.delete(flush:true)
		  }
	}
	
	def handleDate(year,month,day){
		SimpleDateFormat format=new SimpleDateFormat(DATEFORMAT)
		return format.parse(year+"-"+month+"-"+day)
	}
	def doRegistrationEvent(regEvent,
	firstName,
	lastName,
	birthDate,
	emailParams,
	phoneParams,
	addressParams,
	otherParams,
	registrationDate,
	registrationForPartyType,
	securityService
	)throws ValidationException{
		if (regEvent==null){
			regEvent=new RegistrationEvent()
		}
		regEvent.registrationGrade=params.registrationGrade
		regEvent.registrationDate=registrationDate
		regEvent.registrationFor=validate(doParty(registrationForPartyType,
				regEvent.registrationFor,
				firstName,
				lastName,
				handleDate(otherParams.birthDate_year,
				otherParams.birthDate_month,
				otherParams.birthDate_day),
				emailParams,
				phoneParams,
				addressParams,
				otherParams))
		regEvent.registrationUser=securityService.getRegisteredUser()
		return validate(regEvent)
	}
	
	
	def RegistrationEvent handleInitialRegistrationEvent(id,birthDate,regUser){
		def RegistrationEvent regEvent=null
		if (id!=null){
			regEvent= RegistrationEvent.get(id)
		}
		if (regEvent==null){
			regEvent=new RegistrationEvent()
		}
		regEvent.registrationUser=regUser
		return validate(regEvent)
	}
	def OrderRecordLineItem doOrderRecordLineItem(or,
	product,sequence,amountPurchased)throws ValidationException{
		
		Product pr=product
		OrderRecordLineItem orli=new OrderRecordLineItem()
		orli.amountPurchased=amountPurchased
		orli.lineItemAmount=pr.netSalesAmount
		orli.lineItemDate=new Date()
		orli.lineItemNumber=sequence
		orli.orderRecord=or
		orli.product=product
		return validate(orli)
	}
	def handleSingleOrderRecordLineItem(OrderRecord orderRecord,productId) throws ValidationException{
		int i=1
		int numItems=1//temporary
		Product pr=Product.get(productId)
		OrderRecordLineItem orli=doOrderRecordLineItem(orderRecord,pr,1,numItems)
		orderRecord.totalAmount+=orli.lineItemAmount
		orderRecord.grossAmount+=orli.lineItemAmount
		orli.save()
		if (orderRecord.lineItems==null){
			orderRecord.lineItems=[orli]
		}else{
		  orderRecord.lineItems.add(orli)
		}
		orderRecord.save()
	}
	def handleOrderRecordLineItems(OrderRecord orderRecord,
	params)throws ValidationException{
		int i=1
		int numItems=1//temporary
		boolean pickOne=false
		params.each{
			if (params[it.key]== "on"){
				pickOne=true
				Product pr=Product.findByCd(it.key)
				OrderRecordLineItem orli=doOrderRecordLineItem(orderRecord,pr,i++,numItems)
				orderRecord.totalAmount+=orli.lineItemAmount
				orderRecord.grossAmount+=orli.lineItemAmount
			}
		}
		if (pickOne==false || !orderRecord.validate()){
			throw new ValidationException(orderRecord)
		}
		orderRecord.save()
	}
	def RegistrationEvent[] getRegistrationEventsForProduct(product){
		def events=RegistrationEvent.findAll("from RegistrationEvent as r where"+
				" 1<=(select count(*) from "+
				"OrderRecordLineItem as orli where "+
				"orli.orderRecord=r.orderRecord and "+
				"orli.product=?)",product)
		return events
	}
	
	def getRegistrationEventsAndLineItemsForProduct(product){
		def events=RegistrationEvent.findAll("from RegistrationEvent as r," +
				" RegistrationEventOrderRecord as reor," +
				" OrderRecord ordrRec, "+
				" OrderRecordLineItem orli"+
				" where r.order=reor and reor.order_record=ordrRec and "+
				" ordrRec=orli.orderRecord and " +
				" orli.product=? "+
				" and r.orderRecord.orderStatus in ('Ready',"+
				" 'Incomplete','Paid in Full',"+
				" 'Please Call','Waiting For Payment',"+
				" 'Waiting For Information')",product)
		
		if (events==null){
			events=new ArrayList()
		}
		return events
	}
	
	def deleteRegistrationEvent(RegistrationEvent regEvent){
		regEvent.orderRecord.orderStatus="DELETED"
		regEvent.orderRecord=null
		regEvent.save()
		regEvent.delete()
	}
	
	def findRegistrationPartyByPartyTypeAndRole(RegistrationEvent regEvent,PartyType partyType,PartyRole role){ 
		return RegistrationEventParty.findAll("from RegistrationEventParty as rep, Party as p "+
		" where "+
		" rep.party = p and"+
		" rep.registrationEvent=? and"+
		" rep.role=? and"+
		" p.partyType=? ",regEvent,role,partyType)
	}
}
