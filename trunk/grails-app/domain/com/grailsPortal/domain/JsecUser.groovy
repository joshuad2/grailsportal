package com.grailsPortal.domain
import com.grailsPortal.domain.Party
class JsecUser implements Serializable{
    String username
    String passwordHash
    Party party
	Boolean active
	
    static hasMany = [ attributeValues: JsecUserAttributeValue ]
    static constraints = {
        username(nullable: false, blank: false,unique:true)
        passwordHash(nullable:true,blank:true)
		party(nullable:true,blank:true)
		attributeValues(nullable:true,blank:true)
		active(nullable:false,blank:false)
    }
    String toString() {
        return  username
    }
}
