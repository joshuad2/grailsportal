package com.grailsPortal.domain
/**
 * This domain class holds the default components
 * that can be displayed by the portal.
 * @author Joshua Davis
 */
class Component implements Serializable{
    String name
    String dsc
    String cd
    static constraints = {
    	name(size:1..100,nullable:false,blank:false)
    	dsc(size:1..100,nullable:false,blank:false)
    	cd(size:1..20,nullable:false,blank:false)
    }
    String toString() {
        return name 
    }
}
