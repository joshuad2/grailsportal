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
class JsecRolePermissionRel implements Serializable{
    JsecRole role
    JsecPermission permission
    String target
    String actions

    static constraints = {
        actions(nullable: false, blank: false)
		target(nullable:true,blank:true)
		permission(nullable:true,blank:true)
		role(nullable:true, blank:true)
    }
	String toString(){
	  return actions+":"+target+":"+permission+":"+role	
	}
}
