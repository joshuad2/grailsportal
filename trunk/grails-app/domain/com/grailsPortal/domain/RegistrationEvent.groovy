package com.grailsPortal.domain 
 import java.sql.Timestamp
class RegistrationEvent implements Serializable{
	Party              registrationFor
    Date               registrationDate
    String             registrationGrade
    JsecUser           registrationUser
   
    static hasMany=[ contacts: RegistrationEventParty,
                     orders: RegistrationEventOrderRecord,
	                 attrValues: RegistrationEventAttrValue]
    static constraints = {
        registrationFor(nullable:false,blank:false)
        registrationUser(nullable:false,blank:false)
        registrationDate(nullable:true,blank:true)
        contacts(nullable:true,blank:true)
		orders(nullable:true,blank:true)
		attrValues(nullable:true,blank:true)
		
    }
    String toString() {
        return  registrationFor.firstName+" "+registrationFor.lastName
    }
}
