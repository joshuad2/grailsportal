package com.grailsPortal.domain
class PortalConfigBusinessProcess {

	String name
	String purpose
	PortalConfig portalConfig
	static hasMany=[views:View]
    static constraints = {
		portalConfig(nullable:false)
		name(size:1..100, nullable:false, blank:false)
		purpose(size:1..200, nullable:false, blank:false)
		views(nullable:true,blank:true)
    }
	String toString() {
        return name 
    }
}
