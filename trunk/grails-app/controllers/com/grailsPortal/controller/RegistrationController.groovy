
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


import org.apache.shiro.SecurityUtils
import org.apache.shiro.subject.Subject
import org.apache.shiro.session.Session
import org.codehaus.groovy.grails.web.servlet.DefaultGrailsApplicationAttributes;
import org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes;
import java.text.SimpleDateFormat
import com.grailsPortal.service.util.*;
import com.grailsPortal.exception.ValidationException
import com.grailsPortal.service.util.*;
import com.grailsPortal.domain.*
import com.grailsPortal.service.config.*;

class RegistrationController {
  private static final String GOTOEMERGENCYCONTACTINFO      ="gotoEmergencyContactInfo"
  private static final String GOTOEMERGENCYCONTACTINFONOSAVE="gotoEmergencyContactInfoNoSave"
  private static final String ENTEREMERGENCYCONTACTINFO     ="enterEmergencyContactInfo"
  private static final String GOTOCANCEL                    ="gotoCancel"
  private static final String CANCELREGISTRATIION           ="cancelRegistration"
  private static final String GOTOPARENTINFO                ="gotoParentInfo"
  private static final String ENTERPARENTINFO               ="enterParentInfo"
  private static final String GOTOPARENTINFO2               ="gotoParentInfo2"
  private static final String ENTERREGISTRANTINFO           ="enterRegistrantInfo"
  private static final String GOTOREGISTRANTINFO             ="gotoRegistrantInfo"
  private static final String SECONDARYPARENTINFO           ="secondaryParentInfo"
  private static final String GOTOFINISH                    ="gotoFinish"
  private static final String FINISH                        ="finish"
  private static final String GOTOMEDICALINFO               ="gotoMedicalInfo"
  private static final String ENTERMEDICALINFO              ="enterMedicalInfo"
  private static final String DATEFORMAT                    ="yyyy-MM-dd"
  private static final String FIRSTNAME                     ="firstName"
  private static final String LASTNAME                      ="lastName"
  private static final String ADDRESS1                      ="address1"
  private static final String ADDRESS2                      ="address2"
  private static final String CITY                          ="city"
  private static final String STATE                         ="state"
  private static final String ZIPCODE                       ="zipcode"
  private static final String HOME                          ="Home"
  private static final String MOBILE                        ="Mobile"
  private static final String WORK                          ="Work"
  private static final String REGISTRATION                  ="registration"
  private static final String REGISTRATIONFINISH            ="registrationFinish"
  private static final String CANCEL                        ="cancel"
  private static final String CANCEL_MESSAGE                ="Cancelled"
  private static final String PORTALCONFIGURATIONSERVICE    ="portalConfigurationService"
  private static final String REGISTRATIONSERVICE           ="registrationService"
  private static final String SECURITYSERVICE               ="securityService"
  private static final String PORTALVIEWSERVICE             ="portalviewService"
  private static final String PARENT                        ="Parent"
 def index = {
	session.prods= Product.list()
    redirect(action:'register')
    }

