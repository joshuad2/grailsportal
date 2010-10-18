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
 import java.sql.Timestamp
class RegistrationEvent implements Serializable{
	Party              registrationFor
    Date               registrationDate
    String             registrationGrade
    JsecUser           registrationUser
   
    static hasMany=[ contacts: RegistrationEventParty,
                     orders: RegistrationEventOrderRecord,
	                 attrValues: RegistrationEventAttrValue]
    static constraints = {
        registrationUser(nullable:false,blank:false,display:true)
        registrationFor(nullable:true,blank:true,display:true)
		registrationGrade(nullable:true,blank:true,display:true)
        registrationDate(nullable:true,blank:true,display:true)
        contacts(nullable:true,blank:true)
		orders(nullable:true,blank:true)
		attrValues(nullable:true,blank:true)
		
    }
    String toString() {
        return  registrationFor.firstName+" "+registrationFor.lastName
    }
}
