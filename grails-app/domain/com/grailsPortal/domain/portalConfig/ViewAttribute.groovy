package com.grailsPortal.domain.portalConfig
import com.grailsPortal.domain.attribute.*
class ViewAttribute implements Serializable, Comparable{
	View               view
	AttributeComponent attributeComponent
	Integer            displayOrder
	Boolean            mandatory
    static constraints = {
    	view(nullable:false, blank:false)
    	attributeComponent(nullable:false,blank:false)
    	displayOrder(range:1..100,nullable:false,blank:false)
    	mandatory(nullable:false,blank:false)
    }
	   int compareTo(obj) {
	       displayOrder.compareTo(obj.displayOrder)
	   }

    String toString() {
        return view.name+":"+attributeComponent.name 
    }
}