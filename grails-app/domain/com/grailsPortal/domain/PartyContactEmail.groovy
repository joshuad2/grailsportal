package com.grailsPortal.domain

class PartyContactEmail implements Serializable{
	Party          party
	ContactEmail   email
	Boolean        active
	ContactType contactType
	static constraints = {
		party(nullable:false, blank:false)
		email(nullable:false, blank:false)
		contactType(nullable:false, blank:false)
		active(nullable:false, blank:false)
	}
	
	String toString(){
		return party.toString()+":"+email.toString()+":"+contactType.toString()
	}
}
