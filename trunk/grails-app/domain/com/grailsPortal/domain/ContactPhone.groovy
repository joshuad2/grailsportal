package com.grailsPortal.domain
class ContactPhone implements Serializable{
    static mapping = {
         table 'contact_phone'
         phoneNumber   column:'phone_number' 
    }
    String      phoneNumber
    String      areaCode
    String      internationalCode
    static constraints = {
    	areaCode(size:1..4,nullable:true, blank:true)
    	internationalCode(size:1..2,nullable:true,blank:true)
        phoneNumber(size: 1..20, blank: false,nullable:false)
    }
    String toString() {
        return phoneNumber 
    }
}
