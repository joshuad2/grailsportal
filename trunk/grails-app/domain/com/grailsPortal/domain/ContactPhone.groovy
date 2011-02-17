/*
 * Licensed under the Apache License, Version 2.0 (the "License")
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
class ContactPhone implements Serializable{
    static mapping = {
         table 'contact_phone'
         phoneNumber   column:'phone_number' 
    }
    String      phoneNumber
    String      areaCode
    String      internationalCode
	Party          party
	Boolean        active
	ContactType contactType
    static constraints = {
    	areaCode(size:1..4,nullable:false, blank:false)
    	internationalCode(size:1..2,nullable:true,blank:true)
        phoneNumber(size: 1..20, blank: false,nullable:false)
		party(addOnlyIfShiroRole:true,shiroRole:'admin',valueRoles:'user',nullable:false,blank:false)
		contactType(addOnlyIfShiroRole:true,shiroRole:'admin',valueRoles:'user',nullable:false,blank:false)
		active(nullable:false,blank:false)
    }
    String toString() {
        return phoneNumber 
    }
}
