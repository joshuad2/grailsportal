package com.grailsPortal.domain.portalConfig

import com.grailsPortal.domain.attribute.*
class PortalConfigAttribute {

	Attribute attribute
	Boolean   mandatory
	PortalConfig portalConfig
	
    static constraints = {
		attribute(nullable:false, blank:false)
		mandatory(nullable:false, blank:false)
		portalConfig(nullable:false, blank:false)
    }
    String toString() {
        return portalConfig.name+":"+attribute.name 
    }
}
