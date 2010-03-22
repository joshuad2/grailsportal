package com.grailsPortal.domain
class JsecUserRoleRel implements Serializable{
    JsecUser user
    JsecRole role

    static constraints = {
        user(nullable: false)
        role(nullable:false)
    }
    String toString() {
        return user.username+" - "+role.name 
    }
    
}
