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
package com.grailsPortal.domain.menu

import java.io.Serializable;

class PortalMenu  implements Serializable{
	String itemLabel
	String itemText
	PortalMenuType portalMenuType
	PortalMenuConfiguration configuration
	String isActive
	int    sequence
	static hasMany=[subMenus:PortalSubMenu]
    static mapping = {
		subMenus lazy:false
		portalMenuType lazy:false
	}
    static constraints = {
		itemLabel(size:1..40,blank:false,nullable:false)
		itemText(size:1..100,blank:false,nullable:false)
		sequence(range:1..100,blank:false,nullable:false)
		portalMenuType(blank:false,nullable:false)
		isActive(size:1..2,blank:false,nullable:false,inList:['Y','N'])
		subMenus(blank:true,nullable:true)
    }
	
	String toString(){
	 return itemLabel+":"+itemText	
	}
}
