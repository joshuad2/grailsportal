package com.grailsPortal.domain.attribute;

class AttributeComponentGroup implements Serializable{
    String name
    String dsc
    String cd
	Boolean active
	Boolean mandatory
    static constraints = {
    	name(size:1..100,nullable:false,blank:false)
    	dsc(size:1..200,nullable:true,blank:true)
        cd(size:1..20,nullable:false,blank:false)
        attributeComponents(nullable:true,blank:true)
		active(nullable:false, blank:false)
		mandatory(nullable:false,blank:false)
    }
    String toString() {
        return name+":"+cd
    }
}
