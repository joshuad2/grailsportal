package com.grailsPortal.controller
import com.grailsPortal.domain.RegistrationEvent
import com.grailsPortal.domain.PartyType
import com.grailsPortal.domain.Party

class CamperRegistrationController {
    def registrationService
	def portalviewService
	def securityService
    def index = { redirect(action: 'camperRegistration',params: params)}
    def camperRegistration={attrs->
		def isInitial=false
		def regEvent=attrs?.regEvent
		if (regEvent==null){
			regEvent=session.regEvent
			if (regEvent==null){
			  session.regEvent=new RegistrationEvent()
			  regEvent=registrationService.handleInitialRegistrationEvent(session?.regEventId, new java.util.Date(),securityService.getRegisteredUser())// this has to run with the first step
			  isInitial=true
			}
		}
		session.regEvent=regEvent
		session.regEventId=regEvent.id
		["registerInstance":regEvent,
		 "regEvent":regEvent]
	}
	
	def campRegister={attrs->
	    RegistrationEvent event=session?.regEvent
	    PartyType partyType=PartyType.findByName("Child")
		session?.regEvent?.registrationGrade=params["registrationGrade"]
		def camper=registrationService.doParty(portalviewService,
													   partyType,
													   event.registrationFor,
													   params["firstName"],
													   params["lastName"],
													   new Date(),
													   new ArrayList(),
													   new ArrayList(),
													   new ArrayList(),
													   new ArrayList())
		event.registrationFor=camper
		camper.save(flush:true)
		event.save(flush:true)
		render (model:["registerInstance":event,"regEvent":event],view:'camperRegistration')
		}
}
