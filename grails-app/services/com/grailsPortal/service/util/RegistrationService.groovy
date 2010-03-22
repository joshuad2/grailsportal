package com.grailsPortal.service.util
import org.apache.shiro.SecurityUtils
import org.apache.shiro.subject.Subject
import org.apache.shiro.session.Session
import java.text.SimpleDateFormat
import com.grailsPortal.domain.*;
import com.grailsPortal.exception.ValidationException
class RegistrationService {
	private static final String elements=["email","phone","address"]
	private static final String INCOMPLETE="Incomplete"
	private static final String DATEFORMAT="yyyy-MM-dd"
    boolean transactional = true

    def RegistrationEventParty doRegistrationParty(
    		  regEvent,
    		  roleType,
    		  partyType,
	          firstName,
	          lastName,
	          birthDate,
	          emailParams,
	          addressParams,
	          phoneParams,
	          otherParams
	          ) throws ValidationException{
        def rps=rs.findRegistrationPartyByPartyTypeAndRole(regEvent,partyType,roleType)
        rps.each{
          doParty(partyType,
				   it.party,
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
    def Party doParty(partyType,
    		          theParty,
    		          firstName,
    		          lastName,
    		          birthDate,
    		          emailParams,
    		          addressParams,
    		          phoneParams,
    		          otherParams
    		          ) throws ValidationException{
	   if (theUser==null){
    	theParty=new Party()
	   }
	   theParty.firstName=firstName
	   theParty.lastName=lastName
	   theParty.birthDate=birthDate
	   theParty.partyType=partyType
       portalviewService.handleEmail(emailParams)
       portalviewService.handlePhone(phoneParams)
       portalviewService.handleAddress(addressParams)
       portalviewService.handleOtherParams(otherParams)
	   return theUser
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
    def OrderRecord doOrderRecord(securityService,
    		                      orderRecord, 
    		                      defaultOrderComment,
    		                      defaultGrossAmount,
    		                      defaultTaxAmount,
    		                      defaultTotalAmount,
    		                      defaultOrderDate,
    		                      defaultOrderStatus,
    		                      defaultOrderRecordTypeCD){
  	    orderRecord.orderComment=defaultOrderComment
  	    orderRecord.grossAmount=defaultGrossAmount
  	    orderRecord.taxAmount=defaultTaxAmount
  	    orderRecord.totalAmount=defaultTotalAmount
  	    orderRecord.orderDate=defaultOrderDate
  	    orderRecord.orderNumber=orderRecord.orderDate.getTime().toString()
  	    orderRecord.orderStatus=defaultOrderStatus
  	    orderRecord.orderRecordType=OrderRecordType.findByCd(defaultOrderRecordTypeCD)
  	    orderRecord.party=securityService.getRegisteredUser().party
  	    return validate(orderRecord)
      }

      
      def OrderRecordLineItem doOrderRecordLineItem(or,
    		                                        netSalesAmount,
    		                                        li,
    		                                        lineItemDate,
    		                                        amountPurchased
    		                                        ) throws ValidationException{
      	def orli=new OrderRecordLineItem()
      	orli.amountPurchased=amountPurchased
      	orli.lineItemAmount=pr.netSalesAmount
      	orli.lineItemDate=lineItemDate
      	orli.lineItemNumber=li
      	orli.orderRecord=or
      	orli.product=pr
      	return validate(orli)
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
	    		                          lastname,
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
    
      
      def RegistrationEvent handleInitialRegistrationEvent(id,birthDate){
    	  def RegistrationEvent regEvent=null
          if (id!=null){
          	  regEvent= RegistrationEvent.get(id)
          	}
          	if (regEvent==null){
          		  regEvent=new RegistrationEvent()
          	}
          if (regEvent.registrationFor==null){
            regEvent.registrationFor=new Party()
            regEvent.registrationFor.birthDate=birthDate
            }
          return regEvent
      }
     def handleOrderRecordLineItems(orderRecord,
    		                                    params)throws ValidationException{
 		int i=1
		boolean pickOne=false
		params.each{
	      if (params[it.key]== "on"){
		    pickOne=true
		    def pr=Product.findByCd(it.key)
		    def orli=doOrderRecordLineItem(orderRecord,pr,i++)
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

     def findRegistrationPartyByPartyTypeAndRole(regEvent,partyType,role){ 
       return RegistrationEventParty.findAll("from RegistrationEventParty as rep, Party as p "+
                                             " rep.party = p and"+
    		                                 " where rep.registrationEvent=? and"+
    		                                 " rep.role=? and"+
    		                                 " p.partyType=? ",regEvent,role,partyType)
     }
}
