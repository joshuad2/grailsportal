package com.grailsPortal.domain

class PartyContactPhone implements Serializable{

	Party          party
	ContactPhone   phone
	Boolean        active
	ContactType contactType
	static constraints = {
		party(nullable:false, blank:false)
		phone(nullable:false, blank:false)
		contactType(nullable:false, blank:false)
		active(nullable:false, blank:false)
	}
	
	String toString(){
		return party.toString()+":"+phone.toString()+":"+contactType.toString()
	}
}
