package com.grailsPortal.domain.attribute

/**
 * Attribute Domain class holds the description
 * of the attributes that have the potential to 
 * affect the appearance of the portal.  The data
 * in this domain clas describes a generic representation
 * of data that extends the existing set of tables described
 * in the Portal Application.
 * 
 * @author Joshua Davis
 *
 */
class Attribute implements Serializable{

    String cd
    String dsc
    String name
	Integer maximumWidth
    AttributeType attributeType
    AttributeDataType attributeDataType
    String pathToAttribute
    static constraints = {
        cd(size: 1..20, blank: false,nullable:false)
        dsc(size: 1..200, blank: false,nullable:false)
        name(size: 1..100, blank: false,nullable:false)
        attributeDataType(nullable:true)
        attributeType(nullable:false)
		maximumWidth(nullable:true,blank:true)
        pathToAttribute(size:1..100, blank:true,nullable:true)
    }
    String toString() {
        return name 
    }
}
