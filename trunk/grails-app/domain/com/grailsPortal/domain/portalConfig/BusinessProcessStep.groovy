package com.grailsPortal.domain.portalConfig

/**
 * @author Joshua Davis
 * This domain class defines the steps that are
 * available to the views.  These are not updateable
 * by the users in this version because these are the
 * actual strings used in the Spring Web-Flow and subsequently
 * the controllers.
 */
class BusinessProcessStep implements Serializable {
	String name
	String dsc
	String cd
    static belongsTo = View
    static hasMany = [views:View]
    static constraints = {
    	cd(size:1..20, nullable:false, blank:false)
		name(size:1..100, nullable:false, blank:false)
    	dsc(size:1..200, nullable:true, blank:true)
    	views(nullable:true,blank:true)
    }
	String toString(){
		return name
	}
}
