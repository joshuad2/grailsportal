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

class RegistrationEventParty {

	Party             party
	RegistrationEvent registrationEvent
	PartyRole         role
	Boolean           active
    static constraints = {
		party(blank:false, 
		     nullable:false,
		     display:true)
		registrationEvent(blank:false, nullable:false,addOnlyIfShiroRole:true,shiroRole:"Administrator")
		role(nullable:false,addOnlyIfShiroRole:true,shiroRole:"Administrator")
    }

    String toString(){
    	return party.toString()+":"+registrationEvent.toString()
    }
}