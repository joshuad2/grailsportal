package com.grailsPortal.domain
class JsecPermission implements Serializable{
    String type
    String possibleActions

    static constraints = {
        type(nullable: false, blank: false, unique: true)
        possibleActions(nullable:false, blank: false)
    }
	
	String toString(){
	 return type+":"+possibleActions	
	}
}
