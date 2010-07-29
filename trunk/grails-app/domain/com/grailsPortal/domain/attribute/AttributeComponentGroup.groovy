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
package com.grailsPortal.domain.attribute;

class AttributeComponentGroup implements Serializable{
    String name
    String dsc
    String cd
	Boolean active
	Boolean mandatory
	static hasMany=[attributeComponents:AttributeComponent]
    static constraints = {
    	name(size:1..100,nullable:false,blank:false)
    	dsc(size:1..200,nullable:true,blank:true)
        cd(size:1..20,nullable:false,blank:false)
        attributeComponents(nullable:true,blank:true)
		active(nullable:false, blank:false)
		mandatory(nullable:false,blank:false)
    }
    String toString() {
        return name+":"+cd
    }
}
