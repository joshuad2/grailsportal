package com.grailsPortal.domain
class ContactEmail implements Serializable{
    static mapping = {
         table 'contact_email'
         emailAddress    column:'email_address' 
         verified        column:'verified' 
    }
              
    String      emailAddress
    String      verified
    static constraints = {
        emailAddress(size: 1..200, blank: false,nullable:false)
        verified(size: 1..255, blank: false,nullable:true)
    }
    String toString() {
        return emailAddress 
    }
}
