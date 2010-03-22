package com.grailsPortal.domain

class OrderStatus implements Serializable{
    String name
	String cd
	String dsc
	Boolean active = true
    static constraints = {
		name(size:1..100,blank:false, nullable:false)
		cd(size:1..20,blank:false, nullable:false)
		dsc(size:1..200,blank:true, nullable:true)
		active(blank:false, nullable:false)
    }
}
