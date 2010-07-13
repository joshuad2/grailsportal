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
import com.grailsPortal.domain.Party
class JsecUser implements Serializable{
    String username
    String passwordHash
    Party party
	Boolean active
	
    static hasMany = [ attributeValues: JsecUserAttributeValue ]
    static constraints = {
        username(nullable: false, blank: false,unique:true)
        passwordHash(nullable:true,blank:true)
		party(nullable:true,blank:true)
		attributeValues(nullable:true,blank:true)
		active(nullable:false,blank:false)
    }
    String toString() {
        return  username
    }
}
