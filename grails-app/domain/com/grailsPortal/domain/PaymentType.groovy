package com.grailsPortal.domain 
class PaymentType implements Serializable{
    static mapping = {
         table 'payment_type'
         cd       column:'cd' 
         dsc      column:'dsc' 
         name     column:'name' 
    }
    String   cd
    String   dsc
    String   name
    static constraints = {
        cd(size: 1..20, blank: false,nullable:false)
        dsc(size: 1..200, blank:true,nullable:true)
        name(size: 1..100, blank: false,nullable:false)
    }
    String toString() {
        return name 
    }
}
