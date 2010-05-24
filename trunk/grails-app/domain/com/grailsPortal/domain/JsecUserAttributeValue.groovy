package com.grailsPortal.domain

import com.grailsPortal.domain.attribute.Attribute;

class JsecUserAttributeValue {
	
	JsecUser user
	String value
	Attribute attribute
	Boolean active
    static constraints = {
		user(nullable:false,blank:false)
		attribute(nullable:false, blank:false)
		value(nullable:false,blank:false)
		active(nullable:false)
    }
	String toString(){
		return user.toString()+":"+attribute.toString()+":"+value+":"+active
	}
}
