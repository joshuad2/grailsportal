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

import java.io.Serializable;

import com.grailsPortal.domain.attribute.Attribute;

class RegistrationEventAttrValue  implements Serializable{
    RegistrationEvent registrationEvent
	String    value
	Attribute attribute
    static constraints = {
		value(size:1..100, nullable:false,blank:false)
		registrationEvent(nullable:false,blank:false)
		attribute(nullable:false,blank:false)
    }
	String toString(){
	  return registrationEvent.toString+":"+attribute.toString()+":"+value	
	}
}
