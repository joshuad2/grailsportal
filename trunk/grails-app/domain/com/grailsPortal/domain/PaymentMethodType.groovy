package com.grailsPortal.domain 
class PaymentMethodType implements Serializable{
    String cd
    String dsc
    String name
    static constraints = {
        cd(size: 1..20, blank: false,nullable:false)
        dsc(size: 1..200, nullable:true)
        name(size: 1..100, blank: false,nullable:false)
    }
    String toString() {
        return name 
    }
}
