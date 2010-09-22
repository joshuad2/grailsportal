/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
	Party       party
	Boolean     active
	ContactType contactType
    static constraints = {
        address1(size: 1..100, blank: false,nullable:false)
        address2(size:1..100, blank:true, nullable:true)
        address3(size:1..100, blank:true, nullable:true)
        city(size: 1..100, blank: false,nullable:false)
        state( blank: false,nullable:false)
        zipcode(size: 1..10, blank: false,nullable:false)
		party(nullable:false,blank:false)
		active(nullable:false,blank:false)
		contactType(nullable:false,blank:false)		
    }
    String toString() {
        return address1+" "+city+" "+state+" "+zipcode 
    }
}
