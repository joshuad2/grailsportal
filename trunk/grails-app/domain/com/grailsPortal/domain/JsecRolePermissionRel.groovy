package com.grailsPortal.domain 
class JsecRolePermissionRel implements Serializable{
    JsecRole role
    JsecPermission permission
    String target
    String actions

    static constraints = {
        actions(nullable: false, blank: false)
		target(nullable:true,blank:true)
		permission(nullable:true,blank:true)
		role(nullable:true, blank:true)
    }
	String toString(){
	  return actions+":"+target+":"+permission+":"+role	
	}
}
