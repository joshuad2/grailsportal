package com.grailsPortal.domain
class ContactType implements Serializable{
    String cd
    String dsc
    String name
    static constraints = {
        cd(size: 1..20, blank: false)
        dsc(size: 1..200, blank: false)
        name(size: 1..100, blank: false)
    }
    String toString() {
        return name 
    }
}
