package com.grailsPortal.domain

class RegistrationEventOrderRecord {
    RegistrationEvent event
    OrderRecord       order
    Boolean           isActive
    String            comment
    static constraints = {
    	event(nullable:false,blank:false)
    	order(nullable:false,blank:false)
    	isActive(nullable:false,blank:false)
    	comment(size:1..1000,nullable:true,blank:true)
    }
    
    String toString(){
    	return event.toString()+":"+order.toString()+":"+isActive
    }
}
