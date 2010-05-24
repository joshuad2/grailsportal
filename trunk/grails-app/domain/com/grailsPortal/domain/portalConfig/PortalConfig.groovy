package com.grailsPortal.domain.portalConfig
 
class PortalConfig {
    static mapping={
    	table 'portal_config'
    	name        column:'portal_name'
    	description column:'description'
    }
	String name
	String description
    static constraints = {
    	name(size:1..100, blank:false,nullable:false)
    	description(size:1..200, blank:true, nullable:true)
    }
    static hasMany=[portalConfigValues: PortalConfigAttrValue,
                    portalAttributes:   PortalConfigAttribute,
                    portalConfigBusinessProcesses: PortalConfigBusinessProcess]
    String toString(){
    	return name
    }
}
