package com.grailsPortal.domain

class PartyContactAddress implements Serializable{
	
	Party          party
	ContactAddress address
	Boolean        active
	ContactType contactType
    static constraints = {
		party(nullable:false, blank:false)
		address(nullable:false, blank:false)
		contactType(nullable:false, blank:false)
		active(nullable:false, blank:false)
    }
	
	String toString(){
		return party.toString()+":"+address.toString()+":"+contactType.toString()
	}
}
