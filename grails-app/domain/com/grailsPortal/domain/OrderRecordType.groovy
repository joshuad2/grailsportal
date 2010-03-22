package com.grailsPortal.domain
class OrderRecordType implements Serializable{
    static mapping = {
         table 'order_record_type'
         cd column:'cd' 
         dsc column:'dsc' 
         name column:'name' 
    }
    String cd
    String dsc
    String name
    static constraints = {
        cd(size: 1..20, blank: false)
        dsc(size: 1..200, blank: false)
        name(size: 1..100, blank: false)
    }
    String toString() {
        return name
    }
}
