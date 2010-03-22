package com.grailsPortal.domain

class PartyRole {
    String name
    String dsc
    String cd
    static constraints = {
    	name(size:1..100,nullable:false,blank:false)
    	cd(size:1..20,nullable:false,blank:false)
    	dsc(size:1..200,nullable:true, blank:true)
    }
    
    String toString(){
    	return name
    }
}