 def showFinish={
	session.regEventId=params.id
	redirect(controller:REGISTRATION, action:REGISTRATIONFINISH)
    }
 def cancel={
	request.regEvent=null	session.regEventId=null	session.regEvent=null
}
 def list = {
    if(!params.max) params.max = 10
    def lst=RegistrationEvent.findAllByRegistrationUser(securityService.getRegisteredUser())
    [ registrationEventInstanceList: lst ]
   }
 def registrationFinish={
	session.regEvent=RegistrationEvent.get(session.regEventId)
	request.regEvent=session.regEvent
    }
 def edit={
    session.regEventId=params.regEventId
    }
 def createAddress={
	 def contactAddressInstance = new ContactAddress()
	 contactAddressInstance.properties = params
	 render(view:'createAddress',model:['contactAddressInstance':contactAddressInstance])
 }
 def newRegistration={
	        session.prods= Product.list()
    		request.regEventId=null
    		session.regEventId=null
    		redirect(action:'register')
    }
  /***
   * This is the start of the registration flow
   */

def doContacts={String partyTypeCd,
	            String partyRoleCd,
				String firstName,
				String lastName,
				RegistrationEvent regEvent->
        def ctx = servletContext.getAttribute(GrailsApplicationAttributes.APPLICATION_CONTEXT)
        RegistrationService rs=    ctx.getBean(REGISTRATIONSERVICE)
		PortalviewService pvs = ctx.getBean(PORTALVIEWSERVICE)
        return rs.doRegistrationParty(
			     pvs,
			     regEvent,
      		     PartyRole.findByCd(partyRoleCd),
      		     PartyType.findByCd(partyTypeCd),
                 firstName,lastName,
				 new Date(),
				 new ArrayList(),new ArrayList(),new ArrayList(),new ArrayList())
  }
def registerFlow = {
      selectProduct{// this by default is the first step
    	on(GOTOREGISTRANTINFO){
          try{
        	def ctx = servletContext.getAttribute(GrailsApplicationAttributes.APPLICATION_CONTEXT)
        	RegistrationService rs=ctx.getBean(REGISTRATIONSERVICE)
			SecurityService sc=ctx.getBean(SECURITYSERVICE)
            RegistrationEvent spRegEvent=rs.handleInitialRegistrationEvent(session.regEventId, new java.util.Date(),sc.getRegisteredUser())// this has to run with the first step
    		OrderRecord orderRecord=rs.doOrderRecord(new OrderRecord(),
													 sc.getRegisteredUser().party,
    				                                 "online Order",
													  0.0,0.0,0.0,
													   new java.util.Date(),
													   "IA",
    				                                   "Registration")
            rs.handleOrderRecordLineItems(orderRecord,params)
		    RegistrationEventOrderRecord roer=rs.handleRegistrationEventOrderRecord(orderRecord,spRegEvent)
			if (spRegEvent.orders==null){
				spRegEvent.orders=new ArrayList()
			}
			spRegEvent.orders.add(roer)
			if (spRegEvent.registrationFor==null){
				spRegEvent.registrationFor=new Party()
			}
			flow.regEvent=spRegEvent
    		}catch(ValidationException e){
        		  flash.message="Please select the product"
        		  flow.registerInstance=e.getExceptionVal()
    			  return error()
    		      }
    	   request.regEvent=flow.regEvent
    	   return success()
    	  }.to ENTERREGISTRANTINFO
    	  on(GOTOCANCEL){
    	    flash.message=CANCEL_MESSAGE
    	  }.to CANCEL
      }
      enterRegistrantInfo{
    	 on(GOTOPARENTINFO){
    	try{	 
	       def ctx = servletContext.getAttribute(GrailsApplicationAttributes.APPLICATION_CONTEXT)
		   RegistrationService rs=    ctx.getBean(REGISTRATIONSERVICE)
		   PortalviewService pvs=     ctx.getBean(PORTALVIEWSERVICE)
		   RegistrationEvent event=flow.regEvent
		   PartyType partyType=PartyType.findByName("Child")
           flow.regEvent.registrationFor=rs.doParty(
			                                       pvs,
			                                       partyType,
												   event.registrationFor,
												   params[FIRSTNAME],
												   params[LASTNAME],
												   new Date(),new ArrayList(),new ArrayList(),new ArrayList(),new ArrayList())
		   if (flow.parent==null){
		     Party parent=new Party();
		     parent.partyType=PartyType.findByName("Parent")
		     flow.parent=parent
		   }
		   if (flow.parent2==null){
			 Party parent2=new Party()
			 parent2.partyType=PartyType.findByName("Parent")
			 flow.parent2=parent2
		   }
          return success()
 		}catch(ValidationException e){
			flash.message="Please re-enter the information"
   		    flow.errorInstance=e.exceptionVal
		    return error()
		}
    	    }.to ENTERPARENTINFO
       on(GOTOCANCEL){
            session.regEventId=null;
    		flash.message=CANCEL_MESSAGE 
    	 }.to CANCEL
      }      
      enterParentInfo{
     	 on("saveParentInfo"){
     		try{
			def ctx = servletContext.getAttribute(GrailsApplicationAttributes.APPLICATION_CONTEXT)
			RegistrationService rs=    ctx.getBean(REGISTRATIONSERVICE)
	        PortalviewService pvs=     ctx.getBean(PORTALVIEWSERVICE)
			PartyType partyType=PartyType.findByName(PARENT)
		    def parent=flow.parent
		    def regEvent=flow.regEvent
            parent=rs.doParty(
			                  pvs,partyType,parent,params[FIRSTNAME], params[LASTNAME],
			                  new Date(),new ArrayList(),new ArrayList(),new ArrayList(),new ArrayList())
			flow.parent=parent
			request.parent=parent
			if (flow.parent2==null){
		     Party parent2=new Party();
		     parent2.partyType=PartyType.findByName(PARENT)
		     flow.parent2=parent
		   }
     		return success()
     		}catch(ValidationException e){
     			flash.message="Please re-enter the information"
         		flow.parentInstance=e.exceptionVal
     		    return error()
     		}
     	 }.to ENTERPARENTINFO
	     on(GOTOPARENTINFO2).to SECONDARYPARENTINFO    	 
         on(GOTOCANCEL){
     		 flash.message=CANCEL_MESSAGE
             session.regEventId=null;
     	 }.to CANCEL
      }
      secondaryParentInfo{
        	 on(GOTOEMERGENCYCONTACTINFO){
        		 try{
        	          flow.regEvent.registrationFor=doContacts("Parent","Secondary Parent",params,flow,regEvent)
            		  return success()
        		 }catch(ValidationException e){
        		  flow.parentInstance=e.exceptionVal
         		  flash.message="Please re-enter the information"
        		  return error()
        		 }
        	 }.to ENTEREMERGENCYCONTACTINFO
        	 on (GOTOEMERGENCYCONTACTINFONOSAVE){
        	 }.to ENTEREMERGENCYCONTACTINFO
             on(GOTOCANCEL){
                 session.regEventId=null;
        	 }.to CANCEL
      }
      enterEmergencyContactInfo{
      	 on(GOTOMEDICALINFO){
    		 try{
    	          flow.regEvent.registrationFor=doContacts("Emergency","Contact",params,flow,regEvent)
        		  return success()
      		 }catch(ValidationException e){
      			 flow.errorInstance=e.exceptionVal
     			 flash.message="Please re-enter your information"
      			 return error()
      		 }
      	 }.to ENTERMEDICALINFO
         on(GOTOCANCEL){
      		 flash.message=CANCEL_MESSAGE
             session.regEventId=null;
      	 }.to CANCEL
      }
      enterMedicalInfo{
          on(GOTOFINISH){
        	  try{
                  flow.regEvent.registrationFor=doContacts("Child","Registrant",params,flow,regEvent)
                  return success()
        	  }catch (ValidationException e){
        		 flow.medInstance=e.exceptionVal
      			 flash.message="Please re-enter the information"
        		 return error()
        	  }
          } .to FINISH
          on(GOTOCANCEL){
          	flash.message=CANCEL_MESSAGE
            session.regEventId=null;
           }.to CANCEL
      }
      finish{
        redirect(controller:REGISTRATION, action:REGISTRATIONFINISH)
        }
	  cancel{
		  redirect(controller:REGISTRATION,action:CANCEL)
	  }
      cancelRegistration{
        redirect(controller:REGISTRATION,action:CANCEL)
        }
      }
  }
    