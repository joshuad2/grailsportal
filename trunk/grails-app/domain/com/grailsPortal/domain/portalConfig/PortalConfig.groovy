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
package com.grailsPortal.domain.portalConfig
import java.io.Serializable;

import com.grailsPortal.domain.menu.PortalMenuConfiguration
class PortalConfig  implements Serializable{
    static mapping={
    	table 'portal_config'
    	name        column:'portal_name'
    	description column:'description'
    }
	String name
	String description
    static constraints = {
    	name(size:1..100, blank:false,nullable:false)
    	description(size:1..200, blank:true, nullable:true)
    }
    static hasMany=[portalConfigValues: PortalConfigAttrValue,
                    portalAttributes:   PortalConfigAttribute,
                    portalConfigBusinessProcesses: PortalConfigBusinessProcess,
	                portalMenuConfigurations: PortalMenuConfiguration]
    String toString(){
    	return name
    }
}
