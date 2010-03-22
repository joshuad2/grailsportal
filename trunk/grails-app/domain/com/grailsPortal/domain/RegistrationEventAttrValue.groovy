package com.grailsPortal.domain

class RegistrationEventAttrValue {
    RegistrationEvent registrationEvent
	String    value
	Attribute attribute
    static constraints = {
		value(size:1..100, nullable:false,blank:false)
		registrationEvent(nullable:false,blank:false)
		attribute(nullable:false,blank:false)
    }
	String toString(){
	  return registrationEvent.toString+":"+attribute.toString()+":"+value	
	}
}
