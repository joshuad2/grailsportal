package com.grailsPortal.domain
class Responsibility implements Serializable{
    static mapping = {
         table 'responsibility'
         cd column:'cd' 
         dsc column:'dsc' 
         name column:'name' 
    }
    java.lang.String cd
    java.lang.String dsc
    java.lang.String name
    static constraints = {
        cd(size: 1..20, blank: false)
        dsc(size: 1..200, blank: false)
        name(size: 1..100, blank: false)
    }
    String toString() {
        return name 
    }
}
