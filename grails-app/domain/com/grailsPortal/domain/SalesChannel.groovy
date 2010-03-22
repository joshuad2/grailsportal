package com.grailsPortal.domain 
class SalesChannel implements Serializable{
    static mapping = {
         table 'sales_channel'
         cd column:'cd' 
         dsc column:'dsc' 
         name column:'name' 
         salesChannelType column:'sales_channel_type_id'
    }
    String cd
    String dsc
    String name
    SalesChannelType salesChannelType
    static constraints = {
        cd(size: 1..20, blank: false,nullable:false)
        dsc(size: 1..200, blank: true,nullable:true)
        name(size: 1..100, blank: false,nullable:false)
        salesChannelType(nullable:false)
    }
    String toString() {
        return name 
    }
}
