package com.grailsPortal.domain

class State implements Serializable{
    String name
	String cd
	String dsc
    static constraints = {
		name(size:1..100,blank:false, nullable:false)
		cd(size:1..2,blank:false, nullable:false)
		dsc(size:1..200,blank:true, nullable:true)
    }
	
	String toString(){
	  return name
	}
}
