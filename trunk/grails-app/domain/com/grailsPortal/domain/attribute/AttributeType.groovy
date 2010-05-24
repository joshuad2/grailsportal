package com.grailsPortal.domain.attribute


class AttributeType implements Serializable{
    static mapping = {
         table 'attribute_type'
         cd column:'cd' 
         dsc column:'dsc' 
         name column:'name' 
    }
    String cd
    String dsc
    String name
    static constraints = {
        name(size: 1..100, nullable:false,blank: false)
        cd(size: 1..20, nullable:false,blank: false)
        dsc(size: 1..200, nullable:true, blank: true)
    }
    String toString() {
        return name 
    }
}
