package com.grailsPortal.domain

import com.grailsPortal.domain.portalConfig.State;

/**
 * class ContactAddress is the domain class that holds the information for the 
 * address.
 * @author Joshua Davis
 */
class ContactAddress implements Serializable{
    String      address1
    String      address2
    String      address3
    String      city
    State       state
    String      zipcode
    static constraints = {
        address1(size: 1..100, blank: false,nullable:false)
        address2(size:1..100, blank:true, nullable:true)
        address3(size:1..100, blank:true, nullable:true)
        city(size: 1..100, blank: false,nullable:false)
        state( blank: false,nullable:false)
        zipcode(size: 1..10, blank: false,nullable:false)		
    }
    String toString() {
        return address1+" "+city+" "+state+" "+zipcode 
    }
}
