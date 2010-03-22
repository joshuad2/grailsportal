package com.grailsPortal.domain

import org.apache.shiro.SecurityUtils
import org.apache.shiro.subject.Subject
import org.apache.shiro.session.Session

import java.text.SimpleDateFormat
import com.grailsPortal.exception.ValidationException
import com.grailsPortal.service.util.*;

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
 def index = {
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
 def newRegistration={
    		request.regEventId=null
    		session.regEventId=null
    		redirect(action:'register')
    }
  /***
   * This is the start of the registration flow
   */

def doContacts={partyTypeCd, partyRoleCd,params,flow,regEvent->
        def ctx = servletContext.getAttribute(GrailsApplicationAttributes.APPLICATION_CONTEXT)
        def rs=    ctx.getBean(REGISTRATIONSERVICE)
        return rs.doRegistrationParty(regEvent,
      		     PartyRole.findByCd(partyRoleCd),
      		     PartyType.findByCd(partyTypeCd),
				     params[FIRSTNAME],
				     params[LASTNAME],
				     new java.util.Date(),
                   pvs.buildEmailParams(params),
                   pvs.buildPhoneParams(params),
                   pvs.buildAddressParams(params),
                   pvs.buildOtherParams(params)
				   )
  }
def registerFlow = {
      selectProduct{// this by default is the first step
    	on(GOTOREGISTRANTINFO){
          try{
        	def ctx = servletContext.getAttribute(GrailsApplicationAttributes.APPLICATION_CONTEXT)
        	def rs=ctx.getBean(REGISTRATIONSERVICE)
            flow.regEvent=rs.handleInitialRegistrationEvent(session.regEventId, new java.util.Date())// this has to run with the first step
    		flow.regEvent.orderRecord=rs.doOrderRecord(sc,flow.regEvent.orderRecord,
    				                                   "online Order",0.0,0.0,0.0,
													   new java.util.Date(),"initial",
    				                                   OrderRecordType.findByCd("Registration"))
    		flow.regEvent.orderRecord=rs.handleOrderRecordLineItems(flow.regEvent.orderRecord, params)
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
          flow.regEvent.registrationFor=doContacts("Child","Registrant",params,flow,regEvent)
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
     	 on(GOTOPARENTINFO2){
     		try{
     	      flow.regEvent.registrationFor=doContacts("Parent",
			                                           "Primary Parent",
													   params,flow,regEvent)
     		  return success()
     		}catch(ValidationException e){
     			flash.message="Please re-enter the information"
         		flow.parentInstance=e.exceptionVal
     		    return error()
     		}
     	 }.to SECONDARYPARENTINFO    	 
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
      cancelRegistration{
        redirect(controller:REGISTRATION,action:CANCEL)
        }
      }
  }
    