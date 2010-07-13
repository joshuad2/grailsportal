package com.grailsPortal.domain.portalConfig
import com.grailsPortal.domain.attribute.AttributeComponentGroup;

class ViewAttributeComponentGroup implements Serializable{

	String                  name
	View                    view
	Long                    displayOrder
	com.grailsPortal.domain.attribute.AttributeComponentGroup attributeComponentGroup
	Boolean                 active
	Boolean                 mandatory
    static constraints = {
		name(size:1..100,nullable:false,blank:false)
		displayOrder(nullable:false,blank:false)
		view(nullable:false,blank:false)
		active(nullable:false,blank:false)
		mandatory(nullable:false,blank:false)
		attributeComponentGroup(nullable:false, blank:false)
    }
	String toString(){
		return name+":"+attributeComponentGroup.toString()+":"+view.toString()+":"+displayOrder
	}
}
