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

import com.grailsPortal.domain.attribute.*
class PortalConfigAttribute {

	Attribute attribute
	Boolean   mandatory
	PortalConfig portalConfig
	
    static constraints = {
		attribute(nullable:false, blank:false)
		mandatory(nullable:false, blank:false)
		portalConfig(nullable:false, blank:false)
    }
    String toString() {
        return portalConfig.name+":"+attribute.name 
    }
}