package com.grailsPortal.domain
class JsecUserPermissionRel implements Serializable{
    JsecUser user
    JsecPermission permission
    String target
    String actions

    static constraints = {
        target(nullable: true, blank: false)
        actions(nullable: false, blank: false)
    }
	String toString(){
	   return user.toString()+":"+permission.toString()+":"+target+":"+actions
	}
}
