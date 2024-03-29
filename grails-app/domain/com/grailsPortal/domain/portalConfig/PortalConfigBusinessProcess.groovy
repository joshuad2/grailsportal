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

class PortalConfigBusinessProcess  implements Serializable{

	String name
	String purpose
	PortalConfig portalConfig
	static hasMany=[views:View]
    static constraints = {
		portalConfig(nullable:false)
		name(size:1..100, nullable:false, blank:false)
		purpose(size:1..200, nullable:false, blank:false)
		views(nullable:true,blank:true)
    }
	String toString() {
        return name 
    }
}
