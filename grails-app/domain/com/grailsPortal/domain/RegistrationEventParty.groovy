package com.grailsPortal.domain

class RegistrationEventParty {

	Party             party
	RegistrationEvent registrationEvent
	PartyRole         role
	Boolean           active
    static constraints = {
		party(blank:false, 
		     nullable:false,
		     display:true)
		registrationEvent(blank:false, nullable:false,addOnlyIfShiroRole:true,shiroRole:"Administrator")
		role(nullable:false,addOnlyIfShiroRole:true,shiroRole:"Administrator")
    }

    String toString(){
    	return party.toString()+":"+registrationEvent.toString()
    }
}
