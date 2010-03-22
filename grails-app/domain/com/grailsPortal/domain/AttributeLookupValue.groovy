package com.grailsPortal.domain
class AttributeLookupValue implements Serializable{

	Attribute attribute
	String    lookupValue
	Integer   displayOrder
	String    imagePath
	String    cssClass
	String    cssStyle
	Boolean   active
    static constraints = {
		lookupValue(nullable:false,blank:false)
		attribute(nullable:false,blank:false)
		displayOrder(nullable:false)
		imagePath(nullable:true, blank:true)
		cssClass(nullable:true, blank:true)
		cssStyle(nullable:true, blank:true)
		active(nullable:false,blank:false)
    }
	String toString(){
		return attribute.toString+":"+lookupValue+":"+displayOrder
	}
}
