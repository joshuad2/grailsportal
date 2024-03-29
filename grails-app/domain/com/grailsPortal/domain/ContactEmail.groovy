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
class ContactEmail implements Serializable{
    static mapping = {
         table 'contact_email'
         emailAddress    column:'email_address' 
         verified        column:'verified' 
    }
              
    String      emailAddress
    String      verified
	Party          party
	Boolean        active
	ContactType contactType

    static constraints = {
        emailAddress(size: 1..200, blank: false,nullable:false)
        verified(size: 1..255, blank: false,nullable:true)
	    party(addOnlyIfShiroRole:true,shiroRole:'admin',valueRoles:'user',blank:false,nullable:false)
		contactType(addOnlyIfShiroRole:true,shiroRole:'admin',valueRoles:'user',blank:false,nullable:false)
    }
    String toString() {
        return emailAddress 
    }
}
